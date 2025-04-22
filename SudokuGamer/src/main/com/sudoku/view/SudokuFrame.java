package main.com.sudoku.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SudokuFrame extends JFrame {
    private GamePanel gamePanel;
    private JButton newGameButton;
    private JButton checkButton;
    private JButton clearButton;
    private JComboBox<String> difficultyCombo;

    public SudokuFrame() {
        super("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        
        // Painel de controle
        JPanel controlPanel = new JPanel();
        newGameButton = new JButton("Novo Jogo");
        newGameButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        checkButton = new JButton("Verificar");
        clearButton = new JButton("Limpar");
        
        String[] difficulties = {"Fácil", "Médio", "Difícil"};
        difficultyCombo = new JComboBox<>(difficulties);
        
        controlPanel.add(newGameButton);
        controlPanel.add(difficultyCombo);
        controlPanel.add(checkButton);
        controlPanel.add(clearButton);
        
        // Painel do jogo
        gamePanel = new GamePanel();
        
        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public JButton getNewGameButton() {
        return newGameButton;
    }

    public JButton getCheckButton() {
        return checkButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public JComboBox<String> getDifficultyCombo() {
        return difficultyCombo;
    }
}