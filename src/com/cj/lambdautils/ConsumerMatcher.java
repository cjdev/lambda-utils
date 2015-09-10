package com.cj.lambdautils;

import java.util.function.Consumer;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class ConsumerMatcher<T> extends TypeSafeMatcher<T>{

    private final Consumer<T> consumer;
    private String error="";

    public ConsumerMatcher(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    /**
     * The intention is that the consumer will throw an assertion exception if t isn't satisified by consumer.  
     * It's like a predicate but this allows multiple tests to have different messages rather than dealing with generic messaging in describeTo. 
     */
    @Override
    protected boolean matchesSafely(T t) {
    	try{
    		consumer.accept(t);
    	}catch(AssertionError assertionError){
    		error = assertionError.getMessage() +":"+ExceptionUtils.getStackTrace(assertionError);
    		return false;
    	}
    	return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Matcher "+error);
    }
    
    /**
     * The provided consumer should contain a set of assertions to run on the test object.
     * Should only be used with "oneOf" style assertions because this is expected to throw an assertionError.
     */
	public static <T> Matcher<T> asserting(Consumer<T> consumer) {
		return new ConsumerMatcher<T>(consumer);
	}
}

