package practice;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

@ScriptManifest(authors = { "Jkg58" }, category = "Skills", name = "Gibbos_Firemaker")
public class Gibbos_Firemaker extends Script {

	RSItem[] normalLogs = Inventory.find(Filters.Items.nameEquals("Logs"));

	RSItem[] tinderBox = Inventory.find(Filters.Items.nameEquals("Tinderbox"));

	RSTile banktile = new RSTile(3092, 3244, 0);

	Condition burningLogs = new Condition() {
		@Override
		public boolean active() {
			General.sleep(1000, 1500);

			return Player.getAnimation() == 733;
		}
	};

	@Override
	public void run() {

		while (loop());
	}

	public boolean loop() {

		if (normalLogs.length > 0) {
			burn();
			return true;
		
		} else
			walkToBank();
		return true;
	}

	public void burn() {

		if (Player.getAnimation() == -1) {

			if (Clicking.click(tinderBox))

				if (Clicking.click(normalLogs)) {
					Timing.waitCondition(burningLogs, General.random(4000, 5000));
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

			Banking.withdraw(0, "Logs");
			General.sleep(400, 800);
			Banking.close();
		}
		WebWalking.walkTo(startingTile);
	}

}
