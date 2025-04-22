package main.com.sudoku.controller;

import main.com.sudoku.model.*;
import main.com.sudoku.view.*;

import javax.swing.*;

public class SudokuController {
    private SudokuFrame view;
    private SudokuBoard model;
    private SudokuGenerator generator;
    public SudokuController(SudokuFrame view) {
        this.view = view;
        this.generator = new SudokuGenerator();
        
        // Inicia um novo jogo
        newGame(1); // Dificuldade média por padrão
        
        // Configura listeners
        setupListeners();
    }

    private void setupListeners() {
        // para novo jogo
        view.getNewGameButton().addActionListener(e -> {
            int difficulty = view.getDifficultyCombo().getSelectedIndex() + 1;
            newGame(difficulty);
        });
        
        // para verificar
        view.getCheckButton().addActionListener(e -> checkSolution());
        
        // para limpar
        view.getClearButton().addActionListener(e -> clearUserInputs());
        
        // para células
        GamePanel gamePanel = view.getGamePanel();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final int r = row;
                final int c = col;
                gamePanel.setCellListener(row, col, e -> {
                    handleCellInput(r, c);
                });
            }
        }
    }

 // alteração no método newGame:
    private void newGame(int difficulty) {
        new SwingWorker<SudokuBoard, Void>() {
            @Override
            protected SudokuBoard doInBackground() throws Exception {
                // Executa em background
                return generator.generatePuzzle(difficulty);
            }

            @Override
            protected void done() {
                try {
                    model = get(); // Obtém o resultado
                    view.getGamePanel().setBoard(model);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(view, 
                        "Erro ao gerar o tabuleiro: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    private void handleCellInput(int row, int col) {
        if (model.getCell(row, col).isFixed()) {
            return;
        }
        
        int value = view.getGamePanel().getSelectedValue(row, col);
        
        if (value < 0 || value > 9) {
            JOptionPane.showMessageDialog(view, "Por favor, insira um número entre 1 e 9");
            view.getGamePanel().getSelectedValue(row, col);
            return;
        }
        
        if (value != 0) {
            boolean isValid = model.isValidMove(row, col, value);
            model.getCell(row, col).setValid(isValid);
            model.getCell(row, col).setValue(value);
        } else {
            model.getCell(row, col).setValue(0);
            model.getCell(row, col).setValid(true);
        }
        
        view.getGamePanel().updateView();
    }

    private void checkSolution() {
        boolean isComplete = true;
        boolean allValid = true;
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Cell cell = model.getCell(row, col);
                if (cell.getValue() == 0) {
                    isComplete = false;
                    cell.setValid(false);
                } else if (!cell.isFixed()) {
                    boolean isValid = model.isValidMove(row, col, cell.getValue());
                    cell.setValid(isValid);
                    if (!isValid) {
                        allValid = false;
                    }
                }
            }
        }
        
        view.getGamePanel().updateView();
        
        if (!allValid) {
            JOptionPane.showMessageDialog(view, "Existem números inválidos no tabuleiro!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (isComplete) {
            JOptionPane.showMessageDialog(view, "Parabéns! Você resolveu o Sudoku!", "Vitória", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "O tabuleiro está correto até agora, mas ainda há células vazias.", "Status", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void clearUserInputs() {
        model.clearUserInputs();
        view.getGamePanel().updateView();
    }
}