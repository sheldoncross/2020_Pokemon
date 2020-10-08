package sample;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameInfoBox{
    private Group group;
    private String infoText = "";

    final private Image backImage = new Image(new FileInputStream("background/textbox.png"));
    final private ImageView background = new ImageView();
    final private Text gameInfo = new Text();

    public GameInfoBox() throws FileNotFoundException {
        init();
    }

    void init(){
        background.setImage(backImage);
        background.setFitWidth(600);
        background.setFitHeight(150);

        gameInfo.setFont(Font.loadFont("file:resources/fonts/nintendo.ttf", 10.0));
        gameInfo.setLayoutX(50.0);
        gameInfo.setLayoutY(110.0);

        StackPane pane = new StackPane();
        pane.getChildren().addAll(background, gameInfo);

        group = new Group(pane);
    }

    public Group getGroup(String text){
        infoText = text;
        gameInfo.setText(infoText);
        return group;
    }

}
