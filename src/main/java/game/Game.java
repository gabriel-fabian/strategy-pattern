package src.main.java.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import src.main.java.game.battle.Battle;
import src.main.java.game.characters.Enemy;
import src.main.java.game.characters.Player;
import src.main.java.game.strategies.FireAttackStrategy;
import src.main.java.game.strategies.IceAttackStrategy;
import src.main.java.game.strategies.PhysicalAttackStrategy;

public class Game {
  private Player player;
  private ArrayList<Enemy> enemies;

  public Game(Player player, ArrayList<Enemy> enemies) {
    this.player = player;
    this.enemies = enemies;
  }

  public void run() {
    System.out.println("Starting game!");

    while (!enemies.isEmpty()) {
      Enemy enemy = enemies.remove(0);

      Battle encounter = new Battle(player, enemy);

      encounter.start();

      if (!player.isAlive()) {
        break;
      }
    }
  }

  public static void main(String[] args) {
	  try (Scanner scanner = new Scanner(System.in)) {

      System.out.println("Type character name: ");
      String playerName = scanner.nextLine();

      // Move to factory creation
      Player player = new Player(playerName, 100, new HashMap<String, Integer>() {{
        put("PHYSICAL", 15);
        put("FIRE", 20);
        put("ICE", 25);
      }}, new HashMap<String, Integer>() {{
        put("PHYSICAL", 5);
        put("FIRE", 8);
        put("ICE", 8);
      }}, new PhysicalAttackStrategy());

      ArrayList<Enemy> enemies = new ArrayList<>();
      enemies.add(new Enemy("Goblin", 45, new HashMap<String, Integer>() {{
        put("PHYSICAL", 8);
        put("FIRE", 0);
        put("ICE", 0);
      }}, new HashMap<String, Integer>() {{
        put("PHYSICAL", 9);
        put("FIRE", 5);
        put("ICE", 5);
      }}, new PhysicalAttackStrategy()));
      enemies.add(new Enemy("Fire Goblin", 40, new HashMap<String, Integer>() {{
        put("PHYSICAL", 3);
        put("FIRE", 17);
        put("ICE", 12);
      }}, new HashMap<String, Integer>() {{
        put("PHYSICAL", 25);
        put("FIRE", 50);
        put("ICE", 0);
      }}, new FireAttackStrategy()));
      enemies.add(new Enemy("Ice Golem", 75, new HashMap<String, Integer>() {{
        put("PHYSICAL", 3);
        put("FIRE", 15);
        put("ICE", 24);
      }}, new HashMap<String, Integer>() {{
        put("PHYSICAL", 10);
        put("FIRE", 0);
        put("ICE", 100);
      }}, new IceAttackStrategy()));

      Game game = new Game(player, enemies);
      game.run();

      if (!player.isAlive()) {
        System.out.println("Game Over!.");
      }

      System.out.println("You Win!");
    }
  }
}
