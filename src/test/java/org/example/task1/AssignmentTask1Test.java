package org.example.task1;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AssignmentTask1Test {
        @Test
        public void testConvertIntoRangeForDices() {
            // Create a test input map
            Map<Integer, Integer> inputMap = new HashMap<>();
            inputMap.put(1, 10);
            inputMap.put(2, 30);
            inputMap.put(3, 15);
            inputMap.put(4, 15);
            inputMap.put(5, 30);
            inputMap.put(6, 0);

            // Call the conversion method
            Map<Integer, AssignmentTask1.Range> rangeMap = AssignmentTask1.convertIntoRange(inputMap);

            // Check the size of the resulting range map
            assertEquals(5, rangeMap.size());

            // Check the values of specific ranges
            assertTrue(rangeMap.get(1).start==1 && rangeMap.get(1).end==10);
            assertTrue(rangeMap.get(2).start==11 && rangeMap.get(2).end==40);
            assertTrue(rangeMap.get(3).start==41 && rangeMap.get(3).end==55);
            assertTrue(rangeMap.get(4).start==56 && rangeMap.get(4).end==70);
            assertTrue(rangeMap.get(5).start==71 && rangeMap.get(5).end==100);
        }

    @Test
    public void testConvertIntoRangeForCoin() {
        // Create a test input map
        Map<Integer, Integer> inputMap = new HashMap<>();
        inputMap.put(1, 35);
        inputMap.put(2, 65);

        // Call the conversion method
        Map<Integer, AssignmentTask1.Range> rangeMap = AssignmentTask1.convertIntoRange(inputMap);

        // Check the size of the resulting range map
        assertEquals(2, rangeMap.size());

        // Check the values of specific ranges
        assertTrue(rangeMap.get(1).start==1 && rangeMap.get(1).end==35);
        assertTrue(rangeMap.get(2).start==36 && rangeMap.get(2).end==100);
    }

}