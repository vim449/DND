package DND;

import java.util.Hashtable;
import java.util.Vector;

public class Character {
    Roll roll; // TODO, make constructor that supports seeding this

    public enum Alignment {
        LAWFUL_GOOD, LAWFUL_NEUTRAL, LAWFUL_EVIL, CHAOTIC_GOOD, CHAOTIC_NETRUAL, CHAOTIC_EVIL, NEUTRAL, NEUTRAL_GOOD,
        NEUTRAL_EVIL;
    }

    private DNDClass dndclass;
    private char[] subclass;
    private Race race;
    private String background; // TODO, make a custom class for this
    private char[] ideals;
    private char[] bonds;
    private char[] traits;
    private char[] flaws;
    private char[] name;
    private short age;
    private short height; // inches
    private short weight; // pounds
    private Alignment alignment;
    private int experience;
    private byte proficiencyBonus;

    private Hashtable<Stat, Byte> stats = new Hashtable<>();
    private Hashtable<Stat, Byte> modifiers = new Hashtable<>();
    private byte AC;
    private byte initiative;
    private boolean inspiration; // rn, only level 1 characters will be supported,
    // so this will always be null
    private String hitDice; // dice class?
    private short walkSpeed;
    private short flySpeed;
    private short swimSpeed;

    private byte[] spellSlots = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private short spellsKnown;
    private byte prepareCount;
    private byte spellDC;
    private byte spellAttack;
    private Vector<String> cantrips = new Vector<String>(0);
    private Vector<Vector<String>> spells = new Vector<Vector<String>>(0);

    private Hashtable<Skill, Byte> skills = new Hashtable<>();
    private Hashtable<Skill, Profs> skillProficiencies = new Hashtable<>();
    // I'm not sold on storing passive stats as fields
    private Hashtable<Skill, Byte> passives = new Hashtable<>();
    private Hashtable<Stat, Profs> saveProficiencies = new Hashtable<>();
    private Hashtable<Stat, Byte> saves = new Hashtable<>();
    private Vector<String> proficiencies;
    private Equipment[] equipment;
    private Feature[] features;
    private short maxHP;
    private int gp;

    public Character(DNDClass dndclass, Race race, String background, char[] name, Alignment alignment) {
        this.dndclass = dndclass;
        this.subclass = dndclass.getSubclass();
        this.race = race;
        this.background = background;
        this.name = name;
        this.alignment = alignment;

        experience = 0;
        proficiencyBonus = 2;

        // TODO: walkspeed (aarakocra gives flyspeed, so parse race too)
        walkSpeed = race.getSpeed();
        swimSpeed = 0;
        flySpeed = 0;
        inspiration = false;

        // TODO, roll from Background tables
        traits = null;
        bonds = null;
        flaws = null;
        ideals = null;

        // TODO: get from race/prompt user
        age = 0;
        height = 0;
        weight = 0;

        rollStats(); // TODO: make more interactive
        calcModifiers();
        AC = (byte) (10 + modifiers.get(Stat.DEXTERITY));
        initiative = modifiers.get(Stat.DEXTERITY);

        hitDice = dndclass.getHitDice();
        saveProficiencies = dndclass.getSaveProfs();
        calcSaves();

        // TODO, get from class
        spellsKnown = 0;
        prepareCount = 0;
        spellDC = (byte) (8 + proficiencyBonus + modifiers.get(dndclass.getMainStat()));
        spellAttack = (byte) (proficiencyBonus + modifiers.get(dndclass.getMainStat()));

        // TODO: Skill, skill profs, passive skills
        maxHP = (byte) (Byte.valueOf(hitDice.substring(hitDice.indexOf("d") + 1)) + modifiers.get(Stat.CONSTITUTION));

        // TODO: equipment, proficiencies, features, and gp
    }

    private void calcModifiers() {
        for (Stat key : this.stats.keySet()) {
            modifiers.put(key, (byte) ((stats.get(key) - 10) / 2));
        }
    }

    private void calcSaves() {
        for (Stat stat : Stat.values()) {
            saves.put(stat, (byte) (proficiencyBonus * saveProficiencies.get(stat).ordinal() + modifiers.get(stat)));
        }
    }

    private void rollStats() {
        Hashtable<Stat, Byte> stats = new Hashtable<>();
        for (Stat key : Stat.values()) {
            stats.put(key, (byte) roll.statRoll());
        }
        // TODO: prompt user for which stats go where
        this.stats = stats;
    }
}
