package org.example;

import javafx.scene.layout.AnchorPane;

public class ArrayView {
    private ArrayItem[] items;

    public ArrayView(int[] array, int sceneWidth, int sceneHeight, int maxBarHeight, int gap) {
        items = new ArrayItem[array.length];

        int max = getMaxValueInArray(array);

        float scaleHeight = getScaleHeight(maxBarHeight, max);
        float scaleWidth = getScaleWidth(array, gap, sceneWidth);

        generateItems(array, sceneHeight, gap, scaleHeight, scaleWidth);
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

    public void drawArrayView(AnchorPane root) {
        for (ArrayItem arrayItem : items) {
            root.getChildren().add(arrayItem.getItem());
        }
    }

    public ArrayItem[] getItems() {
        return items;
    }
}
