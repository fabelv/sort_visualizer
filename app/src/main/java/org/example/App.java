package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
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

        int[] testArray = new int[]{1,4,2,6,8,3};
        int length = testArray.length;
        int max = 8;

        int gap = 10;
        float scaleHeight = (float) (maxBarHeight / max);
        float scaleWidth = (float) ((width - (length * (gap) )) / length);
        System.out.println(scaleWidth);

        for (int i = 0; i < testArray.length; i++) {
            Rectangle r = new Rectangle();
            int h = Math.round(testArray[i] * scaleHeight);
            int w = (int) scaleWidth;
            int x = (int) (i * scaleWidth) +gap;
            int y = height - 50 - h;

            r.setHeight(h);
            r.setWidth(w);
            r.setX(x);
            r.setY(y);

            System.out.println("h: " + h + " w: " + w + " x: " + x + " y: " + y);
            System.out.println(r.toString());

            root.getChildren().add(r);

        }


        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.show();
    }

}
