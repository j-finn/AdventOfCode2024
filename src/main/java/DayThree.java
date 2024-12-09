package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {

    List<String> instructionList;


    public static void main(String[] args) {
        var dayThree = new DayThree();

        System.out.println("Part One Solution: " + dayThree.solvePartOne());
        System.out.println("Part Two Solution: " + dayThree.solvePartTwo());
    }


    private int solvePartOne() {
        // extracts the pattern mul(X,Y) into a list
        instructionList = FileUtils.extractPatternFromFileUsingRegex(3, "mul\\(\\d{1,3},\\d{1,3}\\)");

        return completeMultiplicationInstructions();
    }


    private int solvePartTwo() {
        // extracts the patterns: 'mul(X,Y)', 'do()', and 'don't()' into a list
        instructionList = FileUtils.extractPatternFromFileUsingRegex(3, "(mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don\\'t\\(\\))");

        List<String> enabledInstructions = new ArrayList<>();

        boolean active = true; // to begin with

        String DOES_NOT = "don't()";
        String DOES = "do()";

        for (String instruction: instructionList) {

            if (instruction.equals(DOES)) {
                active = true;
            }

            if (instruction.equals(DOES_NOT)) {
                active = false;
            }

            if (active && !instruction.equals(DOES) &&  !instruction.equals(DOES_NOT)) {
                enabledInstructions.add(instruction);
            }
        }

        // set the instruction list
        instructionList = new ArrayList<>(enabledInstructions);

        return completeMultiplicationInstructions();
    }


    /**
     * Must set up {@link DayThree#instructionList} of mul(123,456) beforehand.
     * <p>
     * Extracts the pairs of 1-3 digit numbers and multiply them. Then keep
     * a running total of that.
     *
     * @return the sum
     */
    private int completeMultiplicationInstructions() {
        Pattern pattern = Pattern.compile("\\d{1,3}");
        Matcher matcher;
        int sum = 0;

        // instruction e.g. "mul(123, 456)"
        for (String instruction: instructionList) {
            matcher = pattern.matcher(instruction);

            // first int
            matcher.find();
            int x = Integer.parseInt(matcher.group());

            // second int
            matcher.find();
            int y = Integer.parseInt(matcher.group());

            sum += x * y;
        }
        return sum;
    }
}