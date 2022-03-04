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

    private String dndclass;
    private String subclass;
    private String race; // TODO, make a custom class for this
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
    private byte proficiency_bonus;

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

    private Hashtable<String, Byte> abilities = new Hashtable<>();
    private Hashtable<String, Profs> ability_proficiences = new Hashtable<>(); // I'm not sold on storing passive stats
                                                                               // as
    // fields
    private Hashtable<String, Byte> passives = new Hashtable<>();
    private Hashtable<Stat, Profs> saving_throws = new Hashtable<>();
    private String[] proficiencies;
    private Equipment[] equipment;
    private Feature[] features;
    private short maxhp;
    private int gp;
}
