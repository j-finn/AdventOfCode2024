package main.java;

import java.util.ArrayList;
import java.util.List;

public class DayTwo {

    List<List<Integer>> inputValues;


    public DayTwo() {
        this.inputValues = FileUtils.readFileAsListOfListOfInts(2);
    }


    public static void main(String[] args) {
        var dayTwo = new DayTwo();

        System.out.println("Part One Solution: " + dayTwo.solvePartOne());
        System.out.println("Part Two Solution: " + dayTwo.solvePartTwo());
    }


    /**
     * Check if a list is "safe". This means:
     * <li> Absolute difference between each integer in list is at least 1 and at most 3
     * <li> The list is strictly monotonic
     */
    private boolean isSafe(List<Integer> inputToCheck) {

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 1; i < inputToCheck.size(); i++) {
            int previous = inputToCheck.get(i-1);
            int current = inputToCheck.get(i);

            int absoluteDifference = Math.abs(current - previous);

            // CHECK #1: at least 1, at most 3
            if (absoluteDifference == 0 || absoluteDifference > 3) {
                return false;
            }

            // CHECK #2: Strictly monotonic
            if (current > previous) {
                decreasing = false;
            } else {
                increasing = false;
            }

            if (!increasing && !decreasing) {
                return false;
            }
        }

        return true;
    }


    /**
     * Count number of "safe" reports.
     * See {@link DayTwo#isSafe(List)} for definition of safe.
     */
    private int solvePartOne() {

        int countOfSafeReports = 0;

        for (List<Integer> integers: inputValues) {
            if (isSafe(integers)) {
                countOfSafeReports++;
            }
        }

        return countOfSafeReports;
    }


    /**
     * Check if an unsafe report can be made "safe" by removing a single element.
     * <p>
     * For any unsafe reports, we iterate over the report, removing a single element each time
     * and rechecking if the report is now safe.
     */
    private int solvePartTwo() {
        int countOfSafeReports = 0;

        for (List<Integer> integers: inputValues) {
            if (isSafe(integers)) {
                countOfSafeReports++;
            } else {
                // can we make it safe by removing one element
                for (int i = 0; i < integers.size(); i++) {
                    List<Integer> modifiedList = new ArrayList<>(integers);
                    modifiedList.remove(i);

                    if (isSafe(modifiedList)) {
                        countOfSafeReports++;
                        break;
                    }
                }
            }
        }

        return countOfSafeReports;
    }
}