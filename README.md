# PageRank Simulator (Markov Chains & Eigenvalues)

A Java program that demonstrates how **Markov chains** and **eigen decomposition** can be used to compute the steady-state probabilities of a random web surfer (a scaled-down version of Google’s **PageRank algorithm**).

## Features
- Builds a random **10×10 Markov transition matrix** (each column sums to 1).
- Lets the user choose:
  - A **starting web page** (initial state vector).
  - A number of **steps (k)** to “click through”.
- Computes probabilities after *k* steps using the formula:
  \[
    u_k = \sum_i c_i \lambda_i^k x_i
  \]
  where \(x_i\) are eigenvectors and \(\lambda_i\) are eigenvalues.
- Prints the **probability distribution** across all pages.
- Highlights the **most likely page** after *k* steps.
- Shows the **steady-state distribution** (the eigenvector with λ ≈ 1).

## Concepts Covered
- Markov matrices and random walks
- Eigenvalues and eigenvectors
- Eigen decomposition for efficient computation of \(A^k u_0\)
- Steady state (stationary distribution)
- Application to **PageRank** as a real-world use of linear algebra

## Technologies
- **Java**
- [JAMA](http://math.nist.gov/javanumerics/jama/) (Java Matrix Package)

## Usage
Compile and run:
```bash
javac -cp .:jama.jar PageRank.java
java -cp .:jama.jar PageRank
