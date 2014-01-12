package com.dataxu.resourcepool.test;

public class MockResource {
	public int id;
	
	public MockResource() {
		id = 0;
	}
	
	public MockResource(int i) {
		id = i;
	}
	
	@Override
	public boolean equals (Object obj) {
        boolean result = false;
        if (obj instanceof MockResource) {
        	MockResource res = (MockResource) obj;
        	if (this.id == res.id) 
        		result = true;
        }
        return result;
	}
	
    @Override 
    public int hashCode() {
        return id;
    }
}

