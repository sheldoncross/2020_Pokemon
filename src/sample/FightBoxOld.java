package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FightBoxOld {

    public Monster monster;
    public Monster target;

    private StackPane mainPane = new StackPane();
    private GridPane skillPane = new GridPane();
    private StackPane textPane = new StackPane();
    private FightBoxHandler fightBoxHandler;
    private Text monsterName;

    final private Image backImage = new Image(new FileInputStream("background/textbox.png"));
    final private ImageView background = new ImageView();

    public Button skillButton[] = new Button[4];


    public FightBoxOld(Monster monster) throws FileNotFoundException {
        //Monster used for the fight skill box
        this.monster = monster;
        //fightBoxHandler = new FightBoxHandler(this);
        init();
        createTargetDummy();
    }
    private void createTargetDummy() throws FileNotFoundException {
        int skillDamage[] = {0, 0, 0, 0};
        target = new Monster(skillDamage, 0, "Target Dummy");
    }
    private void init(){

        System.out.println("init ");
        background.setImage(backImage);
        background.setFitWidth(600);
        background.setFitHeight(150);

        for(int i = 0; i < skillButton.length; i++){
            skillButton[i] = new Button(monster.getSkills().get(i).getName());
        }

        skillButton[0].setOnAction(fightBoxHandler);
        skillButton[1].setOnAction(fightBoxHandler);
        skillButton[2].setOnAction(fightBoxHandler);
        skillButton[3].setOnAction(fightBoxHandler);

        //Maybe have power points so abilities can only be used x times
        skillPane.add(skillButton[0], 1, 0);
        skillPane.add(skillButton[1], 2, 0);
        skillPane.add(skillButton[2], 1, 1);
        skillPane.add(skillButton[3], 2, 1);
       // skillPane.add(monsterName, 0, 0);

        skillPane.setHgap(25);
        skillPane.setVgap(15);
        skillPane.setPadding(new Insets(3,3,3,3));
        skillPane.setAlignment(Pos.CENTER_LEFT);
        monsterName = new Text(monster.getName() + " skills");
        monsterName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        monsterName.setFill(Color.BLACK);
;
        textPane.getChildren().add(monsterName);
        textPane.setAlignment(Pos.CENTER);

        mainPane.getChildren().add(background);
        mainPane.getChildren().add(skillPane);
        mainPane.getChildren().add(textPane);

        mainPane.setAlignment(Pos.BOTTOM_LEFT);

    }

    public StackPane getMainPane(){
        return this.mainPane;
    }

}
