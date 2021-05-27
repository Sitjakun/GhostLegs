import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt();
        int H = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        String[] topLabels = new String[0];
        String[] botLabels = new String[0];

        // solution map : key = top label, value = bot label
        Map<String, String> solutionValues = new HashMap<>();

        // index of the column associated to a top label
        Map<Integer, String> solutionIndexes = new HashMap<>();
        for (int i = 0; i < H; i++) {
            String line = in.nextLine();
            System.err.println(line);

            if (i == 0) {
                topLabels = line.split("\\s+");
                for (int j = 0; j < topLabels.length; j++) {
                    solutionValues.put(topLabels[j], Integer.toString(j));
                    solutionIndexes.put(j, topLabels[j]);
                }
            }
            if (i == H - 1) {
                botLabels = line.split("\\s+");
            }
            String bridgeString = "--";
            if (line.contains(bridgeString)) {
                int index = line.indexOf(bridgeString);

                while (index >= 0) {

                    // indexes of the "|" columns that are around the bridge
                    int columnIndex = (index - 1) / 3;
                    int nextColumnIndex = (index + 2) / 3;

                    // which top label is associated to the index
                    String x = solutionIndexes.get(columnIndex);
                    String y = solutionIndexes.get(nextColumnIndex);

                    // inversion of the labels associated to index
                    solutionIndexes.put(columnIndex, y);
                    solutionIndexes.put(nextColumnIndex, x);
                    solutionValues.put(y, Integer.toString(columnIndex));
                    solutionValues.put(x, Integer.toString(nextColumnIndex));

                    index = line.indexOf(bridgeString, index + 1);
                }
            }
        }

        // Write an answer using System.out.println()
        // To debug: System.err.println("Debug messages...");
        for (String topLabel : topLabels) {
            System.out.println(topLabel + botLabels[Integer.parseInt(solutionValues.get(topLabel))]);
        }
    }
}
