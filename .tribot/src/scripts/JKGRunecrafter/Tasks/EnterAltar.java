package scripts.JKGRunecrafter.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;

import scripts.JKGRunecrafter.Conditions;
import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Antiban;
import scripts.JkgAPI.Game.Inventory;
import scripts.JkgAPI.Game.Variables;
import scripts.JkgAPI.Game.Helpers.ObjectsHelper;

public class EnterAltar implements Task {
	@Override
	public int priority() {
		return 5;
	}

	@Override
	public boolean validate() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		int essenceCount;
		if (altar.requirePureEssence())
			essenceCount = Inventory.getCount("Pure essence");
		else
			essenceCount = Inventory.getCount(Filters.Items.nameContains("essence"));

		return essenceCount > 0 && altar.getAltarLocation().distanceTo(Player.getPosition()) < 7;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		final RSItem[] talisman;
		if ((talisman = Inventory.find(altar.getTalismanID())).length > 0) {

			String uptext = Game.getUptext();

			if (uptext == null || !uptext.equalsIgnoreCase("Use " + altar.getRuneName() + " talisman ->")) {
				if (talisman[0].click()) {
					General.sleep(Antiban.getUtil().DELAY_TRACKER.ITEM_INTERACTION.next());
				}
			} else {
				ObjectsHelper.interact("Mysterious ruins", "Use");
			}
		} else
			ObjectsHelper.interact("Enter");

		Timing.waitCondition(Conditions.UntilInAltar(altar), General.random(4000, 5000));
	}

}
