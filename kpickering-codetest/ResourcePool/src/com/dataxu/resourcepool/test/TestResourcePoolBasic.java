package com.dataxu.resourcepool.test;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import com.dataxu.resourcepool.ResourcePool;

/**
 * Single threaded test cases to test basic functionality.
 * 
 * @author Ken Pickering
 */
public class TestResourcePoolBasic {

	@Test
	public void testOpenCloseBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		assertTrue(pool.isOpen());
		pool.close();
		assertFalse(pool.isOpen());
		pool.open();
		assertTrue(pool.isOpen());
	}
	
	@Test
	public void testOpenCloseNowBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		assertTrue(pool.isOpen());
		pool.closeNow();
		assertFalse(pool.isOpen());
		pool.open();
		assertTrue(pool.isOpen());
	}

	@Test
	public void testAddBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();

		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddNull() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		pool.add(null);
	}

	@Test
	public void testAcquireBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();

		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
	}

	@Test
	public void testAcquireTimedBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();

		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertEquals(obj, obj2);
	}
	
	
	@Test
	public void testReleaseBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(obj2);
		Object obj4 = pool.acquire();
		assertEquals(obj, obj4);
	}

	@Test(expected=RuntimeException.class)
	public void testReleaseNull() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(null);
	}

	@Test
	public void testRemoveBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(obj2);
		Object obj4 = pool.acquire();
		assertEquals(obj, obj4);
		
		pool.release(obj4);
		boolean remove = pool.remove(obj4);
		assertTrue(remove);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRemoveNull() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(obj2);
		Object obj4 = pool.acquire();
		assertEquals(obj, obj4);
		
		pool.release(obj4);
		pool.remove(null);
	}	
	
	@Test
	public void testRemoveNowBasic() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(obj2);
		Object obj4 = pool.acquire();
		assertEquals(obj, obj4);
		
		pool.release(obj4);
		boolean remove = pool.removeNow(obj4);
		assertTrue(remove);
	}

	@Test(expected=RuntimeException.class)
	public void testRemoveNowNull() {
		ResourcePool<Object> pool = new ResourcePool<Object>();
		
		Object obj = new Object();
		pool.add(obj);
		Object obj2 = pool.acquire();
		assertEquals(obj, obj2);
		
		Object obj3 = pool.acquire(100, TimeUnit.MILLISECONDS);
		assertNull(obj3);
		
		pool.release(obj2);
		Object obj4 = pool.acquire();
		assertEquals(obj, obj4);
		
		pool.release(obj4);
		pool.removeNow(null);
	}
	
}
