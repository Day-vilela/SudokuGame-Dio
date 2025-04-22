package main.com.sudoku.view;

import main.com.sudoku.model.Cell;
import main.com.sudoku.model.SudokuBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private static final int SIZE = 9;
    private SudokuBoard board;
    private JTextField[][] cells;

    public GamePanel() {
        setLayout(new GridLayout(SIZE, SIZE));
        setPreferredSize(new Dimension(450, 450)); // Tamanho fixo para melhor visualização
        cells = new JTextField[SIZE][SIZE];
        initializeCells();
    }

    private void initializeCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 20));
                
                // Configuração melhorada
                int top = (row % 3 == 0) ? 2 : 1;
                int left = (col % 3 == 0) ? 2 : 1;
                int bottom = (row == 8) ? 2 : 1;
                int right = (col == 8) ? 2 : 1;
                
                cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                
                cells[row][col] = cell;
                add(cell);
            }
        }
    }
    public void setBoard(SudokuBoard board) {
        this.board = board;
        updateView();
    }

    public void updateView() {
    	
    	if (board == null) return;
    	
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Cell cell = board.getCell(row, col);
                JTextField textField = cells[row][col];
                
                if (cell.getValue() != 0) {
                    textField.setText(String.valueOf(cell.getValue()));
                } else {
                    textField.setText("");
                }
                
                textField.setEditable(!cell.isFixed());
                textField.setBackground(cell.isFixed() ? new Color(240, 240, 240) : Color.WHITE);
                
                if (!cell.isValid()) {
                    textField.setForeground(Color.RED);
                } else {
                    textField.setForeground(cell.isFixed() ? Color.BLUE : Color.BLACK);
                }
            }
        }
    }

    public int getSelectedValue(int row, int col) {
        try {
            return Integer.parseInt(cells[row][col].getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setCellListener(int row, int col, ActionListener listener) {
        cells[row][col].addActionListener(listener);
    }
}