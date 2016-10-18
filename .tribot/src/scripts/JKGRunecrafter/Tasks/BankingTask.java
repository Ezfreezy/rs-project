package scripts.JKGRunecrafter.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Antiban;
import scripts.JkgAPI.Game.Condition;
import scripts.JkgAPI.Game.Helpers.BankingHelper;
import scripts.JkgAPI.Game.Inventory;
import scripts.JkgAPI.Game.Variables;

public class BankingTask implements Task {
	@Override
	public int priority() {
		return 7;
	}

	@Override
	public boolean validate() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		int essenceCount;
		if (altar.requirePureEssence())
			essenceCount = Inventory.getCount("Pure essence");
		else
			essenceCount = Inventory.getCount(Filters.Items.nameContains("essence"));

		return altar.getBankArea().contains(Player.getPosition()) && essenceCount < 1;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");
		boolean pureEssenceFallback = Variables.getInstance().get("pureEssenceFallback");

		if (Banking.openBank()) {

			if (BankingHelper.waitUntilBankItemsLoaded()) {

				if (!Inventory.isEmpty()) {

					boolean hasTiara = Equipment.isEquipped(altar.getTiaraID());

					if (hasTiara)
						Banking.depositAll();
					else
						Banking.depositAllExcept(altar.getTalismanID());

					Timing.waitCondition(new Condition() {
						public boolean active() {
							General.sleep(50);
							return hasTiara ? Inventory.isEmpty() : Inventory.getAmountOfFreeSpace() == 27;
						}
					}, General.random(3000, 4000));
				}

				String essence = "Pure essence";

				if (!altar.requirePureEssence()) {
					RSItem[] ess = Banking.find("Rune essence");
					// if there is no rune essence left, we should use pure
					// essence (if the setting is checked)
					if (ess.length >= 20 || !pureEssenceFallback) {
						// we dont have to check if we have enough pure essence,
						// the below code will catch out-of-essence cases.
						essence = "Rune essence";
					}
				}

				final int preWithdraw = Inventory.getCount(essence);

				if (Banking.withdraw(0, essence)) {

					final String ess = essence;

					General.sleep(Antiban.getUtil().DELAY_TRACKER.ITEM_INTERACTION.next());

					Timing.waitCondition(new Condition() {
						public boolean active() {
							General.sleep(50);
							return Inventory.getCount(ess) != preWithdraw;
						}
					}, General.random(3000, 4000));
				}
			}

		}
	}
}
