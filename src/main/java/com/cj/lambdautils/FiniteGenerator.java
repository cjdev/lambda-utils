package com.cj.lambdautils;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class FiniteGenerator<T> {
	private final Supplier<Optional<T>> supplier;
	private Optional<T> nextValue; //This variable makes the class not thread safe.
	
	
	public FiniteGenerator(Supplier<Optional<T>> supplier) {
		this.supplier = supplier;
		this.nextValue = supplier.get();
	}
	
	public Stream<T> stream(){
		Iterator<T> iterator = new IteratorBuilder<T>()
				.withHasNext(()->nextValue.isPresent())
				.withNext(()->{
					Optional<T> returnValue = nextValue;
					nextValue = supplier.get();
					return returnValue.get();
				}).iterator();
		
		//Here, we don't use the convenience in IteratorBuilder because we want to be absolutely sure that the
		//Stream is NOT parallel because this class is not thread safe.
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
	}
}
