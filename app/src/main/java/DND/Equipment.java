package DND;

import java.io.Serializable;

public class Equipment implements Serializable {
    enum Rarity {
        common, uncommon, rare, very_rare, legendary, artifact
    }

    int price;
    String name;
    String description;
    float weight;
    boolean isMagic;
    // TODO: methods
}
