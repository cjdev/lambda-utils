package com.cj.lambdautils;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

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
