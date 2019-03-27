package com.vendavo.interview.thermometer.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.provider.Arguments;

import com.vendavo.interview.thermometer.Color;

class ColorIntervalTest {

	private static List<Double> reds = Arrays
			.asList(new Double[] { Double.POSITIVE_INFINITY, 999999999.9999d, 40d, 40.000000000000001d, 100d });
	private static List<Double> oranges = Arrays
			.asList(new Double[] { 35d, 39.99999999999999d, 36d, 35.156478643524d });
	private static List<Double> yellows = Arrays
			.asList(new Double[] { 25d, 34.99999999999999d, 27d, 25.156478643524d });
	private static List<Double> greens = Arrays
			.asList(new Double[] { 10d, 24.99999999999999d, 17.989745648d, 21.156478643524d });
	private static List<Double> lightBlues = Arrays
			.asList(new Double[] { 5d, 9.99999999999999d, 6.13485462487, 7.97524132165d });
	private static List<Double> blues = Arrays
			.asList(new Double[] { -10d, 4.99999999999999d, 0d, -9.99999999999999, -5.154895165431d });
	private static List<Double> darkBlues = Arrays.asList(
			new Double[] { -10.000000000000001d, -11d, -99999999d, -467862134.5648942, Double.NEGATIVE_INFINITY });

	private static Map<Color, List<Double>> testValues = new HashMap<>();

	@BeforeAll
	static void setup() {
		testValues.put(Color.RED, reds);
		testValues.put(Color.ORANGE, oranges);
		testValues.put(Color.YELLOW, yellows);
		testValues.put(Color.GREEN, greens);
		testValues.put(Color.LIGHT_BLUE, lightBlues);
		testValues.put(Color.BLUE, blues);
		testValues.put(Color.DARK_BLUE, darkBlues);
	}

	/**
	 * Generate both positive and negative arguments for test case for given color
	 * 
	 * @param intervalColor color
	 * @return
	 */
	private Stream<Arguments> provideArgumentsForInterval(Color intervalColor) {
		Stream<Arguments> positiveArguments = providePositiveArgumentsForInterval(intervalColor);

		Stream<Arguments> otherArguments = provideNegativeArgumentsForInterval(intervalColor);

		return Stream.of(positiveArguments, otherArguments).flatMap(a -> a);
	}

	private Stream<Arguments> providePositiveArgumentsForInterval(Color intervalColor) {
		return testValues.get(intervalColor).stream().map(value -> Arguments.of(value, true));
	}

	private Stream<Arguments> provideNegativeArgumentsForInterval(Color intervalColor) {
		Map<Color, List<Double>> negativeStreams = new HashMap<>(testValues);
		negativeStreams.remove(intervalColor);

		return negativeStreams.values().parallelStream().map(list -> list.stream()).flatMap(a -> a)
				.map(value -> Arguments.of(value, false));
	}

	/**
	 * Dynamically generate tests for all color intervals
	 * 
	 * @return dynamic test collection
	 */
	@TestFactory
	Collection<DynamicTest> colorIntervalsTests() {
		Collection<DynamicTest> tests = new ArrayList<>();

		for (ColorInterval interval : ColorInterval.values()) {

			provideArgumentsForInterval(interval.getColor()).forEach(args -> {
				tests.add(dynamicTest(generateTestName(interval, args), () -> assertEquals(args.get()[1],
						interval.belongsToInterval((Double) args.get()[0]))));
			});

		}

		return tests;
	}
	
	/**
	 * Test name generation
	 * 
	 * @param interval color interval
	 * @param args test arguments
	 * @return
	 */
	private String generateTestName(ColorInterval interval, Arguments args) {
		return interval.name() + " - " + args.get()[0] + " = " + args.get()[1];
	}

	/**
	 * Check all intervals return the proper color
	 */
	@Test
	public void testColors() {
		Assertions.assertAll(() -> assertEquals(Color.RED, ColorInterval.RED.getColor()),
				() -> assertEquals(Color.ORANGE, ColorInterval.ORANGE.getColor()),
				() -> assertEquals(Color.YELLOW, ColorInterval.YELLOW.getColor()),
				() -> assertEquals(Color.GREEN, ColorInterval.GREEN.getColor()),
				() -> assertEquals(Color.LIGHT_BLUE, ColorInterval.LIGHT_BLUE.getColor()),
				() -> assertEquals(Color.BLUE, ColorInterval.BLUE.getColor()),
				() -> assertEquals(Color.DARK_BLUE, ColorInterval.DARK_BLUE.getColor()));
	}

}
