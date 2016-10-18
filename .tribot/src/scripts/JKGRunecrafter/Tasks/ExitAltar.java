package scripts.JKGRunecrafter.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Player;

import scripts.JKGRunecrafter.Conditions;
import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Game.Variables;
import scripts.JkgAPI.Game.Helpers.ObjectsHelper;

public class ExitAltar implements Task {
	@Override
	public int priority() {
		return 8;
	}

	@Override
	public boolean validate() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		return altar.getAltarArea().contains(Player.getPosition());
	}

	@Override
	public void execute() {
		AbstractAltar altar = Variables.getInstance().get("altar");

		ObjectsHelper.interact("Use");

		Timing.waitCondition(Conditions.UntilNotInAltar(altar), General.random(4000, 5000));
	}
}
