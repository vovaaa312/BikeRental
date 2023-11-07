package org.example.data;

public enum Color {

    WHITE(java.awt.Color.WHITE, "white"),
    BLACK(java.awt.Color.BLACK, "black"),
    RED(java.awt.Color.RED, "red"),
    GREEN(java.awt.Color.GREEN, "green"),
    BLUE(java.awt.Color.BLUE, "blue");

    private final java.awt.Color color;
    private final String name;

    Color(java.awt.Color _color, String _name) {
        this.color = _color;
        this.name = _name;
    }

    public java.awt.Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
