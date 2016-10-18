package scripts.JKGRunecrafter.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;

import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Antiban;
import scripts.JkgAPI.Game.Condition;
import scripts.JkgAPI.Game.Inventory;
import scripts.JkgAPI.Game.Variables;
import scripts.JkgAPI.Game.Helpers.ObjectsHelper;

public class CraftRunes implements Task {
	@Override
	public int priority() {
		return 9;
	}

	@Override
	public boolean validate() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		int essenceCount;
		if (altar.requirePureEssence())
			essenceCount = Inventory.getCount("Pure essence");
		else
			essenceCount = Inventory.getCount(Filters.Items.nameContains("essence"));

		return altar.getAltarArea().contains(Player.getPosition()) && essenceCount > 0;
	}

	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		final int preEssence = Inventory.getCount(altar.getRuneName() + " rune");

		if (ObjectsHelper.interact("Craft-rune")) {

			Timing.waitCondition(new Condition() {
				public boolean active() {
					General.sleep(50);
					return Inventory.find("essence").length != 0 && Player.getAnimation() == -1;
				}
			}, General.random(5000, 6000));
		}

		int newEssence = Math.abs(Inventory.getCount(altar.getRuneName() + " rune") - preEssence);

		if (newEssence > 0) {

			int trips = Variables.getInstance().get("trips", 0);
			Variables.getInstance().addOrUpdate("trips", ++trips);

			int runesCrafted = Variables.getInstance().get("runesCrafted", 0);
			runesCrafted += newEssence;
			Variables.getInstance().addOrUpdate("runesCrafted", runesCrafted);

			Antiban.doIdleActions();
		}
	}
}