package org.example;

import org.example.ActionType;

public class SortingAction {
    private ActionType type;
    private int index1;
    private int index2;
    private String description;

    public SortingAction(ActionType type, int index1, int index2, String description) {
        this.type = type;
        this.index1 = index1;
        this.index2 = index2;
        this.description = description;
    }

    public ActionType getType() {
        return type;
    }

    public int getIndex1() {
        return index1;
    }

    public int getIndex2() {
        return index2;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", index1=" + index1 +
                ", index2=" + index2 +
                '}';
    }
}
