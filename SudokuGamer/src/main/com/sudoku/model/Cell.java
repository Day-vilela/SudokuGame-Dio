package main.com.sudoku.model;

public class Cell {
    private int value;
    private boolean isFixed;
    private boolean isValid;

    public Cell(int value, boolean isFixed) {
        this.value = value;
        this.isFixed = isFixed;
        this.isValid = true;
    }

    // Getters e Setters
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}