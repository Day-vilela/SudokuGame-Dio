package main.com.sudoku.view;

import javax.swing.*;
import java.awt.*;

public class NumberButton extends JButton {
    private int number;

    public NumberButton(int number) {
        super(String.valueOf(number));
        this.number = number;
        setPreferredSize(new Dimension(50, 50));
        setFont(new Font("Arial", Font.BOLD, 20));
    }

    public int getNumber() {
        return number;
    }
}