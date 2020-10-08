package sample;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Monster {

    private Image monsterImage;
    public List<Skill> skillsArrayList;
    private final Image FIRETYPE = new Image(new FileInputStream("images/sprite1.png"));
    private final Image WATERTYPE = new Image(new FileInputStream("images/WaterStarter-1.png.png"));
    private final Image GRASSTYPE = new Image(new FileInputStream("images/GrassStart-1.png.png"));
    private final Image TARGET_DUMMY = new Image(new FileInputStream("images/unnamed.png"));

    //maybe a specialSkill List for abilities like dodge or leer that do 0 damage
    public int HP;
    private String name;
    private String type;


    public Monster(List skills, int HP, String name, String type) throws FileNotFoundException {
        this.name = name;
        this.skillsArrayList = skills;
        this.type = type;
        this.HP = HP;
        imageInit();
    }

    public Monster(String[] skills, int[] skillDamage, int HP, String name, String type) throws FileNotFoundException {
        this.name = name;
        for(int i = 0; i < 3; i++){
            this.skillsArrayList.add(new Skill(skills[i], skillDamage[i]));
        }
        this.type = type;
        this.HP = HP;
        imageInit();
    }

    public Monster(int[] skillDamage, int HP, String name) throws FileNotFoundException {
        this.name = name;
        this.HP = HP;
        for(int i = 0; i < 3; i++){
            this.skillsArrayList.add(new Skill("Test", skillDamage[i]));
        }
        imageInit();
    }

    private void imageInit(){
        //if fire iif whatever
        if(this.type == "fire"){
            monsterImage = FIRETYPE;
        }else if(this.type == "water"){
            monsterImage = WATERTYPE;
        }else if(this.type == "grass"){
            monsterImage = GRASSTYPE;
        }else{
            monsterImage = TARGET_DUMMY;
        }
    }

    public Image getMonsterImage(){
        return this.monsterImage;
    }

    public int getHP(){
        return this.HP;
    }

    public String getName(){
        return this.name;
    }

    public List<Skill> getSkills(){ return this.skillsArrayList;}

    public String getType() { return type; }

    public void setHP(int HP){
        this.HP = HP;
    }

    public void setName(String name) { this.name = name; }

    public void setType(String type) { this.type = type; }

    public String toString(){
        // Skill names, skill damage, name,0ty0e, HP
        return skillsArrayList.get(0) + ", " + skillsArrayList.get(1) + ", " +
                skillsArrayList.get(2) + ", " + skillsArrayList.get(3) + ", " +
                skillsArrayList.get(0).getDamage() + ", " + skillsArrayList.get(1).getDamage() + ", " +
                skillsArrayList.get(2).getDamage() + ", " + skillsArrayList.get(3).getDamage() + ", " +
                name + ", " + type + ", " + HP;
    }

}
