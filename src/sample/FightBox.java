package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class FightBox implements Runnable{

    public Monster monster;
    public Monster target;
    public MonsterInfoBox monsterInfoBox;
    public Button skillButton[] = new Button[4];
    private Group group;
    private GameInfoBox gameInfoBox = new GameInfoBox();
    private String infoText = "";
    private GridPane skillPane = new GridPane();
    private FightBoxHandler fightBoxHandler;
    private Text monsterName;
    final private Image backImage = new Image(new FileInputStream("background/textbox.png"));
    final private ImageView background = new ImageView();

    public FightBox(Monster monster) throws FileNotFoundException {
        //Monster used for the fight skill box
        this.monster = monster;
        createTargetDummy();
        fightBoxHandler = new FightBoxHandler(this);
        run();

    }

    public FightBox(Monster monster, Monster target) throws FileNotFoundException {
        //Monster used for the fight skill box
        this.monster = monster;
        this.target = target;
        fightBoxHandler = new FightBoxHandler(this);
        run();
    }

    private void createTargetDummy() throws FileNotFoundException {
        ArrayList<Skill> skills = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            skills.add(new Skill("Test", 1));
        }
        this.target = new Monster(skills, 45, "Target Dummy", null);
    }

    @Override
    public void run(){
        try {
            monsterInfoBox = new MonsterInfoBox(monster, target);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        skillPane.add(skillButton[0], 0, 0);
        skillPane.add(skillButton[1], 1, 0);
        skillPane.add(skillButton[2], 0, 1);
        skillPane.add(skillButton[3], 1, 1);

        skillPane.setHgap(25);
        skillPane.setVgap(15);
        skillPane.setPadding(new Insets(3,3,3,3));
        skillPane.setAlignment(Pos.CENTER_LEFT);

        monsterName = new Text(monster.getName() + " skills");
        monsterName.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        monsterName.setFill(Color.BLACK);

        skillPane.setLayoutX(110);
        skillPane.setLayoutY(45);
        monsterName.setLayoutX(skillPane.getLayoutX());
        monsterName.setLayoutY(30);

        group = new Group(background, skillPane, monsterName);
    }

    public void cpuAttack(){
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int damage = target.getSkills().get(0).getDamage();
        monster.setHP(monster.getHP() - damage);
        infoText = target.getName() + " did " + target.getSkills().get(0).getDamage()
                    + " damage to " + monster.getName() + "(" + monster.getHP() + ")";
    }

    public Group getGroup(){return group;}

    public void setGroup(Group newGroup){group = newGroup;}

    public GameInfoBox getGameInfoBox(){return gameInfoBox;}

    public GridPane getSkillPane(){return skillPane;}

    public ImageView getBackground(){return background;}

    public Text getMonsterName(){return monsterName;}

    public void setInfo(String text){infoText = text;}

    public void setGameInfoBox(GameInfoBox gameInfoBox){
        group = gameInfoBox.getGroup(infoText);
    }
}