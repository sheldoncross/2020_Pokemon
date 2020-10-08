package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FightBackground {
    Image backgroundURL = new Image(new FileInputStream("background/Background_Attack-1.png.png"));

    BackgroundImage backgroundimage = new BackgroundImage(backgroundURL,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);

    public Background background = new Background(backgroundimage);

    public FightBackground() throws FileNotFoundException {


    }
}
