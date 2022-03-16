package DND;

import java.util.Hashtable;
import java.util.Vector;

public class Race extends Storeable {
    private char[] subrace; // null means that race has no subrace
    private String[] languages;
    private Feature[] features;

    private Hashtable<Stat, Byte> scoreIncreases = new Hashtable<>(); // TODO: tasha's variable ancestry/floating score
                                                                      // allocation
    private Hashtable<Skill, Profs> skillProficiencies = new Hashtable<>();
    private byte speed;
    private byte age; // TODO: find a good type to represent a range
    private String height;
    private String weightMult;
    private Vector<String> proficiencies;
    // TODO: variant human exists (choice of profs)
    // TODO: aarakocra exists (flyspeed)
    // TODO: dragonborn exists (weapon attacks)
    // TODO: tiefling/drow exists (spell-list)

    public Race(String name, char[] subrace, String[] languages, Feature[] features,
            Hashtable<Stat, Byte> scoreIncreases, Hashtable<Skill, Profs> skillProficiencies, byte speed, byte age,
            String height, String weightMult, Vector<String> proficiencies) {
        this.name = name;
        this.subrace = subrace;
        this.languages = languages;
        this.features = features;
        this.scoreIncreases = scoreIncreases;
        this.skillProficiencies = skillProficiencies;
        this.speed = speed;
        this.age = age;
        this.height = height;
        this.weightMult = weightMult;
        this.proficiencies = proficiencies;
        this.storeType = StoreableType.DNDCLASS;
    }

    public Hashtable<Skill, Profs> getProficiencies() {
        return this.skillProficiencies;
    }

    public Feature[] getFeatures() {
        return this.features;
    }

    public String[] getLanguages() {
        return this.languages;
    }

    public byte getSpeed() {
        return this.speed;
    }
}
