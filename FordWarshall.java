import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileWriter;

public class FordWarshall {
    public static void main(String[] args) throws IOException {
        File file = new File("input.txt"); 
        Scanner input = new Scanner(file); 

        // getting the information from the input file and storing it as integers
        int vertices = Integer.parseInt(input.nextLine().trim());
        int sourceVertex = Integer.parseInt(input.nextLine().trim());
        int edges = Integer.parseInt(input.nextLine().trim());
        int x = edges * 2;

        // creating arrays for the vertices and costs
        int[] n1 = new int[x];
        int[] n2 = new int[x];
        int[] costs = new int[x];

        // creating an adjacency matrix using # of vertices
        int[][] matrix = new int[vertices][vertices];

        // initialize adjacency matrix to 0 if they are the same position or infinity otherwise
        for (int m = 0; m < vertices; m++) {
            for (int n = 0; n < vertices; n++) {
                if (m == n) {
                    matrix[m][n] = 0;
                } else {
                    matrix[m][n] = Integer.MAX_VALUE;
                }
            }
        }

        // loops through the input and stores the nodes and costs between edges and also adds the values into the arrays/matrix created above
        for (int i = 0; i < edges; i++) {
            String[] vals = input.nextLine().split(" ");
            int firstNode = Integer.parseInt(vals[0]);
            int secondNode = Integer.parseInt(vals[1]);
            int edgeCost = Integer.parseInt(vals[2]);

            // adding values to array
            n1[i] = firstNode;
            n2[i] = secondNode;
            costs[i] = edgeCost;
            n1[i + edges] = secondNode;
            n2[i + edges] = firstNode;
            costs[i + edges] = edgeCost;

            // adding values to the matrix
            matrix[firstNode - 1][secondNode - 1] = edgeCost;
        }

        // creating arrays to manipulate in BF function
        int dist[] = new int[vertices];
        int prev[] = new int[vertices];
        Arrays.fill(prev, -1); // Initialize prev array with -1

        // outputs the content into a new file
        FileWriter output = new FileWriter("BellmanFordAndFloydWarshall.txt");
        FileWriter output2 = new FileWriter("BellmanFordAndFloydWarshallP2.txt");

        BF(n1, n2, costs, dist, prev, sourceVertex);

        // output for the Bellman Ford algorithm
        output.write(vertices + "\n");
        for (int k = 0; k < vertices; k++) {
            output.write((k + 1) + " " + dist[k] + " " + prev[k] + "\n");
        }

        // Perform Floyd-Warshall algorithm
        floydWarshall(matrix, vertices);

        // output for the Floyd-Warshall algorithm
        output2.write(vertices + "\n");
        for (int a = 0; a < vertices; a++) {
            for (int b = 0; b < vertices; b++) {
                if (matrix[a][b] == Integer.MAX_VALUE) {
                    output2.write("oo ");
                } else {
                    output2.write(matrix[a][b] + " ");
                }
            }
            output2.write("\n");
        }

        output.close();
        output2.close();
        input.close();
    }

    // Function that performs the Bellman Ford algorithm
    static void BF(int[] firstNode, int[] secondNode, int[] costs, int[] dist, int[] prev, int sourceVertex) {
        int vertex = dist.length;
        int edge = firstNode.length;

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[sourceVertex - 1] = 0;

        for (int i = 0; i < vertex - 1; i++) { // Relax edges vertex-1 times
            for (int j = 0; j < edge; j++) {
                int a = firstNode[j];
                int b = secondNode[j];
                int res = costs[j];

                // logic for Bellman Ford, adopted from the class notes
                if (dist[a - 1] != Integer.MAX_VALUE && dist[a - 1] + res < dist[b - 1]) {
                    dist[b - 1] = dist[a - 1] + res;
                    prev[b - 1] = a;
                }
            }
        }
    }

    // Function that performs the Floyd-Warshall algorithm
    static void floydWarshall(int[][] matrix, int vertices) {
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE
                            && matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                    }
                }
            }
        }
    }
}
