package DND;

public class Weapon extends Equipment {
  enum Type { martial, simple, ranged }
  byte hands;
  String features;
  Type type;
  String damage;
}
