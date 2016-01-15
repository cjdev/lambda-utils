package com.cj.lambdautils;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class TryOptionalTest {
    @Test
    public void TryYieldsOptional() throws Exception {


        assertEquals(Try.with(() -> 1 / 1), Optional.of(1));

        assertTrue(Try.with(() -> new Object()).isPresent());

        assertFalse(
                Try.withThrowing((ThrowingSupplier<Integer>) () -> {
                    throw new Exception("boom");
                }).isPresent());

        assertFalse(Try.withThrowing(() -> 1 / 0).isPresent());
    }
}
