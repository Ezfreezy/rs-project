package scripts.cowkiller2;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

import customapi.Condi;
//import customapi.framework.Task;

public class Attack extends Node {
	RSItem[] food = Inventory.find(Filters.Items.nameEquals("Meat"));

	//@Override
	//public int priority() {
		// TODO Auto-generated method stub //pick up hides bury bones
		//return 0;
	//}
	
	@Override
	public void execute() {
		
		RSObject[] cows = Objects.findNearest(20, "Cow");
		if (!Player.getRSPlayer().isInCombat())

			if (DynamicClicking.clickRSObject(cows[0], "Attack"))

				Timing.waitCondition(Condi.inCombat(), General.random(6000, 9000));

	}

	@Override
	public boolean validate() {
		return food.length > 0;
	}

}
