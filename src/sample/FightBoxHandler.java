package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;

public class FightBoxHandler implements EventHandler<ActionEvent> {
    public Thread thread;
    private FightBox fightBox;

    public FightBoxHandler(FightBox fightBox){
        this.fightBox = fightBox;
        thread = new Thread(fightBox);
        thread.run();
    }

    @Override
    public void handle(ActionEvent e) {
        if(e.getSource() == fightBox.skillButton[0]){
            BorderPane bp = MonsterList.getBp();
            fightBox.target.setHP(fightBox.target.getHP() - fightBox.monster.getSkills().get(0).getDamage());
            String text = fightBox.monster.getName() + " did " + fightBox.monster.getSkills().get(0).getDamage() + " damage to "
                    + fightBox.target.getName() + "(" + fightBox.target.getHP() + ")";
            fightBox.setInfo(text);
            fightBox.setGameInfoBox(fightBox.getGameInfoBox());
            sleep(bp);

        }else if(e.getSource() == fightBox.skillButton[1]){
            BorderPane bp = MonsterList.getBp();
            fightBox.target.setHP(fightBox.target.getHP() - fightBox.monster.getSkills().get(1).getDamage());
            String text = fightBox.monster.getName() + " did " + fightBox.monster.getSkills().get(1).getDamage() + " damage to "
                    + fightBox.target.getName() + "(" + fightBox.target.getHP() + ")";
            fightBox.setInfo(text);
            fightBox.setGameInfoBox(fightBox.getGameInfoBox());
            sleep(bp);

        } else if(e.getSource() == fightBox.skillButton[2]){
            BorderPane bp = MonsterList.getBp();
            fightBox.target.setHP(fightBox.target.getHP() - fightBox.monster.getSkills().get(2).getDamage());
            String text = fightBox.monster.getName() + " did " + fightBox.monster.getSkills().get(2).getDamage()+ " damage to "
                    + fightBox.target.getName() + "(" + fightBox.target.getHP() + ")";
            fightBox.setInfo(text);
            fightBox.setGameInfoBox(fightBox.getGameInfoBox());
            sleep(bp);

        }else if(e.getSource() == fightBox.skillButton[3]){
            BorderPane bp = MonsterList.getBp();
            fightBox.target.setHP(fightBox.target.getHP() - fightBox.monster.getSkills().get(3).getDamage());
            String text = fightBox.monster.getName() + " did " + fightBox.monster.getSkills().get(3).getDamage() + " damage to "
                    + fightBox.target.getName() + "(" + fightBox.target.getHP() + ")";
            fightBox.setInfo(text);
            fightBox.setGameInfoBox(fightBox.getGameInfoBox());
            sleep(bp);

        }
        fightBox.cpuAttack();
        fightBox.monsterInfoBox.updateBar();
        fightBox.monsterInfoBox.updateText();
    }

    public void sleep(BorderPane bp) {
        Task<Void> sleeper = new Task<>() {
            @Override
            protected Void call() throws InterruptedException {
                thread.sleep(2500);
                return null;
            }
        };
        sleeper.setOnRunning(workerStateEvent -> bp.setBottom(fightBox.getGroup()));
        sleeper.setOnSucceeded(workerStateEvent -> {
            fightBox.setGroup(new Group(fightBox.getBackground(), fightBox.getSkillPane(), fightBox.getMonsterName()));
            bp.setBottom(fightBox.getGroup());
        });
        new Thread(sleeper).start();
    }
}
