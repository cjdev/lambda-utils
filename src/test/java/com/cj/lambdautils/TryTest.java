package com.cj.lambdautils;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TryTest {
    @Test(expected=RuntimeException.class)
    public void oldCanWrapExceptionsFromAThrowingRunnable(){
    	Try.to(()->throwCheckedException());
    }
    
    @Test
    public void oldCanOptionaNotPresentACheckedThrowingSupplier(){
    	Optional<String> s = Try.too(()->{
    		throwCheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isPresent());
    }
    
    @Test
    public void oldCanOptionaNotPresentAnUnCheckedThrowingSupplier(){
    	Optional<String> s = Try.too(()->{
    		throwUncheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isPresent());
    }
    
    @Test
    public void oldCanConvertASupplierIntoAnOptional(){
    	Optional<String> s = Try.too(()->{
    		return "Anything";
		});
    	
    	assertTrue(s.isPresent());
    	assertEquals(s.get(), "Anything");
    }
    
    @Test
    public void canOptionaNotPresentACheckedThrowingSupplier(){
    	Either<Exception, String> s = Try.to(()->{
    		throwCheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isRight());
    	assertTrue(s.isLeft());
    }
    
    @Test
    public void canOptionaNotPresentAnUnCheckedThrowingSupplier(){
    	Either<Exception, String> s = Try.to(()->{
    		throwUncheckedException();
    		return "Anything";
		});
    	
    	assertFalse(s.isRight());
    	assertTrue(s.isLeft());
    }
    
    @Test
    public void canConvertASupplierIntoAnOptional(){
    	Either<Exception, String> s = Try.to(()->{
    		return "Anything";
		});
    	
    	assertTrue(s.isRight());
    	assertFalse(s.isLeft());
    	assertEquals(s.optionRight().get(), "Anything");
    }
    
    
    public void throwCheckedException() throws Exception{
    	throw new Exception("This is a checked exception");
    }
    
    public void throwUncheckedException(){
    	throw new RuntimeException("This is a checked exception");
    }
    
}
