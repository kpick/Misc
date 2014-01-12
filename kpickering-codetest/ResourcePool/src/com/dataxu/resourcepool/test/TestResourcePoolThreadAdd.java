package com.dataxu.resourcepool.test;

import com.dataxu.resourcepool.ResourcePool;
import junit.framework.*;
import net.sourceforge.groboutils.junit.v1.*;

public class TestResourcePoolThreadAdd extends TestCase {
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
            System.out.println("Requestor " + id + " acquired resource " + mr.id);
   
            // Sleep between 2-5 seconds
            Thread.sleep(l * 1000);
            
            // Release Resource
            pool.release(mr);
            System.out.println("Requestor " + id + " released resource " + mr.id);
        }
    }
    
    private class Adder extends TestRunnable {
    	private int id;
        
        private Adder(int id) {
            this.id = id;
        }

        public void runTest() throws Throwable {
            MockResource mr = new MockResource(id);
            boolean ret = pool.add(mr);
            if (ret) 
            	System.out.println("Adder " + id + " Added resource " + mr.id);
            else 
            	System.out.println("Adder " + id + " duplicate. Not modified.");
        }
    }
    
    public void testAdd() throws Throwable {
    	System.out.println("ADDING BASIC");

    	
    	MockResource res1 = new MockResource(1);
    	MockResource res2 = new MockResource(2);
    	
    	pool.add(res1);
    	pool.add(res2);
    	
		//instantiate the TestRunnable classes
		TestRunnable tr1, aa1, tr2, tr3, tr4, tr5, tr6, tr7;
		tr1 = new Requestor(1);
		aa1 = new Adder(10);
		tr2 = new Requestor(2);
		tr3 = new Requestor(3);
		tr4 = new Requestor(4);
		tr5 = new Requestor(5);
		tr6 = new Requestor(6);
		tr7 = new Requestor(7);
		
		//pass that instance to the MTTR
		TestRunnable[] trs = {tr1, aa1, tr2, tr3,tr4, tr5, tr6, tr7};
		MultiThreadedTestRunner mttr =new MultiThreadedTestRunner(trs);
		
		//kickstarts the MTTR & fires off threads
	    mttr.runTestRunnables();
	}
    
    public void testAddNonUnique() throws Throwable {
    	System.out.println("ADDING NON UNIQUE");
    	
    	MockResource res1 = new MockResource(1);
    	MockResource res2 = new MockResource(2);
    	
    	pool.add(res1);
    	pool.add(res2);
    	
		//instantiate the TestRunnable classes
		TestRunnable tr1, aa1, tr2, tr3, tr4, tr5, tr6, tr7;
		tr1 = new Requestor(1);
		aa1 = new Adder(1);
		tr2 = new Requestor(2);
		tr3 = new Requestor(3);
		tr4 = new Requestor(4);
		tr5 = new Requestor(5);
		tr6 = new Requestor(6);
		tr7 = new Requestor(7);
		
		//pass that instance to the MTTR
		TestRunnable[] trs = {tr1, aa1, tr2, tr3,tr4, tr5, tr6, tr7};
		MultiThreadedTestRunner mttr =new MultiThreadedTestRunner(trs);
		
		//kickstarts the MTTR & fires off threads
	    mttr.runTestRunnables();
	}

}
