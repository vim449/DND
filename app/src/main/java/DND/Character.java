package DND;

import java.util.Hashtable;
import java.util.Vector;
public class Character {
  String dndclass; // TODO, make a custom class for this
  String subclass;
  String race;       // TODO, make a custom class for this
  String background; // TODO, make a custom class for this
  String ideals;
  String bonds;
  String traits;
  String flaws;
  String name;
  short age;
  short height; // inches
  Alignment alignment;
  int experience;
  byte proficiency_bonus;

  Hashtable<Stat, Byte> stats = new Hashtable<>();
  Hashtable<Stat, Byte> modifiers = new Hashtable<>();
  byte AC;
  byte initiative;
  boolean inspiration; // rn, only level 1 characters will be supported, so this
                       // will always be null
  String hitdice;      // dice class?
  short walkSpeed;
  short flySpeed;
  short swimSpeed;
  byte[] spellSlots = new byte[9];
  short spellsKnown;
  byte prepareCount;
  Vector<String> cantrips = new Vector<String>(0);
  Vector<Vector<String>> spells = new Vector<Vector<String>>(0);

  Hashtable<String, Byte> abilities = new Hashtable<>();
  Hashtable<String, Profs> ability_proficiences = new Hashtable<>();
  // I'm not sold on storing passive stats as fields
  Hashtable<String, Byte> passives = new Hashtable<>();
  String[] proficiencies;
  String[] equipment;
  String[] features;
  short maxhp;
  int gp;
}
