package scripts.JKGRunecrafter.Tasks;

import org.tribot.api2007.ext.Filters;

import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Antiban;
import scripts.JkgAPI.Game.Inventory;
import scripts.JkgAPI.Game.Movement;
import scripts.JkgAPI.Game.Variables;

public class TravelToAltar implements Task {
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

		return essenceCount > 0;
	}

	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		Antiban.doIdleActions();

		Movement.walkTo(altar.getAltarLocation());
	}
}