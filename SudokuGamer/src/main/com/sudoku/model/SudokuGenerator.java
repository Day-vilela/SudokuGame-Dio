package main.com.sudoku.model;

import java.util.Random;

public class SudokuGenerator {
    private SudokuBoard board;
    private Random random;

    public SudokuGenerator() {
        board = new SudokuBoard();
        random = new Random();
    }

    public SudokuBoard generatePuzzle(int difficulty) {
        fillDiagonalBoxes();
        fillRemaining(0, 3);
        removeNumbers(difficulty);
        return board;
    }

    private void fillDiagonalBoxes() {
        for (int box = 0; box < 9; box += 3) {
            fillBox(box, box);
        }
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = random.nextInt(9) + 1;
                } while (!isValidInBox(row, col, num));
                
                board.setCell(row + i, col + j, num, true);
            }
        }
    }

    private boolean isValidInBox(int startRow, int startCol, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(startRow + i, startCol + j).getValue() == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= 9 && i < 8) {
            i++;
            j = 0;
        }
        if (i >= 9 && j >= 9) {
            return true;
        }
        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < 6) {
            if (j == (i / 3) * 3) {
                j += 3;
            }
        } else {
            if (j == 6) {
                i++;
                j = 0;
                if (i >= 9) {
                    return true;
                }
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (board.isValidMove(i, j, num)) {
                board.setCell(i, j, num, true);
                if (fillRemaining(i, j + 1)) {
                    return true;
                }
                board.setCell(i, j, 0, false);
            }
        }
        return false;
    }

    private void removeNumbers(int difficulty) {
        int cellsToRemove = 0;
        
        switch (difficulty) {
            case 1: cellsToRemove = 40; break; // Fácil
            case 2: cellsToRemove = 50; break; // Médio
            case 3: cellsToRemove = 60; break; // Difícil
            default: cellsToRemove = 50;
        }

        while (cellsToRemove > 0) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            
            if (board.getCell(row, col).getValue() != 0) {
                board.setCell(row, col, 0, false);
                cellsToRemove--;
            }
        }
    }
}
