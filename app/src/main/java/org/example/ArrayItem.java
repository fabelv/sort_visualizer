package org.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ArrayItem {
    private Rectangle item;
    private int value;

    public ArrayItem(int x, int y, int value, int height, int width) {
        this.value = value;

        item = new Rectangle();
        item.setX(x);
        item.setY(y);
        item.setHeight(height);
        item.setWidth(width);

        markNormal();
    }

    public void markPivot() {
        item.setStroke(Color.web("0x00ff80", 1.0));
    }

    public void markNormal() {
        item.setFill(Color.web("0xa7a7a7", 1.0));
        item.setStroke(Color.web("0x262626", 1.0));
    }

    public void markHighlight() {
        item.setFill(Color.web("0x0080ff", 1.0));
    }

    public Rectangle getItem() {
        return item;
    }

    public int getValue() {
        return value;
    }

    public void transitionTo(double x) {
        item.setTranslateX(x);
    }
}
