package DND;

public enum Ability {
  ACROBATICS(Stat.DEXTERITY),
  ANIMAL_HANDLING(Stat.WISDOM),
  ARCANA(Stat.INTELLIGENCE),
  ATHLETICS(Stat.STRENGTH),
  DECEPTION(Stat.CHARISMA),
  HISTORY(Stat.INTELLIGENCE),
  INSIGHT(Stat.WISDOM),
  INTIMIDATION(Stat.CHARISMA),
  INVESTIGATION(Stat.WISDOM),
  MEDICINE(Stat.WISDOM),
  NATURE(Stat.INTELLIGENCE),
  PERCEPTION(Stat.WISDOM),
  PERFORMANCE(Stat.CHARISMA),
  PERSUASION(Stat.CHARISMA),
  RELIGION(Stat.INTELLIGENCE),
  SLEIGHT_OF_HAND(Stat.CHARISMA),
  STEALTH(Stat.DEXTERITY),
  SURVIVAL(Stat.WISDOM);

    private Stat stat;

    public Stat getStat() {
        return this.stat;
    }

    private Ability(Stat stat) {
        this.stat = stat;
    }
}
