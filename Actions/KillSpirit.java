package scripts.BicTreeKiller.Actions;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;

import scripts.BicTreeKiller.AntiBan;
import scripts.BicTreeKiller.BicTreeKiller;
import scripts.BicTreeKiller.Constants;
import scripts.BicTreeKiller.Node;
import scripts.BicTreeKiller.Utils;

public class KillSpirit extends Node {

	@Override
	public void execute() {

		if (Utils.isSpiritAlive()) {
			if (!Utils.isOnSafespot()) {
				if (walkSafespot()) {
				}
			}

			if (attackSpirit()) {
				AntiBan.generateTrackers(30000, false);
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						General.sleep(100,150);
						AntiBan.timedActions();
						RSNPC[] spirit = NPCs.findNearest("Tree spirit");
						return spirit[0].getAnimation() == Constants.DEATH_ANIMATION;
					}

				}, General.random(50000, 60000));

				BicTreeKiller.status = "AntiBan Sleep";
				AntiBan.getReactionTime();
				AntiBan.sleepReactionTime();
			}
		}

		if (!Utils.isSpiritAlive()) {
			if (!Utils.isOnChopTile()) {
				if (walkChopspot()) {

				}
			}
			
			chopTree();
		}
	}

	@Override
	public boolean validate() {
		return Utils.hasSupplies() && !Utils.axesToAlch()
				&& Utils.itemsToLoot() < 1;
	}

	public boolean walkSafespot() {
		BicTreeKiller.status = "Walk Safespot";
		
		if (Walking.walkTo(Constants.SAFE_TILE)) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Player.getPosition().equals(Constants.SAFE_TILE);
				}

			}, General.random(3000, 5000));
		}

		return true;
	}

	public boolean walkChopspot() {
		BicTreeKiller.status = "Walk Chopspot";
		
		if (Walking.walkTo(Constants.CHOP_AREA.getRandomTile())) {
			Timing.waitCondition(new Condition() {

				@Override
				public boolean active() {
					return Constants.CHOP_AREA.contains(Player.getPosition());
				}

			}, General.random(3000, 5000));
		}

		return true;
	}

	public boolean chopTree() {
		BicTreeKiller.status = "Chopping Tree";
		
		RSObject[] tree = Objects.findNearest(4, "Tree");
		
		if (tree.length > 0) {
			if (DynamicClicking.clickRSObject(tree[0], "Chop-down")) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return BicTreeKiller.lastServerMessage == "You swing";
					}

				}, General.random(2000, 3000));

				BicTreeKiller.lastServerMessage = "";
			}
		}

		return true;
	}

	public boolean attackSpirit() {
		BicTreeKiller.status = "Attacking Spirit";

		RSNPC[] spirit = NPCs.findNearest("Tree spirit");

		if (spirit.length > 0) {
			if (DynamicClicking.clickRSNPC(spirit[0], "Attack")) {
				Timing.waitCondition(new Condition() {

					@Override
					public boolean active() {
						return Player.getAnimation() == Constants.ATTACK_ANIMATION;
					}

				}, General.random(2000, 3000));
				return true;
			}
		}

		return false;
	}

}
