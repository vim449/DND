package DND;

import java.io.Serializable;

public class Storeable implements Serializable {
    public String name;

    enum StoreableType {
        DNDCLASS,
        RACE,
        BACKGROUND
    }

    public StoreableType storeType;
}
