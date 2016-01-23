package com.cj.lambdautils;

import java.util.Optional;
import java.util.function.Supplier;

public class Try {    

	public static void to(ThrowingRunnable function) {
        try {
            function.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	public static <T> Optional<T> to(ThrowingSupplier<T> fn) {
		try {
            return Optional.ofNullable(fn.supply());
        } catch (Exception e) {
            return Optional.empty();
        }
	}	
}
