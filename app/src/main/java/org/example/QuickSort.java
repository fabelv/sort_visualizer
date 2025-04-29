package org.example;

import java.util.ArrayDeque;

public class QuickSort {

    public static void quickSort(int[] arr, int begin, int end, ArrayDeque<SortingAction> queue) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, queue);

            quickSort(arr, begin, partitionIndex - 1, queue);
            quickSort(arr, partitionIndex + 1, end, queue);
        }
    }

    private static int partition(int[] arr, int begin, int end, ArrayDeque<SortingAction> queue) {
        queue.add(new SortingAction(ActionType.UNMARK_PIVOT, end, -1)); // in case we want to unmark previous pivot
        queue.add(new SortingAction(ActionType.MARK_PIVOT, end, -1));

        int pivot = arr[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            queue.add(new SortingAction(ActionType.CLEAR_HIGHLIGHTS, -1, -1));
            queue.add(new SortingAction(ActionType.COMPARE, j, end));
            queue.add(new SortingAction(ActionType.MARK_HIGHLIGHT, j, end));

            if (arr[j] <= pivot) {
                i++;
                queue.add(new SortingAction(ActionType.SWAP, i, j));

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        queue.add(new SortingAction(ActionType.SWAP, i + 1, end));
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }

}
