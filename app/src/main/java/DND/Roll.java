package DND;

import java.util.Random;

public class Roll {
  private Random random;

  public Roll() { random = new Random(System.currentTimeMillis()); }

  public Roll(long seed) { random = new Random(seed); }

  public int roll(String rollString) {
    rollString = rollString.trim();
    if (!rollString.contains("d")) {
      System.out.println("dice rolls must be of the form XdY or XdY+Z");
    }

    String[] dice = rollString.split("d", -1);
    dice[1] = dice[1].split("\\+", 0)[0];
    int modifier = 0;
    if (rollString.contains("+")) {
      modifier = Integer.valueOf(rollString.split("\\+", -1)[1]);
    }
    int roll = 0;
    for (int i = 0; i < Integer.valueOf(dice[0]); i++) {
      roll += (random.nextInt(Integer.valueOf(dice[1])) + 1);
    }

    return roll + modifier;
  }
}
