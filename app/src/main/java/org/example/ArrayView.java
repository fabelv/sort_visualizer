package org.example;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ArrayView {
    private ArrayItem[] items;
    private int sceneWidth;
    private int sceneHeight;
    private int maxBarHeight;
    private int gap;

    public ArrayView(int[] array, int sceneWidth, int sceneHeight, int maxBarHeight, int gap) {
        this.items = new ArrayItem[array.length];
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
        this.maxBarHeight = maxBarHeight;
        this.gap = gap;

        generateItems(array);
    }

    // returns true if next step sould be triggered by button
    public void executeSortingAction(SortingAction action, Text actionText) {
        ActionType type = action.getType();
        int index1 = action.getIndex1();
        int index2 = action.getIndex2();

        switch (type) {
            case CLEAR_HIGHLIGHTS:
                for (ArrayItem item : items) {
                    item.clearHighlightOnly();
                }
                break;

            case UNMARK_PIVOT:
                for (ArrayItem item : items) {
                    item.markNormal();
                }
                break;

            case MARK_PIVOT:
                items[index1].markPivot();
                actionText.setText(action.getDescription());
                break;

            case MARK_HIGHLIGHT:
                items[index1].markHighlight();
                if (index2 != -1)
                    items[index2].markHighlight();
                break;

            case SWAP:
                int v1 = items[index1].getValue();
                int v2 = items[index2].getValue();
                double x1 = items[index1].getItem().getX();
                double x2 = items[index2].getItem().getX();
                items[index1].updateX(x2);
                items[index2].updateX(x1);
                ArrayItem temp = items[index1];
                items[index1] = items[index2];
                items[index2] = temp;
                actionText.setText(action.getDescription());
                break;

            case COMPARE:
                actionText.setText(action.getDescription());
                break;

            default:
                actionText.setText(action.getDescription());
                break;
        }
    }

    public void drawArrayView(AnchorPane root) {
        for (ArrayItem arrayItem : items) {
            root.getChildren().add(arrayItem.getGroup());
        }
    }

    public ArrayItem[] getItems() {
        return items;
    }

    private void generateItems(int[] array) {
        int max = getMaxValueInArray(array);
        float scaleHeight = getScaleHeight(maxBarHeight, max);
        float scaleWidth = getScaleWidth(array, gap, sceneWidth);

        for (int i = 0; i < array.length; i++) {
            int h = Math.round(array[i] * scaleHeight);
            int w = (int) scaleWidth;
            int x = (int) (i * scaleWidth) + ((i + 1) * gap);
            int y = sceneHeight - 150 - h;
            items[i] = new ArrayItem(i, x, y, array[i], h, w);
        }
    }

    private float getScaleWidth(int[] array, int gap, float scaleHeight) {
        return (float) ((scaleHeight - (array.length * (gap + 1))) / array.length);
    }

    private float getScaleHeight(int sceneHeight, int max) {
        return (float) (sceneHeight / max);
    }

    private int getMaxValueInArray(int[] array) {
        int max = 0;
        for (int value : array) {
            max = Math.max(max, value);
        }
        return max;
    }

    public void resetArray(int[] newArray) {
        items = new ArrayItem[newArray.length];
        generateItems(newArray);
    }

}
