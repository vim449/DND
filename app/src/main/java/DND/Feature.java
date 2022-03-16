package DND;

import java.io.Serializable;

public class Feature implements Serializable {
    public enum Rest {
        LONG,
        SHORT,
        DAY,
        ROLLED_LONG,
        ROLLED_SHORT,
        ROLLED_DAY,
        NULL
    }

    private String name;
    private String descriptionText;
    private int level;
    private int uses;
    private Rest regainCondition;
    private String regainConditionRoll; // only used if the regain condition has
    // to occur a specific number of times

    public Feature(String name, String descriptionText, int level, int uses,
            Rest regainCondition) {
        this.name = name;
        this.descriptionText = descriptionText;
        this.level = level;
        this.uses = uses;
        this.regainCondition = regainCondition;
    }

    public Feature(String name, String descriptionText) {
        this(name, descriptionText, 0, 0, Rest.NULL);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return descriptionText;
    }
}
