package DND;

import java.util.Hashtable;
import java.util.Vector;

public class Background {
    private char[] name;
    private String[] languages;
    private Equipment[] startingEquipment;
    private int gp;
    private Feature[] feature;
    private Hashtable<Skill, Profs> skillProficiencies = new Hashtable<>();
    private Vector<String> proficiencies;

    // NOTE: not sure how many people actually use these for char creation
    // these are the tables with different traits per roll
    private String[] personalityTrait;
    private String[] ideal;
    private String[] bond;
    private String[] flaw;
}
