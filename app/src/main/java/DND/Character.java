package DND;

import java.util.Hashtable;
import java.util.Vector;

public class Character {
  public enum Alignment {
    LAWFUL_GOOD,
    LAWFUL_NEUTRAL,
    LAWFUL_EVIL,
    CHAOTIC_GOOD,
    CHAOTIC_NETRUAL,
    CHAOTIC_EVIL,
    NEUTRAL,
    NEUTRAL_GOOD,
    NEUTRAL_EVIL;
  }

  private DNDClass dndclass;
  private String subclass;
  private String race;       // TODO, make a custom class for this
  private String background; // TODO, make a custom class for this
  private char[] ideals;
  private char[] bonds;
  private char[] traits;
  private char[] flaws;
  private char[] name;
  private short age;
  private short height; // inches
  private Alignment alignment;
  private int experience;
  private byte proficiencyBonus;

  private Hashtable<Stat, Byte> stats = new Hashtable<>();
  private Hashtable<Stat, Byte> modifiers = new Hashtable<>();
  private byte AC;
  private byte initiative;
  private boolean inspiration; // rn, only level 1 characters will be supported,
  // so this will always be null
  private String hitdice; // dice class?
  private short walkSpeed;
  private short flySpeed;
  private short swimSpeed;

  private byte[] spellSlots = {0, 0, 0, 0, 0, 0, 0, 0, 0};
  private short spellsKnown;
  private byte prepareCount;
  private byte spellDC;
  private byte spellAttack;
  private Vector<String> cantrips = new Vector<String>(0);
  private Vector<Vector<String>> spells = new Vector<Vector<String>>(0);

  private Hashtable<Ability, Byte> abilities = new Hashtable<>();
  private Hashtable<Ability, Profs> abilityProficiences = new Hashtable<>();
  // I'm not sold on storing passive stats as fields
  private Hashtable<Ability, Byte> passives = new Hashtable<>();
  private Hashtable<Stat, Profs> saveProficiencies = new Hashtable<>();
  private Hashtable<Stat, Byte> saves = new Hashtable<>();
  private String[] proficiencies;
  private Equipment[] equipment;
  private Feature[] features;
  private short maxhp;
  private int gp;

  public Character(DNDClass dndclass, String race, String background,
                   char[] name, Alignment alignment) {
    this.dndclass = dndclass;
    this.race = race;
    this.background = background;
    this.name = name;
    this.alignment = alignment;

    experience = 0;
    proficiencyBonus = 2;
    swimSpeed = 0;
    flySpeed = 0;
    inspiration = false;

    spellsKnown = 0;
    prepareCount = 0;
    spellDC = 0;
    spellAttack = 0;

    traits = null;
    bonds = null;
    flaws = null;
    ideals = null;
    age = 0;
    height = 0;

    calcModifiers();
    AC = (byte)(10 + modifiers.get(Stat.DEXTERITY));
    initiative = modifiers.get(Stat.DEXTERITY);

    hitdice = dndclass.getHitDice();
    saveProficiencies = dndclass.getSaveProfs();
    calcSaves();
  }

  private void calcModifiers() {
    for (Stat key : this.stats.keySet()) {
      modifiers.put(key, (byte)((stats.get(key) - 10) / 2));
    }
  }

  private void calcSaves() {
    for (Stat stat : Stat.values()) {
      saves.put(stat, (byte)(proficiencyBonus *
                                 saveProficiencies.get(stat).ordinal() +
                             modifiers.get(stat)));
    }
  }
}
