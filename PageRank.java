// Mar's code
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;

public class PageRank {

   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      Random rand = new Random();

      int size = 10;
      double[][] markov = new double[size][size];

      // Define a Markov matrix
      for (int col = 0; col < size; col++) {
         double colSum = 0.0;
         for (int row = 0; row < size; row++) {
            markov[row][col] = rand.nextDouble();
            colSum += markov[row][col];
         }
         for (int row = 0; row < size; row++) {
            markov[row][col] /= colSum;
         }
      }

      Matrix A = new Matrix(markov);

      // Perform eigen decomposition once
      EigenvalueDecomposition eig = new EigenvalueDecomposition(A);
      Matrix V = eig.getV();
      Matrix inverseV = V.inverse();
      double[] lambda = eig.getRealEigenvalues();

      Matrix[] eigenvectors = new Matrix[V.getColumnDimension()];
      for (int i = 0; i < V.getColumnDimension(); i++) {
         eigenvectors[i] = V.getMatrix(0, V.getRowDimension() - 1, i, i);
      }

      // Main loop
      while (true) {
         // Ask for number of steps
         System.out.print("\nHow many links will you click through: ");
         int k = scanner.nextInt();

         // Ask for initial state
         double[][] vector = new double[size][1];
         System.out.print("Which web page are you starting from (1-10): ");
         int webpageNum = scanner.nextInt();
         vector[webpageNum - 1][0] = 1.0;

         Matrix initialState = new Matrix(vector);
         Matrix C = inverseV.times(initialState);

         Matrix S = new Matrix(size, 1);
         for (int i = 0; i < size; i++) {
            S = S.plus((eigenvectors[i].times(Math.pow(lambda[i], k))).times(C.get(i, 0)));
         }

         // Print results
         double maxProb = -1.0;
         int maxIndex = -1;
         for (int i = 0; i < size; i++) {
            double prob = S.get(i, 0);
            System.out.printf("Page %d: %.2f%% probability\n", i + 1, prob * 100);
            if (prob > maxProb) {
               maxProb = prob;
               maxIndex = i;
            }
         }

         System.out.printf("\nYou will most likely end up on Page %d with %.2f%% probability.\n",
               maxIndex + 1, maxProb * 100);

         // Ask if user wants to continue
         System.out.print("Try again? (yes/no): ");
         scanner.nextLine(); // clear newline
         String response = scanner.nextLine();
         if (!response.equalsIgnoreCase("yes")) {
            break;
         }
      }
      // Normalized steady state for comparison
      Matrix columnVector = V.getMatrix(0, V.getRowDimension() - 1, 0, 0);
      double sum = 0.0;
      for (int i = 0; i < columnVector.getRowDimension(); i++) {
          sum += columnVector.get(i, 0);
      }
      
      // Normalize the vector
      for (int i = 0; i < columnVector.getRowDimension(); i++) {
          columnVector.set(i, 0, columnVector.get(i, 0) / sum);
      }
      System.out.println("Normalized Steady State:");
      columnVector.print(6, 4);


      
      scanner.close();
   }
}