package DND;

import java.util.Hashtable;
import java.util.Vector;

public class DNDClass {

    // fields to store class-specific information

    private char[] name;
    private char[] subclass;
    private Feature[] features;
    private String hitDie;
    private Hashtable<Stat, Profs> saves = new Hashtable<>();

    private int skillProficiencyNum; // how many skill proficiencies
    private Skill[] skillProficiencyOptions; // What options do they have for
    // skill proficiencies?
    private Vector<String> proficiencies; // tools proficient in

    private int casting; // half, full, third caster
    private boolean prepareSpells; // Do they have to prepare spells
    private boolean knowSpells; // Do they have a particular list of spells known

    private int[][] spellSlotTable; // 2d array containing spell slots per level
    // of spell per level of character.
    private int[][] spellsKnownTable; // 2d array containing spells known per
    // level of spell per level of character
    private final Stat mainStat;
    // TODO: format for starting gold...
    // TODO: starting equipment
    // TODO: variable for limited use class features like infusions, metagmagics,
    // and invocations?
    // TODO: multiclass required stats?

    public DNDClass(char[] name, Feature[] features, String hitDie, Hashtable<Stat, Profs> saves,
            int skillProficiencyNum, Skill[] skillProficiencyOptions, int casting, boolean prepareSpells,
            boolean knowSpells, Stat mainStat, Vector<String> proficiencies) {

        this.name = name;
        this.features = features;
        this.hitDie = hitDie;
        this.casting = casting;
        this.skillProficiencyNum = skillProficiencyNum;
        this.skillProficiencyOptions = skillProficiencyOptions;
        this.prepareSpells = prepareSpells;
        this.knowSpells = knowSpells;
        this.mainStat = mainStat;
        this.proficiencies = proficiencies;
    }

    public Stat getMainStat() {
        return mainStat;
    }

    public String getHitDice() {
        return this.hitDie;
    }

    public Hashtable<Stat, Profs> getSaveProfs() {
        return this.saves;
    }

    public char[] getSubclass() {
        return this.subclass;
    }

}
