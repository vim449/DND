package DND;

public enum Ability {
  ACROBATICS(Stats.DEXTERITY),
  ANIMAL_HANDLING(Stats.WISDOM),
  ARCANA(Stats.INTELLIGENCE),
  ATHLETICS(Stats.Strength),
  DECEPTION(Stats.CHARISMA),
  HISTORY(Stats.INTELLIGENCE),
  INSIGHT(Stats.WISDOM),
  INTIMIDATION(Stats.CHARISMA),
  INVESTIGATION(Stats.WISDOM),
  MEDICINE(Stats.WISDOM),
  NATURE(Stats.INTELLIGENCE),
  PERCEPTION(Stats.WISDOM),
  PERFORMANCE(Stats.CHARISMA),
  PERSUASION(Stats.CHARISMA),
  RELIGION(Stats.INTELLIGENCE),
  SLEIGHT_OF_HAND(Stats.CHARISMA),
  STEALTH(Stats.DEXTERITY),
  SURVIVAL(Stats.WISDOM);

  private Stat stat;

  public Stat getStat() { return this.stat; }

  private Ability(Stat stat) { this.stat = stat; }
}
