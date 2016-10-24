package com.gederin.puzzle;

import edu.princeton.cs.algs4.Stack;

/**
 * Class which is represent puzzle board <br>
 * 
 * Program should work with 2 <= N < 128
 * 
 * @author Ruslan
 * 
 */
public class Board {
	/**
	 * Game blocks
	 */
	private int[][] blocks;

	/**
	 * Dimension
	 */
	private int N;

	/**
	 * Construct puzzle board
	 * 
	 * @param blocks
	 *            Integer array with blocks
	 */
	public Board(int[][] blocks) {
		try {
			this.N =  blocks[0].length;
			this.blocks = new int[this.N][this.N];

			for (int i = 0; i < this.N; i++) {
				for (int j = 0; j < this.N; j++) {
					this.blocks[i][j] = blocks[i][j];
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Return board dimension
	 * 
	 * @return - board dimension
	 */
	public int dimension() {
		return this.N;
	}

	/**
	 * Return number of blocks out of place
	 * 
	 * @return - number of blocks out of place
	 */
	public int hamming() {
		int wrongBlocks = 0;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				/**
				 * 2D --> 1D
				 */
				if (this.blocks[i][j] != 0
						&& this.blocks[i][j] != (i * N + j + 1)) {
					wrongBlocks++;
				}
			}
		}
		return wrongBlocks;
	}

	/**
	 * Return sum of Manhattan distances between blocks and goal
	 * 
	 * @return - sum of Manhattan distances between blocks and goal
	 */
	public int manhattan() {
		int manhattan = 0;
		int targetI = 0, targetJ = 0;
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				/**
				 * 2D --> 1D
				 */
				if (this.blocks[i][j] != 0
						&& this.blocks[i][j] != (i * N + j + 1)) {

					targetI = this.blocks[i][j] / N;
					targetJ = this.blocks[i][j] % N;

					if (targetJ == 0 && targetI != 0) {
						targetI--;
						targetJ = N - 1;
					} else if (targetJ != 0) {
						targetJ--;
					}
					manhattan += (Math.abs(j - targetJ) + Math.abs(i - targetI));
				}
			}
		}
		return manhattan;
	}

	/**
	 * Check if is this board the goal board
	 * 
	 * @return true - if goal board, otherwise - false
	 */
	public boolean isGoal() {
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				/**
				 * 2D --> 1D
				 */
				if (this.blocks[i][j] != 0
						&& this.blocks[i][j] != (i * N + j + 1)) {
					return false;
				}
			}
		}
		return true;
	}

	public Board twin() {
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++) {
			/**
			 * Check left most part of the row
			 */
			if (this.blocks[i][0] != 0 && this.blocks[i][1] != 0) {
				this.swap(this.blocks, i, 0, i, 1);
				break;
			}
			/**
			 * Check the right most part of the row
			 */
			if (this.blocks[i][N - 2] != 0 && this.blocks[i][N - 1] != 0) {
				this.swap(this.blocks, i, N - 2, i,
						N - 1);
				break;
			}
		}
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				blocks[i][j] = this.blocks[i][j];
			}
		}

		return new Board(blocks);
	}

	private void swap(int[][] blocks, int i1, int j1, int i2, int j2) {
		int swap = blocks[i1][j1];
		blocks[i1][j1] = blocks[i2][j2];
		blocks[i2][j2] = swap;
	}

	public boolean equals(Object y) {
		if (y == this) {
			return true;
		}
		if (y == null) {
			return false;
		}
		if (y.getClass() != this.getClass()) {
			return false;
		}
		Board that = (Board) y;
		if (this.N != that.N) {
			return false;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.blocks[i][j] != that.blocks[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public Iterable<Board> neighbors() {
		Stack<Board> stack = new Stack<Board>();

		int[][] temp = new int[this.N][this.N];
		int i0 = -1, j0 = -1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = this.blocks[i][j];
				if (temp[i][j] == 0) {
					i0 = i;
					j0 = j;
				}
			}
		}

		if (this.hasLeftNeighbor(i0, j0)) {
			swap(temp, i0, j0, i0, j0 - 1);
			stack.push(new Board(temp));
			swap(temp, i0, j0 - 1, i0, j0);
		}

		if (this.hasRightNeighbor(i0, j0)) {
			swap(temp, i0, j0, i0, j0 + 1);
			stack.push(new Board(temp));
			swap(temp, i0, j0 + 1, i0, j0);
		}

		if (this.hasTopNeighbor(i0, j0)) {
			swap(temp, i0, j0, i0 - 1, j0);
			stack.push(new Board(temp));
			swap(temp, i0 - 1, j0, i0, j0);
		}

		if (this.hasBottomNeighbor(i0, j0)) {
			swap(temp, i0, j0, i0 + 1, j0);
			stack.push(new Board(temp));
			swap(temp, i0 + 1, j0, i0, j0);
		}

		return stack;
	}

	private boolean hasLeftNeighbor(int i0, int j0) {
		if (j0 != 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasRightNeighbor(int i0, int j0) {
		if (j0 != N - 1) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasTopNeighbor(int i0, int j0) {
		if (i0 != 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasBottomNeighbor(int i0, int j0) {
		if (i0 != N - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * String representation of the board in the output format specified
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		/**
		 * Add dimension
		 */
		stringBuilder.append(this.N + "\n");
		for (int i = 0; i < this.N; i++) {
			for (int j = 0; j < this.N; j++) {
				stringBuilder.append(this.blocks[i][j] + " ");
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
