package main.com.sudoku.model;

public class SudokuBoard {
    private static final int SIZE = 9;
    private Cell[][] board;

    public SudokuBoard() {
        board = new Cell[SIZE][SIZE];
        initializeEmptyBoard();
    }

    private void initializeEmptyBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new Cell(0, false);
            }
        }
    }

    public void setCell(int row, int col, int value, boolean isFixed) {
        board[row][col] = new Cell(value, isFixed);
    }

    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isValidMove(int row, int col, int num) {
        // Verifica linha e coluna
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i].getValue() == num || board[i][col].getValue() == num) {
                return false;
            }
        }

        // Verifica subgrade 3x3
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j].getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col].getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void clearUserInputs() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (!board[row][col].isFixed()) {
                    board[row][col].setValue(0);
                }
            }
        }
    }
}