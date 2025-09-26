# Random Surfer PageRank (Java)

Simulates a random web surfer over a 10-node graph using a column-stochastic transition matrix.
Given a start page and k clicks, computes the distribution x_k = A^k x_0 via eigen-decomposition (A = VΛV^-1),
and estimates the stationary distribution using both the λ≈1 eigenvector and power iteration.
Includes an optional damping factor (α=0.85), i.e., G = αA + (1-α)11^T/n.

## Why
Demonstrates Markov chains, eigenvalues/eigenvectors, and the intuition behind PageRank.

## How to run
- Java 8+ and JAMA (http://math.nist.gov/javanumerics/jama/), or include JAMA jar on classpath.
- `javac -cp .:jama.jar PageRank.java`
- `java  -cp .:jama.jar PageRank`

## Features
- Generates a random column-stochastic matrix (or plug in your own).
- k-step prediction: x_k = A^k x_0 using eigendecomposition.
- Steady-state via:
  - eigenvector with eigenvalue ≈ 1 (normalized, non-negative),
  - power iteration (robust).
- Optional damping (α=0.85) for PageRank-style teleportation.
