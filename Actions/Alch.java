package scripts.BicTreeKiller.Actions;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Magic;
import org.tribot.api2007.GameTab.TABS;

import scripts.BicTreeKiller.AntiBan;
import scripts.BicTreeKiller.Constants;
import scripts.BicTreeKiller.Utils;
import scripts.BicTreeKiller.BicTreeKiller;
import scripts.BicTreeKiller.Node;

public class Alch extends Node {

	@Override
	public void execute() {
		BicTreeKiller.status = "Alching";

		alch();
	}

	@Override
	public boolean validate() {
		return !Utils.isSpiritAlive() && Utils.hasNats() && Utils.axesToAlch();
	}

	public boolean alch() {
		int item = 0;

		General.sleep(100, 150);

		// If a spell is not selected, open magic tab
		if (!Magic.isSpellSelected()) {
			if (GameTab.open(TABS.MAGIC)) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(200, 400);
						return GameTab.getOpen().equals(TABS.MAGIC);
					}
				}, General.random(250, 750));

			}
		}

		// If magic tab is open, click on High Alch
		if (GameTab.getOpen().equals(TABS.MAGIC)) {
			General.sleep((long) General.randomSD(29.923, 12.39948));

			if (Magic.selectSpell(Constants.ALCH)) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(250, 400);
						return GameTab.getOpen().equals(TABS.INVENTORY);
					}
				}, General.random(250, 750));
			}
		}

		// If magic spell is selected, click on jewelry
		if (Magic.isSpellSelected()) {

			for (int i = 0; i < Constants.ALCH_AXES.length; i++) {
				if (Inventory.find(Constants.ALCH_AXES[i]).length > 0)
					item = i;

			}

			General.sleep((long) General.randomSD(37.923, 22.39948));
			if (Clicking.click(Inventory.find(Constants.ALCH_AXES[item]))) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(100,150);
						AntiBan.timedActions();
						return GameTab.getOpen().equals(TABS.MAGIC);
					}
				}, General.random(250, 750));
			}
		}

		return true;
	}

}
