package com.cj.lambdautils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ConsumerMatcherTest {
	class TestClass{
		final String stringField;
		@Override public String toString() {return "TestClass [stringField=" + stringField + "]";}
		public TestClass(String string) {this.stringField = string;}
		public TestClass() {this.stringField="String";}
		
		
	}
	
	@Test
	public void canMatchWithAConsumer(){
		assertThat(new TestClass(), ConsumerMatcher.asserting((c)->assertTrue(c.stringField.equals("String"))));
	}

	@Test(expected=AssertionError.class)
	public void canFailWithAConsumer(){
		assertThat(new TestClass(), ConsumerMatcher.asserting((c)->assertTrue(c.stringField.equals("bad string"))));
	}
	
	@Test
	public void canUseAssertingToFindThingsInsideOfLists(){
		List<TestClass> set = Arrays.asList(new TestClass("A String"), new TestClass("Different String"));
		
		assertThat(set, hasItem(ConsumerMatcher.<TestClass>asserting((c)->assertEquals(c.stringField,("A String")))));
		
		assertThat(set, hasItem(ConsumerMatcher.<TestClass>asserting((c)->assertEquals(c.stringField,("Different String")))));
	}
}
