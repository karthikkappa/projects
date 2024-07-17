import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Dijkstra <input file> <output file>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        try {
            File file = new File(inputFileName);
            Scanner input = new Scanner(file);
            PriorityQueue<PqInputs> pq = new PriorityQueue<>();

            int vertices = Integer.parseInt(input.nextLine().trim());
            int sourceVertex = Integer.parseInt(input.nextLine().trim());
            int edges = Integer.parseInt(input.nextLine().trim());

            for (int i = 0; i < edges; i++) {
                String[] vals = input.nextLine().split(" ");
                int firstNode = Integer.parseInt(vals[0]);
                int secondNode = Integer.parseInt(vals[1]);
                int cost = Integer.parseInt(vals[2]);
                pq.add(new PqInputs(firstNode, secondNode, cost));
            }

            FileWriter output = new FileWriter(outputFileName);
            output.write(vertices + "\n");
            while (!pq.isEmpty()) {
                PqInputs outputs = pq.poll();
                output.write(outputs.firstNode + " " + outputs.secondNode + " " + outputs.cost + "\n");
            }

            output.close();
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + inputFileName);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the output file.");
        }
    }

    static class PqInputs implements Comparable<PqInputs> {
        int firstNode;
        int secondNode;
        int cost;

        public PqInputs(int firstNode, int secondNode, int cost) {
            this.firstNode = firstNode;
            this.secondNode = secondNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(PqInputs i) {
            return Integer.compare(this.cost, i.cost);
        }
    }
}
