package org.example;

import java.util.ArrayDeque;

public class BubbleSort implements ISort {

    @Override
    public void sort(int[] array, ArrayDeque<SortingAction> queue) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                queue.add(new SortingAction(ActionType.CLEAR_HIGHLIGHTS, -1, -1,
                        "Clear highlights before next comparison."));
                queue.add(new SortingAction(ActionType.COMPARE, j, j + 1,
                        "Compare elements at index " + j + " and " + (j + 1)));
                queue.add(new SortingAction(ActionType.MARK_HIGHLIGHT, j, j + 1,
                        "Highlight elements at index " + j + " and " + (j + 1)));

                if (array[j] > array[j + 1]) {
                    queue.add(new SortingAction(ActionType.SWAP, j, j + 1,
                            "Swap elements at index " + j + " and " + (j + 1)));
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
