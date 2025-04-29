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
        queue.add(new SortingAction(
                ActionType.UNMARK_PIVOT, -1, -1,
                "Remove any existing pivot highlight before selecting a new pivot."));
        queue.add(new SortingAction(
                ActionType.MARK_PIVOT, end, -1,
                "Mark the element at index " + end + " as the pivot for this partitioning step."));

        int pivot = arr[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            queue.add(new SortingAction(
                    ActionType.CLEAR_HIGHLIGHTS, -1, -1,
                    "Clear any current highlights before comparing elements."));
            queue.add(new SortingAction(
                    ActionType.COMPARE, j, end,
                    "Compare the element at index " + j + " with the pivot (element at index " + end
                            + ") to decide if it should be moved before the pivot."));
            queue.add(new SortingAction(
                    ActionType.MARK_HIGHLIGHT, j, end,
                    "Highlight the element at index " + j + " and the pivot at index " + end
                            + " to show the comparison."));

            if (arr[j] <= pivot) {
                i++;
                queue.add(new SortingAction(
                        ActionType.SWAP, i, j,
                        "Swap the element at index " + i + " with the element at index " + j
                                + " because it is smaller than or equal to the pivot."));
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        queue.add(new SortingAction(
                ActionType.CLEAR_HIGHLIGHTS, -1, -1,
                "Clear highlights before swapping the pivot into its correct position."));
        queue.add(new SortingAction(
                ActionType.SWAP, i + 1, end,
                "Move the pivot from index " + end + " to its correct position at index " + (i + 1) + " by swapping."));
        int temp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = temp;

        return i + 1;
    }
}
