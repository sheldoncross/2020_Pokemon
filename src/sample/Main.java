package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main extends Application {
    public void start(Stage primaryStage) throws Exception {

        BorderPane initPane = new BorderPane();
        Button startButton = new Button("Start");

        startButton.setOnMouseClicked(mouseEvent -> {
            try {
                MonsterList monsterList = new MonsterList(primaryStage);
                primaryStage.setScene(new Scene(monsterList.getGroup()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        initPane.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("background/initBackground.png")),
                                                                    BackgroundRepeat.NO_REPEAT,
                                                                    BackgroundRepeat.NO_REPEAT,
                                                                    BackgroundPosition.CENTER,
                                                                    new BackgroundSize(700, 900, true,
                                                                                        true,true, true))));
        initPane.setCenter(startButton);
        Scene scene = new Scene(initPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
