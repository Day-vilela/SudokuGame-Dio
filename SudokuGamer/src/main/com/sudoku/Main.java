package main.com.sudoku;

import main.com.sudoku.controller.SudokuController;
import main.com.sudoku.view.SudokuFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Define o look and feel do sistema para melhor aparência
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            SudokuFrame frame = new SudokuFrame();
            new SudokuController(frame);
            frame.setVisible(true);
            frame.setSize(600, 600); // Tamanho fixo para melhor visualização
        });
    }
}