package com.gederin.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

	private MinPQ<Node> minPQ;
	private int move = 0;
	private Queue<Board> solutionRoad;
	private boolean solvable;

	public Solver(Board initial) {
		this.minPQ = new MinPQ<Node>();
		this.solutionRoad = new Queue<Board>();
		Node rootNode = new Node(initial, null);
		/**
		 * Add current node to the MinPQ
		 */
		this.minPQ.insert(rootNode);
		solvable = this.algorithm();
	}

	public boolean isSolvable() {
		return this.solvable;
	}

	public int moves() {
		if (solvable) {
			return this.move;
		} else {
			return -1;
		}
	}

	public Iterable<Board> solution() {
		if (solvable) {
			return this.solutionRoad;
		} else {
			return null;
		}
	}

	private class Node implements Comparable<Node> {
		private Board board;
		private Node previous;

		public Node(Board board, Node previous) {
			super();
			this.board = board;
			this.previous = previous;
		}


		public int compareTo(Node o) {
			if (this.board.manhattan() > o.board.manhattan()) {
				return 1;
			}
			if (this.board.manhattan() < o.board.manhattan()) {
				return -1;
			}
			return 0;
		}
	}

	private boolean algorithm() {
		try {
			/**
			 * Delete Node with min Manhattan value and check for goal
			 */
			Node minNode = this.minPQ.delMin();
			this.solutionRoad.enqueue(minNode.board);

			if (minNode.board.isGoal()) {
				return true;
			}

			for (Board neighborBoard : minNode.board.neighbors()) {
				if (minNode.previous == null || !minNode.previous.board.equals(neighborBoard)){
					Node neighborNode = new Node(neighborBoard, minNode);
					this.minPQ.insert(neighborNode);
				}
			}
			move++;
			return algorithm();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		// create initial board from file
		In in = new In("src/main/resources/8puzzle-testing/8puzzle/puzzle4x4-30.txt");
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}
}
