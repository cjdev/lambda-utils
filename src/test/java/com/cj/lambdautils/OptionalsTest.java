package com.cj.lambdautils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class OptionalsTest {
    @Test
    public void canFlattenAnOptional() {
        //Given
        Stream<Optional<Integer>> stream = Arrays.<Optional<Integer>>asList(Optional.empty(), Optional.of(1), Optional.of(2), Optional.empty(), Optional.empty(), Optional.of(3)).stream();

        //When
        List<Integer> result = stream.flatMap(Optionals.nonEmpty()).collect(Collectors.toList());

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(3, result.get(2).intValue());
    }
}
