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
    NEUTRAL_EVIL
  }

  private DNDClass dndclass;
  private String subclass;
  private String race;       // TODO, make a custom class for this
  private String background; // TODO, make a custom class for this
  private String ideals;
  private String bonds;
  private String traits;
  private String flaws;
  private String name;
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

  private byte[] spellSlots = new byte[9];
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
  private Hashtable<Stat, Byte> saves = new Hashtable<>;
  private String[] proficiencies;
  private Equipment[] equipment;
  private Feature[] features;
  private short maxhp;
  private int gp;

  public Character(String dndclass, String race, String background, String name,
                   Alignment alignment) {
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
    spellSlots = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    prepareCount = 0;
    spellDC = 0;
    spellAttack = 0;

    traits = "";
    bonds = "";
    flaws = "";
    ideals = "";
    age = 0;
    height = 0;

    calcModifiers();
    AC = 10 + modifiers.get(Stats.DEXTERITY);
    initiative = modifiers.get(Stats.DEXTERITY);

    hitdice = this.dndclass.getHitDice();
    saveProficiencies = this.dndclass.getSaveProfs();
    saves = calcSaves();
  }

  private calcModifiers() {
    for (Stat key : this.stats.keySet()) {
      modifiers.put(key, (stats.get(key) - 10) / 2);
    }
  }

  private calcSaves() {
    for (Stat stat : Stat.values()) {
      saves.put(stat, proficiencyBonus * saveProficiencies.get(stat) +
                          modifiers.get(stat));
    }
  }
}
