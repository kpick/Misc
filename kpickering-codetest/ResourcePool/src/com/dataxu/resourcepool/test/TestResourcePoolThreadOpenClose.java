package com.dataxu.resourcepool.test;

import com.dataxu.resourcepool.ResourcePool;
import junit.framework.*;
import net.sourceforge.groboutils.junit.v1.*;

public class TestResourcePoolThreadOpenClose extends TestCase {
    private ResourcePool<MockResource> pool = new ResourcePool<MockResource>();

    private class Requestor extends TestRunnable {
    	private int id;
        
        private Requestor(int id) {
            this.id = id;
        }

        public void runTest() throws Throwable {
            long l;
            l = Math.round(2 + Math.random() * 3);
            
            //Acquire Resource
            MockResource mr = pool.acquire();
            if (mr == null) {
            	System.out.println("Requestor " + id + " can't acuire resource.");
            } else {
                System.out.println("Requestor " + id + " acquired resource " + mr.id);
                
                // Sleep between 2-5 seconds
                Thread.sleep(l * 1000);
                
                // Release Resource
                pool.release(mr);
                System.out.println("Requestor " + id + " released resource " + mr.id);	
            }
        }
    }
    
    private class Closer extends TestRunnable {
        
        private Closer() {
        }

        public void runTest() throws Throwable {
            long l;
            l = Math.round(2 + Math.random());
            Thread.sleep(l * 1000);
            synchronized (pool) {
                pool.close();
                System.out.println("Closing pool.");            	
            }

            Thread.sleep(l * 100);     
            synchronized (pool) {
            	System.out.println("Opening pool");
            	pool.open();
            }
        }
    }
    
    private class CloseNow extends TestRunnable {
        
        private CloseNow() {
        }

        public void runTest() throws Throwable {
            long l;
            l = Math.round(2 + Math.random());
            Thread.sleep(l * 1000);
            synchronized (pool) {
                pool.closeNow();
                System.out.println("Closing pool.");            	
            }
        }
    }
    
    public void testClose() throws Throwable {
    	System.out.println("TESTING CLOSE AND OPEN");

    	
    	MockResource res1 = new MockResource(1);
    	MockResource res2 = new MockResource(2);
    	
    	pool.add(res1);
    	pool.add(res2);
    	
		//instantiate the TestRunnable classes
		TestRunnable tr1, aa1, tr2, tr3, tr4, tr5, tr6, tr7, tr8, tr9, tr10, tr11, tr12;
		tr1 = new Requestor(1);
		aa1 = new Closer();
		tr2 = new Requestor(2);
		tr3 = new Requestor(3);
		tr4 = new Requestor(4);
		tr5 = new Requestor(5);
		tr6 = new Requestor(6);
		tr7 = new Requestor(7);
		tr8 = new Requestor(8);
		tr9 = new Requestor(9);
		tr10 = new Requestor(10);
		tr11 = new Requestor(11);
		tr12 = new Requestor(12);
		
		TestRunnable[] trs = {tr1, tr2, tr3, aa1, tr4, tr5, tr6, tr7, tr8, tr9, tr10, tr11, tr12};
		MultiThreadedTestRunner mttr =new MultiThreadedTestRunner(trs);		
	    mttr.runTestRunnables();
	    
	    
	    //Rerun after open.
	    Thread.sleep(10000);
	    System.out.println("TESTING REOPEN");
	    TestRunnable[] trs2 = {tr1};
		MultiThreadedTestRunner mttr2 =new MultiThreadedTestRunner(trs2);		
	    mttr2.runTestRunnables();       
	}
    
    public void testCloseNow() throws Throwable {
    	System.out.println("TESTING CLOSE NOW");

    	
    	MockResource res1 = new MockResource(1);
    	MockResource res2 = new MockResource(2);
    	
    	pool.add(res1);
    	pool.add(res2);
    	
		//instantiate the TestRunnable classes
		TestRunnable tr1, aa1, tr2, tr3, tr4, tr5, tr6, tr7, tr8, tr9, tr10, tr11, tr12;
		tr1 = new Requestor(1);
		aa1 = new CloseNow();
		tr2 = new Requestor(2);
		tr3 = new Requestor(3);
		tr4 = new Requestor(4);
		tr5 = new Requestor(5);
		tr6 = new Requestor(6);
		tr7 = new Requestor(7);
		tr8 = new Requestor(8);
		tr9 = new Requestor(9);
		tr10 = new Requestor(10);
		tr11 = new Requestor(11);
		tr12 = new Requestor(12);
		
		TestRunnable[] trs = {tr1, aa1, tr2, tr3,tr4, tr5, tr6, tr7, tr8, tr9, tr10, tr11, tr12};
		MultiThreadedTestRunner mttr =new MultiThreadedTestRunner(trs);		
	    mttr.runTestRunnables();  
	}
    
}
