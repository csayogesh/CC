package hckrnk;

import java.util.HashMap;
import java.util.Map;

public class CarsShuffle {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, -1};
        int[] expected = {3, -1, 2, 1};
        process(input, expected);
        /*
            Run time complexity : O(n)
            Proof:
                1. For every element at incorrect position moveCarToCorrectPosition this method will be called once
                2. There can be at most 'n' incorrect elements so the run time can be at most O(n)
                3. Lower bound on the algorithm is O(n) as algorithm needs to see number of elements at incorect position.

            Correctness Proof:
                1. For each of the element moveCarToCorrectPosition method will correct position for that element
                2. And if we correct position of each element iteratively. We are guaranteed to get the correct
                    overall configuration at the end.
         */
    }

    private static void process(int[] input, int[] expected) {
        Map<Integer, Integer> correctPositions = new HashMap<>();
        Map<Integer, Integer> currentPositions = new HashMap<>();
        for (int i = 0; i < expected.length; i++) {
            correctPositions.put(expected[i], i);
            currentPositions.put(input[i], i);
        }
        for (int i = 0; i < input.length; i++) {
            while (correctPositions.get(input[i]) != i) {
                moveCarToCorrectPosition(i, input, correctPositions, currentPositions);
            }
        }
    }

    private static void moveCarToCorrectPosition(int curCarIndex, int[] input,
                                                 Map<Integer, Integer> correctPositions,
                                                 Map<Integer, Integer> currentPositions) {
        int curCarId = input[curCarIndex];
        int curCorrectIndex = correctPositions.get(curCarId);
        int destCarId = input[curCorrectIndex];
        if (destCarId == -1 || curCarId == -1) {
            swap(curCarIndex, curCorrectIndex, input, currentPositions);
        } else {
            int currentEmptyIndex = currentPositions.get(-1);
            swap(curCarIndex, currentEmptyIndex, input, currentPositions);
            swap(curCorrectIndex, curCarIndex, input, currentPositions);
            swap(currentEmptyIndex, curCorrectIndex, input, currentPositions);
        }
    }

    private static void swap(int sourceIndex, int destIndex, int[] input, Map<Integer, Integer> currentPositions) {
        System.out.println("Swap called");
        int temp = input[sourceIndex];
        input[sourceIndex] = input[destIndex];
        input[destIndex] = temp;
        currentPositions.put(input[sourceIndex], sourceIndex);
        currentPositions.put(input[destIndex], destIndex);
    }
}
