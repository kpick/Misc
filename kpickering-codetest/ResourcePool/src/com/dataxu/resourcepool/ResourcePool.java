package com.dataxu.resourcepool;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This resource pool uses two queues that represent the available and
 * unavailable resources in the system. This is mainly to track uniqueness
 * in the system for adding, and to remove a specific resource (even if it's
 * in use. I debated using a synchronized hashmap, but felt the dual blocking
 * queues fit better.
 * 
 * @author Ken Pickering
 * @param <R>: Generic Resource Class
 */
public class ResourcePool<R> {
	private BlockingQueue<R> availQueue = new LinkedBlockingQueue<R>();
	private BlockingQueue<R> occupiedQueue = new LinkedBlockingQueue<R>();
	private AtomicBoolean open = new AtomicBoolean(true);
	
	public ResourcePool() {
	
	}
	
	/**
	 * Open marks the resource pool as open.
	 */
	public void open() {
		open.set(true);		
	}
	
	/**
	 * Checks to see if the resource pool is open.
	 * 	 	true = open
	 * 		false = closed
	 * 
	 * @return boolean value of the resource pool. 
	 */
	public boolean isOpen() {
		return open.get();
	}
	
	/**
	 * Close first locks the pool for further updates. Then, it blocks
	 * the close until all utilized resources are released.
	 */
	public void close() {
		open.set(false);			

		//Loop to check to make sure all resources are released, then returns.
		while (true) {
			int size = occupiedQueue.size();
			if (size == 0) {
				break;
			} else {
				Thread.yield();
			}
		}
	}
	
	/**
	 * Acquire method blocks until something can be retrieved from the pool.
	 * I use the take() call to block until there's something in the queue.
	 * Then, I mark the resource as occupied and return it.
	 * 
	 * In case of exception, it will return null. I wasn't sure what sort of 
	 * exception handling your test harness had.
	 * @return
	 */
	public synchronized R acquire() {
		if (open.get()) {
			R res;
			try {
				res = availQueue.take();
				occupiedQueue.put(res);
				return res;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Will only return null if interrupted or pool is not open. take() blocks.
		return null;
	}
	
	/**
	 * Acquire based on a terminator. I use the poll command on the queue 
	 * which will return null on a timeout case. If it returns null, I return
	 * null, else I add the resource to the occupied queue.
	 * 
	 * Any exception will result in a null getting thrown as well.
	 * 
	 * @param timeout Timeout value in numeric form
	 * @param timeUnit The unit of time the timeout param represents
	 * @return The acquired resource
	 */
	public synchronized R acquire (long timeout, TimeUnit timeUnit) {
		if (open.get()) {
			try {
				// poll will return null if timeout.
				R res = availQueue.poll(timeout, timeUnit);
				if (res != null)
					occupiedQueue.put(res);
				return res;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Will also return null of pool is not open
 		return null;
	}
	
	/**
	 * Releases the given resource after it's been used. If it's not a
	 * real resource, it won't put it in the available queue.
	 * @param res
	 */
	public void release (R res) {
		if (res == null) {
			throw new RuntimeException("Can't release a null resource.");
		}
		boolean inUse = occupiedQueue.remove(res);
		if (inUse) {
			try {
				availQueue.put(res);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Resource no longer in pool. Was it removed?");
		}
	}
	
	/**
	 * Add checks for resource existence in the available and unavailable
	 * queues. If it's in neither, it adds it to the available queue.
	 * 
	 * @param res
	 * @return
	 */
	public synchronized boolean add (R res) {
		if (res == null) {
			throw new RuntimeException("Can't add a null resource.");
		}
		for (Iterator<R> i = availQueue.iterator(); i.hasNext();) {
			R r = (R) i.next();
			if (r.equals(res))
				return false;
		}
		for (Iterator<R> i = occupiedQueue.iterator(); i.hasNext();) {
			R r = (R) i.next();
			if (r.equals(res))
				return false;
		}
		try {
			availQueue.put(res);		
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Removes the resource from either of the queues.
	 * 		true = removed
	 * 		false = not removed/not present
	 * 
	 * @param res The resource to be removed.
	 * @return The boolean value on the result
	 */
	public boolean remove (R res) {
		if (res == null) {
			throw new RuntimeException("Can't remove a null resource.");
		}
		boolean found = false;
		// If it's in the available queue, just remove it.
		boolean availRemove = availQueue.remove(res);
		if (availRemove) {
			return true;
		}
		
		// Look to see if it's being used.
		for (Iterator<R> i = occupiedQueue.iterator(); i.hasNext();) {
			R r = (R) i.next();
			if (r.equals(res)) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			System.out.println("Error: Resource not found in Resource Pool. " +
					"Was it previously removed?");
			return false;
		} else {
			// If it is being used, wait for it to go back to the available Queue
			while (true) {
				boolean removed = (availQueue.remove(res));
				if (removed) {
					break;
				} else {
					Thread.yield();
				}	
			}
			return true;
		}
	}
	
	/**
	 * Remove the existing resource without blocking. 
	 * 		True = resource removed.
	 * 		False = resource not removed/not present
	 * 
	 * @param res The resource to be removed.
	 * @return returns a boolean if the queue was modified.
	 */
	public synchronized boolean removeNow(R res) {
		if (res == null) {
			throw new RuntimeException("Can't remove a null resource.");
		}
		boolean availRemove = availQueue.remove(res);
		boolean usedRemove = occupiedQueue.remove(res);
		return availRemove || usedRemove;
	}
	
	/**
	 * A non-blocking close of the queue. Does not wait for 
	 * resources to be closed before exiting.
	 */
	public void closeNow( ) {
		open.set(false);
	}
}
