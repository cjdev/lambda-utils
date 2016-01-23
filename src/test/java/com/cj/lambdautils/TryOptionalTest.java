package com.cj.lambdautils;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TryOptionalTest {
    @Test(expected=RuntimeException.class)
    public void canWrapExceptionsFromAThrowingRunnable(){
    	Try.to(()->throwCheckedException());
    }
    
    @Test
    public void canOptionaNotPresentACheckedThrowingSupplier(){
    	Optional<String> s = Try.to(()->{
    		throwCheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isPresent());
    }
    
    @Test
    public void canOptionaNotPresentAnUnCheckedThrowingSupplier(){
    	Optional<String> s = Try.to(()->{
    		throwUncheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isPresent());
    }
    
    @Test
    public void canConvertASupplierIntoAnOptional(){
    	Optional<String> s = Try.to(()->{
    		return "Anything";
		});
    	
    	assertTrue(s.isPresent());
    	assertEquals(s.get(), "Anything");
    }
    
    
    public void throwCheckedException() throws Exception{
    	throw new Exception("This is a checked exception");
    }
    
    public void throwUncheckedException(){
    	throw new RuntimeException("This is a checked exception");
    }
    
}
