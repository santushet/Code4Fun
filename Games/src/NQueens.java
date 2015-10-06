class NQueens {

	private static int[][] board = new int[4][4];
	private static int size = 4;

	void displayArr() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// board[i][j]=0;
				System.out.println(board[i][j]);
			}
		}

	}

	boolean gameStart(int[][] board, int k) {
		if (k >= size)
			return true;
		for (int i = 0; i < size; i++) {
			if (isSafe(board, i, k)) {
				board[i][k] = 1;
				if (gameStart(board, k + 1))
					return true;
				board[i][k] = 0;
			}
		}

		return false;
	}

	boolean isSafe(int[][] board, int row, int col) {
		for (int i = 0; i < col; i++) {
			if ((board[row][i]) == 1)
				return false;
		}

		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1)
				return false;
		}

		for (int i = row, j = col; i < size && j >= 0; i++, j--) {
			if (board[i][j] == 1)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		NQueens nq = new NQueens();
		System.out.println("printing input before game starts \n");
		nq.displayArr();
		boolean result = nq.gameStart(board, 0);
		System.out.println("output after game ends \n");
		if (result == true)
			System.out.println("solution exists");
		else {
			System.out.println("solution does not exist\n");
		}
		nq.displayArr();

	}

}