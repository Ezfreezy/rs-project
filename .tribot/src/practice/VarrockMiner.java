package practice;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

@ScriptManifest(authors = { "Jkg58" }, category = "Skills", name = "Gibbos_Miner", description = "Start at trees east of Varrock")
public class VarrockMiner extends Script { //maybe cut trees buy cooking guild

	int[] tinrocks = { 7485, 7486 };
	int[] emptyrocks = { 7468, 7469 };
	int ironrocks = 7455;
	int pick_id = 1265;

	RSObject[] tin;
	RSObject[] iron;

	private static Skills.SKILLS mininglvl = Skills.SKILLS.MINING;

	RSTile banktile = new RSTile(3185, 3436);

	Condition mining = new Condition() {

		@Override
		public boolean active() {
			General.sleep(1000, 2000);
			return Player.getAnimation() == 625;
		}
	};

	@Override
	public void run() {

		while (loop())
			;
	}

	public boolean loop() {

		if (!Equipment.isEquipped(pick_id)) {
			equiptPick();
			return true;
		}

		if (!Inventory.isFull()) {
			tinoriron();
			return true;
		} else
			walkToBank();
		return true;
	}

	public void equiptPick() {

		RSItem[] pickaxe = Inventory.find(pick_id);
		pickaxe[0].click("Wield");
	}

	public void tinoriron() {

		if (mininglvl.getCurrentLevel() >= 15) {
			mineiron();
		}

		else
			minetin();
	}

	public void minetin() {

		tin = Objects.findNearest(10, tinrocks);
		if (Player.getAnimation() == -1) {
			if (DynamicClicking.clickRSObject(tin[0], "Mine")) {
				Timing.waitCondition(mining, General.random(2000, 3000));
			}
		}
	}

	public void mineiron() {

		iron = Objects.findNearest(10, ironrocks);
		if (Player.getAnimation() == -1) {
			if (DynamicClicking.clickRSObject(iron[0], "Mine")) {
				Timing.waitCondition(mining, General.random(5000, 6000));
			}
		}
	}

	public void walkToBank() {

		RSTile startingTile = Player.getPosition();
		if (WebWalking.walkTo(banktile)) {
			do {
				Banking.openBank();
				General.sleep(2000, 3000);
			} while (!Banking.isBankScreenOpen());

			Banking.depositAll();
			General.sleep(400, 800);
			Banking.close();
		}
		WebWalking.walkTo(startingTile);
	}
}