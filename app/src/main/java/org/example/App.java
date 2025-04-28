package org.example;

import java.util.ArrayDeque;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        int width = 1200;
        int height = 600;
        int maxBarHeight = 400;
        int[] testArray = new int[] { 1, 4, 2, 6, 8, 3, 3, 4, 5 };
        int gap = 10;

        ArrayView arrayView = new ArrayView(testArray, width, height, maxBarHeight, gap);
        arrayView.drawArrayView(root);

        for (var child : root.getChildren()) {
            System.out.println(child.toString());

        }

        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.show();


        for (int testArray2 : testArray) {
           System.out.print(testArray2 + ", ");;
        }
        System.out.println();

        ArrayDeque<SortingAction> queue = new ArrayDeque<>();

        quickSort(testArray, 0, testArray.length - 1, queue);

        for (int testArray2 : testArray) {
           System.out.print(testArray2 + ", ");;
        }
        System.out.println();

        System.out.println(queue.toString());
    }

    public void quickSort(int[] arr, int begin, int end, ArrayDeque<SortingAction> queue) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end, queue);

            quickSort(arr, begin, partitionIndex - 1, queue);
            quickSort(arr, partitionIndex + 1, end, queue);
        }
    }

    private int partition(int[] arr, int begin, int end, ArrayDeque<SortingAction> queue) {
        int pivot = arr[end];
        queue.add(new SortingAction(ActionType.MARK_PIVOT, end, -1));

        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            queue.add(new SortingAction(ActionType.COMPARE, j, end));
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
