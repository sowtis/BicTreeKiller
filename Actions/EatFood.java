package scripts.BicTreeKiller.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;

import scripts.BicTreeKiller.Constants;
import scripts.BicTreeKiller.BicTreeKiller;
import scripts.BicTreeKiller.Node;
import scripts.BicTreeKiller.Utils;

public class EatFood extends Node {

	@Override
	public void execute() {
		BicTreeKiller.status = "Eating";

		eatFood();
	}

	@Override
	public boolean validate() {
		return Utils.hpPercent() <= BicTreeKiller.eatAt && Utils.hasFood();
	}

	public boolean eatFood() {
		RSItem[] food = Inventory.find(Constants.FOOD);
		if (food.length > 0) {
			if (Clicking.click("Eat", food[0])) {
				Timing.waitCondition(new Condition() {
					public boolean active() {
						General.sleep(200, 300);
						return Player.getAnimation() == -1;
					}
				}, General.random(3500, 4000));
			}
		}
		BicTreeKiller.eatAt = BicTreeKiller.abc.generateEatAtHP();

		return true;
	}

}
