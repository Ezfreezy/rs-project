package scripts.cowkiller.methods;

import org.tribot.api2007.Inventory;

import scripts.JkgAPI.Framework.Task;
import scripts.CowKiller;

public class Attack implements Task {

	@Override
	public int priority() {
		// TODO Auto-generated method stub //pick up hides bury bones
		return 0;
	}

	@Override
	public boolean validate() {
		return !Inventory.isFull();
		
	}

	@Override
	public void execute() {
		CowKiller.killCows();	
		
	}

}