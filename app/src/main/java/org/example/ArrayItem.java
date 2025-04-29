package org.example;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.Group;

public class ArrayItem {
    private Group group;
    private Rectangle item;
    private Text valueLabel;
    private Text indexLabel;
    private int value;

    public ArrayItem(int index, int x, int y, int value, int height, int width) {
        this.value = value;
        item = new Rectangle();
        item.setX(x);
        item.setY(y);
        item.setHeight(height);
        item.setWidth(width);
        item.setStyle("-fx-border-width: 50;");
        valueLabel = new Text(String.valueOf(value));
        valueLabel.setX(x + width / 2.0 - 5);
        valueLabel.setY(y - 5);
        indexLabel = new Text(String.valueOf(index));
        indexLabel.setX(x + width / 2.0 - 5);
        indexLabel.setY(y + height + 15);
        group = new Group(item, valueLabel, indexLabel);
        markNormal();
    }

    public void markPivot() {
        item.setStroke(Color.web("0xcc0000", 1.0));
        item.setStrokeWidth(3);
    }

    public void markNormal() {
        item.setFill(Color.web("0xa7a7a7", 1.0));
        item.setStroke(Color.web("0x262626", 1.0));
        item.setStrokeWidth(1);
    }

    public void markHighlight() {
        item.setFill(Color.web("0x0080ff", 1.0));
    }

    public void clearHighlightOnly() {
        item.setFill(Color.web("0xa7a7a7", 1.0));
    }

    public Group getGroup() {
        return group;
    }

    public Rectangle getItem() {
        return item;
    }

    public int getValue() {
        return value;
    }

    public void updateX(double x) {
        item.setX(x);
        valueLabel.setX(x + item.getWidth() / 2.0 - 5);
    }
}
