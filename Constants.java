package scripts.BicTreeKiller;

import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Constants {
	// ITEMS
	public static int COMBAT_ITEM = 0;
	public static final int FOOD = 0;
	public static final int NAT = 561;
	public static final String[] LOOT_ITEMS = { "Mithril axe", "Adamant axe",
			"Rune axe", "Nature rune" };
	public static final String[] AXES = { "Bronze axe", "Iron axe",
			"Steel axe", "Black axe", "Mithril axe", "Adamant axe", "Rune axe" };
	public static final String[] ALCH_AXES = { "Mithril axe", "Adamant axe",
			"Rune axe" };

	// TILES
	public static final RSTile SAFE_TILE = new RSTile(3032, 4510, 0);
	public static final RSArea CHOP_AREA = new RSArea(
			new RSTile(3031, 4508, 0), new RSTile(3032, 4508, 0));

	// OTHER
	public static final String ALCH = "High Level Alchemy";
	public static final int DEATH_ANIMATION = 0;
	public static final int ATTACK_ANIMATION = 0;
}
