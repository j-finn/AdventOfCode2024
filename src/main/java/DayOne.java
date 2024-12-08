package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DayOne {

    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        var dayOne = new DayOne();
        dayOne.splitInputsAndSort();

        System.out.println("Part One Solution: " + dayOne.solvePartOne());
        System.out.println("Part Two Solution: " + dayOne.solvePartTwo());
    }


    /**
     * Takes list of inputs like:
     *  12823   12823
     *  74540   88907
     *  and splits them into a left and right list of integers.
     */
    private void splitInputsAndSort() {
        List<String> inputLines =  FileUtils.loadContent(1);

        for (String line: inputLines) {
            // remove all spaces
            String[] parsedLine =  line.split("\\s+");

            leftList.add(Integer.parseInt(parsedLine[0]));
            rightList.add(Integer.parseInt(parsedLine[1]));
        }

        // Sort each list
        Collections.sort(leftList);
        Collections.sort(rightList);
    }


    /**
     * Find absolute differences between corresponding items in each list and sum them.
     */
    private int solvePartOne() {
        int sumOfAbsDifference = 0;

        if (leftList.size() != rightList.size()) {
            throw new IllegalStateException("Left and right lists are not the same size.");
        }

        for (int i = 0; i < leftList.size(); i++) {
            sumOfAbsDifference += Math.abs(leftList.get(i) - rightList.get(i));
        }

        return sumOfAbsDifference;
    }


    /**
     * Calculate similarity score for all elements i in
     * left list, then return the sum of all similarity scores.
     *
     * Similarity score of i = i * number of times i appears in right list
     */
    private int solvePartTwo() {

        // Map<Left list element, Similarity score of element>
        HashMap<Integer, Integer> similarityScoreOfEach = new HashMap<>();

        for (Integer leftListInt: leftList) {

            // if a number has already been encountered -> double its count
            if (similarityScoreOfEach.containsKey(leftListInt)) {
                similarityScoreOfEach.compute(leftListInt, (k, count) -> count * 2);
            } else {
                // count num of occurrences in right list
                int count = 0;
                for (Integer rightListInt: rightList) {
                    if (leftListInt.equals(rightListInt)) {
                        count++;
                    }

                    similarityScoreOfEach.put(leftListInt, count * leftListInt);
                }
            }
        }

        int totalCount = 0;

        for (int count: similarityScoreOfEach.values()) {
            totalCount += count;
        }

        return totalCount;
    }
}