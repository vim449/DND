package DND;

import java.util.Hashtable;

public class Race {
  private char[] name;
  private char[] subrace; // null means that race has no subrace
  private String[] languages;
  private Feature[] features;

  private Hashtable<Stat, Byte> scoreIncreases = new Hashtable<>();
  private Hashtable<Ability, Profs> proficiencies = new Hashtable<>();
  private byte speed;
  private byte age; // TODO: find a good type to represent a range
  private String height;
  private String weightMult;
  // TODO: variant human exists (choice of profs)
  // TODO: aarakocra exists (flyspeed)
  // TODO: dragonborn exists (weapon attacks)
  // TODO: tiefling/drow exists (spell-list)

  public Race(char[] name, char[] subrace, String[] languages,
              Feature[] features, Hashtable<Stat, Byte> scoreIncreases,
              Hashtable<Ability, Profs> proficiencies, byte speed, byte age,
              String height, String weightMult) {
    this.name = name;
    this.subrace = subrace;
    this.languages = languages;
    this.features = features;
    this.scoreIncreases = scoreIncreases;
    this.proficiencies = proficiencies;
    this.speed = speed;
    this.age = age;
    this.height = height;
    this.weightMult = weightMult;
  }

  public Hashtable<Ability, Profs> getProficiencies() {
    return this.proficiencies;
  }

  public Feature[] getFeatures() { return this.features; }

  public String[] getLanguages() { return this.languages; }

  public byte getSpeed() { return this.speed; }
}
