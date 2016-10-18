package scripts.JKGRunecrafter.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;

import scripts.JKGRunecrafter.Conditions;
import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Condition;
import scripts.JkgAPI.Game.Inventory;
import scripts.JkgAPI.Game.Movement;
import scripts.JkgAPI.Game.Variables;
import scripts.JkgAPI.Game.Helpers.BankingHelper;

public class FetchTiaraOrTalisman implements Task {
	@Override
	public int priority() {
		return 10;
	}

	@Override
	public boolean validate() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		return !Equipment.isEquipped(altar.getTiaraID()) && !Inventory.hasItem(altar.getTalismanID());
	}

	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		// Check for tiara
		if (!Equipment.isEquipped(altar.getTiaraID())) {

			if (Banking.isBankScreenOpen())
				Banking.close();

			if (Inventory.hasItem(altar.getTiaraID())) {
				if (equipTiara(altar))
					return;
			}
		} else
			return;

		// Check for talisman
		if (Inventory.hasItem(altar.getTalismanID()))
			return;

		// Go to bank if we have neither
		if (!altar.getBankArea().contains(Player.getPosition())) {
			Movement.walkTo(altar.getBankArea().getRandomTile());
		}

		// Open bank
		if (Banking.openBank()) {

			Timing.waitCondition(Condition.UntilBankOpen, General.random(3000, 4000));

			Banking.depositAll();

			// Check for tiara
			RSItem[] tiaraOrTalisman;
			if ((tiaraOrTalisman = Banking.find(altar.getTiaraID())).length > 0) {
				// found a tiara in the bank!
				if (BankingHelper.withdrawItem(tiaraOrTalisman[0], 1)) {
					Banking.close();
				}
				// Check for talisman
			} else if ((tiaraOrTalisman = Banking.find(altar.getTalismanID())).length > 0) {
				// found a talisman in the bank!
				if (BankingHelper.withdrawItem(tiaraOrTalisman[0], 1)) {
					Banking.close();
				}
			} else {
				// couldn't find a talisman or tiara in the bank or inventory,
				// stopping script.
			}
		}

	}

	private boolean equipTiara(AbstractAltar altar) {
		final RSItem[] tiara;
		if ((tiara = Inventory.find(altar.getTiaraID())).length > 0) {
			if (tiara[0].click("Wear")) {
				return Timing.waitCondition(Conditions.UntilTiaraEquipped(altar), General.random(2000, 3500));
			}
		}

		return false;
	}
}
