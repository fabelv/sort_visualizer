package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class App extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 600;
    private static final int MAX_BAR_HEIGHT = 400;
    private static final int GAP = 20;
    private int[] testArray = { 1, 4, 2, 6, 8, 3, 3, 4, 5 };

    private AnchorPane arrayPane;
    private Button nextButton;
    private Text actionText;
    private ComboBox<String> algorithmSelector;

    private ArrayView arrayView;
    private ArrayList<ISort> sortingAlgorithms = new ArrayList<>();
    private Map<String, ISort> algorithmMap = new HashMap<>();
    private ArrayDeque<SortingAction> sortingActions;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        setupArrayPane();
        setupControls();
        populateSortingAlgorithms();

        root.setCenter(arrayPane);
        root.setBottom(createFooter());

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.show();

        nextButton.setOnAction(this::handleNextAction);
    }

    private void setupArrayPane() {
        arrayPane = new AnchorPane();
        arrayView = new ArrayView(testArray, WIDTH, HEIGHT, MAX_BAR_HEIGHT, GAP);
        arrayView.drawArrayView(arrayPane);
    }

    private void setupControls() {
        nextButton = new Button("Next");
        nextButton.setDisable(true);
        actionText = new Text("Select a sorting algorithm");

        algorithmSelector = new ComboBox<>();
        algorithmSelector.setPromptText("Choose algorithm");

        algorithmSelector.setOnAction(e -> {
            String selected = algorithmSelector.getValue();
            ISort algorithm = algorithmMap.get(selected);
            if (algorithm != null) {
                sortingActions = new ArrayDeque<>();
                int[] arrayCopy = testArray.clone();
                algorithm.sort(arrayCopy, sortingActions);
                actionText.setText("Ready to start sorting with " + selected);
                nextButton.setDisable(false);
            }
        });
    }

    private VBox createFooter() {
        HBox controls = new HBox(10);
        controls.setStyle("-fx-alignment: center;");
        controls.getChildren().addAll(algorithmSelector, nextButton);

        VBox footer = new VBox(5);
        footer.setStyle("-fx-padding: 10; -fx-alignment: center;");
        footer.getChildren().addAll(controls, actionText);
        return footer;
    }

    private void populateSortingAlgorithms() {
        ISort quickSort = new QuickSort();

        algorithmMap.put("QuickSort", quickSort);
        algorithmSelector.getItems().addAll("QuickSort");
    }

    private void handleNextAction(ActionEvent e) {
        if (!sortingActions.isEmpty()) {
            SortingAction action = sortingActions.pop();
            arrayView.executeSortingAction(action, actionText);

            if (!sortingActions.isEmpty()) {
                ActionType next = sortingActions.peek().getType();
                if (next == ActionType.CLEAR_HIGHLIGHTS || next == ActionType.MARK_HIGHLIGHT || next == ActionType.UNMARK_PIVOT) {
                    nextButton.fire(); // skip these automatically
                }
            }
        } else {
            actionText.setText("Finished sorting");
            nextButton.setDisable(true);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

