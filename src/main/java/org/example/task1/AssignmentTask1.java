package org.example.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AssignmentTask1 {
    static class Range {
        int start, end;
        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        // Map to represent input data with key-value as numbers and their probabilities
        Map<Integer, Integer> inputMap = new HashMap<>();
        inputMap.put(1, 10);
        inputMap.put(2, 30);
        inputMap.put(3, 15);
        inputMap.put(4, 15);
        inputMap.put(5, 30);
        inputMap.put(6, 0);

        // Convert the input data into a map of ranges
        Map<Integer, Range> rangeMap = convertIntoRange(inputMap);

        // Create a map to count the occurrences of each range
        Map<Integer, Integer> occurrenceMap = new HashMap<>();

        // Simulate 1000 random iterations and count occurrences within the ranges
        for (int i = 0; i < 1000; i++) {
            int rNum = (int) (Math.random() * 100) + 1; // Generate a random number between 1 and 100
            for (Map.Entry<Integer, Range> entry : rangeMap.entrySet()) {
                Range r = entry.getValue();
                if (rNum >= r.start && rNum <= r.end) {
                    // Increment the occurrence count for the corresponding range
                    occurrenceMap.put(entry.getKey(), 1 + occurrenceMap.getOrDefault(entry.getKey(), 0));
                }
            }
        }

        // Print the occurrence count for each range
        System.out.println(occurrenceMap);
    }

    public static Map<Integer, Range> convertIntoRange(Map<Integer, Integer> inputMap) {
        Map<Integer, Range> rangeMap = new HashMap<>();
        int min = 0;
        for (Map.Entry<Integer, Integer> entry : inputMap.entrySet()) {
            if (entry.getValue() == 0) {
                continue; // Skip ranges with a value of 0
            }
            Range range = new Range(min+1, min + entry.getValue());
            rangeMap.put(entry.getKey(), range);
            min += entry.getValue();
        }
        return rangeMap;
    }
}
