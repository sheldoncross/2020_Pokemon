package sample;

import javafx.scene.Group;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class IOMenu {

    private Group group = new Group();

    private MenuBar menuBar = new MenuBar();
    public FileChooser fileChooser = new FileChooser();
    public Stage mainStage;

    private Menu menu = new Menu("Menu");
    public MenuItem load = new MenuItem("Load");
    public MenuItem save = new MenuItem("Save");
    public MenuItem cloudLoad = new MenuItem("Load from Cloud");
    public MenuItem cloudSave = new MenuItem("Save to Cloud");

    public IOMenuHandler menuHandler;
    public FightBox fightBox;

    public IOMenu(FightBox fightBox, Stage mainStage) {

        this.fightBox = fightBox;
        this.mainStage = mainStage;
        this.menuHandler = new IOMenuHandler(this);

        init();

    }

    private void init() {

        load.setOnAction(menuHandler);
        save.setOnAction(menuHandler);
        cloudLoad.setOnAction(menuHandler);
        cloudSave.setOnAction(menuHandler);
        menu.getItems().addAll(load, save, cloudLoad, cloudSave);
        menuBar.getMenus().addAll(menu);
        group.getChildren().add(menuBar);
        menuBar.prefWidthProperty().bind(mainStage.widthProperty());
    }

    public FightBox getFightBox() {
        return this.fightBox;
    }

    public void setFightBox(FightBox fightBox) {
        this.fightBox = fightBox;
    }

    public Group getGroup() {
        return this.group;
    }
}
