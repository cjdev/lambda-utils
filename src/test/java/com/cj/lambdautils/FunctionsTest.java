package com.cj.lambdautils;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.Test;

public class FunctionsTest {
	
	@Test
	public void canCreateAFiniteLazySequenceStreamWithNothingInIt(){
		Supplier<Optional<Integer>> supplier = ()->Optional.empty();
		List<Integer> ints = Functions.streamOf(supplier).collect(Collectors.toList());
		assertEquals(ints.size(), 0);
	}
	
	@Test
	public void canCreateAFiniteLazySequenceStream(){
		Supplier<Optional<Integer>> supplier = new Supplier<Optional<Integer>>(){
			int state = 5;
			@Override public Optional<Integer> get() {
				if(state>0)return Optional.of(state--);
				return Optional.empty();
			}
		};
		List<Integer> ints = Functions.streamOf(supplier).collect(Collectors.toList());
		
		assertEquals(ints.get(0).intValue(), 5);
		assertEquals(ints.get(1).intValue(), 4);
		assertEquals(ints.get(2).intValue(), 3);
		assertEquals(ints.get(3).intValue(), 2);
		assertEquals(ints.get(4).intValue(), 1);
		assertEquals(ints.size(), 5);
	}
	


}
