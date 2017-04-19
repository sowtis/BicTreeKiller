package scripts.BicTreeKiller;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSNPC;

import scripts.BicTreeKiller.Constants;

public class Utils {
	
	public static int emptySpaces() {
		return 28 - Inventory.getAll().length;
	}

	public static double hpPercent() {
		double currentHP = Skills.SKILLS.HITPOINTS.getCurrentLevel();
		double totalHP = Skills.SKILLS.HITPOINTS.getActualLevel();
		return currentHP / totalHP * 100;
	}

	public static boolean hasFood() {
		if (Inventory.find(Constants.FOOD).length > 0)
			return true;

		return false;
	}
	
	public static boolean hasSupplies() {
		if (Inventory.find(Constants.COMBAT_ITEM).length > 0)
			return true;
			
		return false;
	}

	public static boolean hasNats() {
		if (Inventory.find(Constants.NAT).length > 0)
			return true;

		return false;
	}
	
	public static boolean hasAxe() {
		for (int i = 0; i < Constants.AXES.length; i++) {
			if (Inventory.find(Constants.AXES[i]).length > 0)
				return true;
		}
		return false;
	}
	
	public static boolean isSpiritAlive() {
		final RSNPC[] spirit = NPCs.find("Tree spirit");

		if (spirit.length > 0)
			return true;

		return false;
	}
	
	public static int itemsToLoot(){
		List<RSGroundItem> lootList = new ArrayList<>();

		for (int i = 0; i < Constants.LOOT_ITEMS.length; i++) {
			for (RSGroundItem item : GroundItems.findNearest((Filters.GroundItems.nameEquals(Constants.LOOT_ITEMS[i])))) {
				if (item != null && item.equals(Constants.LOOT_ITEMS[i])) {
					lootList.add(item);
				}
			}
		}

		return lootList.size();
	}
	
	public static boolean axesToAlch(){
		for (int i = 0; i < Constants.ALCH_AXES.length; i++) {
			if (Inventory.find(Constants.ALCH_AXES[i]).length > 0)
				return true;
		}
		
		return false;
	}
	
	public static boolean isOnSafespot(){
		return Player.getPosition().equals(Constants.SAFE_TILE);
	}
	
	public static boolean isOnChopTile(){
		return Constants.CHOP_AREA.contains(Player.getPosition());
	}
}
