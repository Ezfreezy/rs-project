package scripts.cowkiller2;

import org.tribot.api.Clicking;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;

//import customapi.framework.Task;

public class Eat extends Node {
	RSItem[] food = Inventory.find(Filters.Items.nameEquals("Meat"));

	//@Override
	//public int priority() {
		// TODO Auto-generated method stub
		//return 0;
	//}
	
	@Override
	public void execute() {

		if (checkHealth(50))
			Clicking.click(food);
	}

	private boolean checkHealth(int p) {
		return Combat.getHPRatio() < p;
	}

	@Override
	public boolean validate() {
		return food.length < 1;
	}

}