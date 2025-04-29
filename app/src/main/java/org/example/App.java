package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayDeque;

public class App extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final int MAX_BAR_HEIGHT = 400;
    private static final int GAP = 10;
    private int[] testArray = { 1, 4, 2, 6, 8, 3, 3, 4, 5 };

    private AnchorPane arrayPane;
    private Button nextButton;
    private Text actionText;

    private ArrayView arrayView;
    private ArrayDeque<SortingAction> sortingActions;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        setupArrayPane();
        setupControls();
        runSortingAlgorithm();

        root.setCenter(arrayPane);
        root.setBottom(createFooter());

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.show();

        // listen for next action
        nextButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (!sortingActions.isEmpty()) {
                    SortingAction action = sortingActions.pop();
                    arrayView.executeSortingAction(action, actionText);
                }else{
                    actionText.setText("finished sorting");
                }
            }
        });
    }

    private void setupArrayPane() {
        arrayPane = new AnchorPane();
        arrayView = new ArrayView(testArray, WIDTH, HEIGHT, MAX_BAR_HEIGHT, GAP);
        arrayView.drawArrayView(arrayPane);
    }

    private void setupControls() {
        nextButton = new Button(">>");
        actionText = new Text("action");
    }

    private HBox createFooter() {
        HBox footer = new HBox(10);
        footer.setStyle("-fx-padding: 10; -fx-alignment: center;");
        footer.getChildren().addAll(nextButton, actionText);
        return footer;
    }

    private void runSortingAlgorithm() {
        sortingActions = new ArrayDeque<SortingAction>();

        System.out.println("Original array:");
        printArray(testArray);

        QuickSort.quickSort(testArray, 0, testArray.length - 1, sortingActions);

        System.out.println("Sorted array:");
        printArray(testArray);

        System.out.println("Sorting Actions:");
        System.out.println(sortingActions);
    }

    private void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
