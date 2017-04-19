package scripts.BicTreeKiller.Actions;

import java.util.ArrayList;
import java.util.List;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSGroundItem;

import scripts.BicTreeKiller.BicTreeKiller;
import scripts.BicTreeKiller.Constants;
import scripts.BicTreeKiller.Node;
import scripts.BicTreeKiller.Utils;

public class Loot extends Node {

	@Override
	public void execute() {
		BicTreeKiller.status = "Looting";

		loot();
	}

	@Override
	public boolean validate() {
		return Utils.itemsToLoot() > 0 && !Utils.isSpiritAlive();
	}

	public void loot() {
		List<RSGroundItem> lootList = new ArrayList<>();

		for (int i = 0; i < Constants.LOOT_ITEMS.length; i++) {
			for (RSGroundItem item : GroundItems.findNearest((Filters.GroundItems.nameEquals(Constants.AXES[i])))) {
					lootList.add(item);
			}
		}

		if (lootList.size() > 0) {
			for (RSGroundItem j : lootList) {

				if (DynamicClicking.clickRSGroundItem(j, "Take")) {
					Timing.waitCondition(new Condition() {
						public boolean active() {
							General.sleep(100, 200);
							return Utils.itemsToLoot() < 1;
						}
					}, General.random(1600, 2050));
				}

			}
		}
	}
}
