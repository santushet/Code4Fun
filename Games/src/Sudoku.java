import java.util.*;

public class Sudoku {
	static int[][] array = { { 1, 0, 0, 2, 0, 0, 0, 0, 0 },
			{ 3, 2, 0, 0, 8, 0, 0, 4, 1 }, { 0, 0, 4, 0, 0, 0, 2, 9, 0 },
			{ 0, 0, 0, 1, 7, 0, 0, 0, 0 }, { 0, 6, 8, 0, 0, 0, 1, 3, 0 },
			{ 0, 0, 0, 0, 3, 2, 0, 0, 0 }, { 0, 9, 1, 0, 0, 0, 8, 0, 0 },
			{ 2, 4, 0, 0, 6, 0, 0, 1, 3 }, { 0, 0, 0, 0, 0, 4, 0, 0, 5 } };
	static int size = 3;

	public static void main(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		solveSudoku();
		displayMatrix(array);
		 displayEachBlock(array);
	}

	private static boolean solveSudoku() {
		int[] temp = findUnassigned(array);
		if (temp != null) {
			int row = temp[0];
			int col = temp[1];
			for (int i = 1; i <= array.length; i++) {
				if (isSafe(array, row, col, i)) {
					array[row][col] = i;
					if (solveSudoku())
						return true;
					array[row][col] = 0;
				}
			}
		} else
			return true;
		return false;
	}

	private static int[] findUnassigned(int[][] array) {
		int[] t = new int[2];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] == 0) {
					t[0] = i;
					t[1] = j;
					return t;
				}
			}
		}
		return null;
	}

	private static boolean isSafe(int[][] array, int row, int col, int n) {
		return checkRow(array, row, col, n) && checkCol(array, row, col, n)
				&& checkBox(array, row, col, n);
	}

	private static boolean checkRow(int[][] array, int row, int col, int num) {
		for (int i = 0; i < array.length; i++) {
			if (array[row][i] == num)
				return false;
		}
		return true;
	}

	private static boolean checkCol(int[][] array, int row, int col, int n) {
		for (int i = 0; i < array.length; i++) {
			if (array[i][col] == n)
				return false;
		}
		return true;
	}

	private static boolean checkBox(int[][] array, int row, int col, int n) {
		row = row - (row % size);
		col = col - (col % size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (array[i + row][j + col] == n)
					return false;
			}

		}
		return true;
	}

	private static void displayMatrix(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			/*
			 * if (i == 3 || i == 6)
			 * System.out.println("------------------------------------");
			 */
			for (int j = 0; j < array[i].length; j++) {
				System.out.format("%-3s", array[i][j]);
				/*
				 * if (j == 2 || j == 5 || j == 8) System.out.print(" | ");
				 */
			}
			System.out.println();
		}
		System.out.println("------------------------------------");
	}

	private static void displayEachBlock(int[][] array) {
		for (int i = 0; i < array.length; i += 3) {
			for (int j = 0; j < array[i].length; j += 3) {
				/*
				 * Here we are finding which block we are standing at.
				 */
				int block = (((i / 3) * 3) + (j / 3));
				System.out.println("Block : " + block);
				int[][] newArray = new int[3][3];
				int newRow = 0;
				for (int k = i; k < (i + 3); k++) {
					int newColumn = 0;
					for (int l = j; l < (j + 3); l++) {
						// This is where you are getting your array inside the
						// given block.
						newArray[newRow][newColumn] = array[k][l];
						System.out.format("[%-1s][%-1s] : %-3s ", newRow,
								newColumn, newArray[newRow][newColumn++]);
					}
					newRow++;
					System.out.println();
				}
				// Here you can send your newArray for VALIDATION, thingy.
				// So that we can move on to the next Block for further
				// processing.
			}
		}
	}
}