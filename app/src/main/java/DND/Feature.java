package DND;

public class Feature {
    private String name;
    private String descriptionText;
    private int level;
    private int uses;
    private Rest regainCondition;
    private String regainConditionRoll; // only used if the regain condition has to occur a speciic number of times

    public Feature(String name, String descriptionText, int level, int uses, Rest regainCondition) {
        this.name = name;
        this.descriptionText = descriptionText;
        this.level = level;
        this.uses = uses;
        this.regainCondition = regainCondition;
    }

    public Feature(String name, String descriptionText) {
        new Feature(name, descriptionText, 0, 0, Rest.NULL);
    }
}