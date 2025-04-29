package org.example;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ArrayView {
    private ArrayItem[] items;
    private int lastPivotIndex = -1;

    public ArrayView(int[] array, int sceneWidth, int sceneHeight, int maxBarHeight, int gap) {
        items = new ArrayItem[array.length];

        int max = getMaxValueInArray(array);

        float scaleHeight = getScaleHeight(maxBarHeight, max);
        float scaleWidth = getScaleWidth(array, gap, sceneWidth);

        generateItems(array, sceneHeight, gap, scaleHeight, scaleWidth);
    }

    public void executeSortingAction(SortingAction action, Text actionText) {
        ActionType type = action.getType();
        int index1 = action.getIndex1();
        int index2 = action.getIndex2();

        switch (type) {
            case CLEAR_HIGHLIGHTS:
                items[index1].markNormal();
                break;

            case UNMARK_PIVOT:
                items[index1].markNormal();
                break;

            case MARK_PIVOT:
                actionText.setText("Marking index " + index1 + " as pivot");
                items[index1].markPivot();
                break;

            case MARK_HIGHLIGHT:
                actionText.setText("Highlighting index " + index1 + " and " + index2);
                items[index1].markHighlight();
                if (index2 != -1)
                    items[index2].markHighlight();
                break;

            case SWAP:
                actionText.setText("Swapping index " + index1 + " and " + index2);
                double x1 = items[index1].getItem().getX();
                double x2 = items[index2].getItem().getX();

                items[index1].getItem().setX(x2);
                items[index2].getItem().setX(x1);

                ArrayItem temp = items[index1];
                items[index1] = items[index2];
                items[index2] = temp;
                break;

            case COMPARE:
                actionText.setText("Comparing index " + index1 + " and " + index2);
                // optionally mark highlight here too
                break;

            default:
                break;
        }
    }

    public void drawArrayView(AnchorPane root) {
        for (ArrayItem arrayItem : items) {
            root.getChildren().add(arrayItem.getItem());
        }
    }

    public ArrayItem[] getItems() {
        return items;
    }

    private void generateItems(int[] array, int sceneHeight, int gap, float scaleHeight, float scaleWidth) {
        for (int i = 0; i < array.length; i++) {
            int h = Math.round(array[i] * scaleHeight);
            int w = (int) scaleWidth;
            int x = (int) (i * scaleWidth) + ((i + 1) * gap);
            int y = sceneHeight - 150 - h;

            items[i] = new ArrayItem(x, y, array[i], h, w);
        }
    }

    private float getScaleWidth(int[] array, int gap, float scaleHeight) {
        float scaleWidth = (float) ((scaleHeight - (array.length * (gap + 1))) / array.length);
        return scaleWidth;
    }

    private float getScaleHeight(int sceneHeight, int max) {
        float scaleHeight = (float) (sceneHeight / max);
        return scaleHeight;
    }

    private int getMaxValueInArray(int[] array) {
        int max = 0;

        for (int i = 0; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }

        return max;
    }

}
