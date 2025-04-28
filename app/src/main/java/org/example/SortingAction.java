package org.example;

enum ActionType {
    COMPARE,
    SWAP,
    OVERWRITE,
    MARK_PIVOT
}

public class SortingAction {
    private ActionType type;
    private int index1;
    private int index2;

    public SortingAction(ActionType type, int index1, int index2) {
        this.type = type;
        this.index1 = index1;
        this.index2 = index2;
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

    @Override
    public String toString() {
        return "Action{" +
                "type=" + type +
                ", index1=" + index1 +
                ", index2=" + index2 +
                '}';
    }
}
