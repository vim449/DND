package DND;

import java.util.Dictionary;
import java.util.Hashtable;

public class DNDClass {

  // fields to store class-specific information

  private char[] name;
  private Feature[] features;
  private String hitDie;
  private Hashtable<Stat, Profs> saves = new Hashtable<>();

  private int skillProficiencyNum;           // how many skill proficiencies
  private Ability[] skillProficiencyOptions; // What options do they have for
  // skill proficiencies?

  private int casting;           // half, full, third caster
  private boolean prepareSpells; // Do they have to prepare spells
  private boolean knowSpells; // Do they have a particular list of spells known

  private int[][] spellSlotTable; // 2d array containing spell slots per level
  // of spell per level of character.
  private int[][] spellsKnownTable; // 2d array containing spells known per
  // level of spell per level of character
  private Stat mainStat;

  public DNDClass(char[] name, Feature[] features, int hitDie,
                  Hashtable<Stat, Boolean> saves, int skillProficiencyNum,
                  String[] skillProficiencyOptions, int casting,
                  boolean prepareSpells, boolean knowSpells, Stat mainStat) {

    this.name = name;
    this.features = features;
    this.hitDie = hitDie;
    this.saves = saves;
    this.skillProficiencyNum = skillProficiencyNum;
    this.skillProficiencyOptions = skillProficiencyOptions;
    this.spells = spells;
    this.prepareSpells = prepareSpells;
    this.knowSpells = knowSpells;
    this.mainStat = mainStat;
  }

  public String getHitDice() { return this.hitDie; }
  public Hashtable<Stat, Profs> getSaveProfs() { return this.saves }
}
