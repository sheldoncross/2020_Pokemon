package sample;

public class Skill {

    private String name;
    private int damage;

    public Skill(String name, int damage){
        this.name = name;
        this.damage = damage;
    }

    public String getName(){return name;}
    public int getDamage(){return damage;}
}
