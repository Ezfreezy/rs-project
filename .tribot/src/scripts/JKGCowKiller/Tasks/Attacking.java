package scripts.JKGCowKiller.Tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;

import scripts.JKGCowKiller.JKGCowKiller;
import scripts.JkgAPI.Framework.Task;

public class Attacking implements Task {

	@Override
	public int priority() {
		return 0;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public void execute() {
		
		if (Player.getRSPlayer().isInCombat())
			inCombat();
		else
			attackCow();
		
	}
	
	private RSNPC cow = null;
	
	
	
	
	private RSNPC selectNextCow() {
		
		RSNPC[] cow = NPCs.findNearest("Cow");
		
		return (RSNPC) JKGCowKiller.abc.selectNextTarget(cow);
		
	}
	
	
	
	private void attackCow() {
		
		cow.click("Attack");
		
		Timing.waitCondition(new Condition() {
			@Override
			public boolean active() {
				General.sleep(150, 300);
				return Player.getRSPlayer().isInCombat();
		}}, General.random(15000, 20000)); 
					
	}
	
	
	
	private void inCombat() {
		
		this.cow = selectNextCow();
		
		if (JKGCowKiller.abc.shouldHover() && Mouse.isInBounds()) {
			if (JKGCowKiller.abc.shouldOpenMenu())
				cow.hover();
			else
				cow.hover();
			
			Timing.waitCondition(new Condition() {
				@Override
				public boolean active() {
					General.sleep(150, 300);
					return !Player.getRSPlayer().isInCombat();
				}
			}, General.random(100000, 200000));
			
		}
		else
			idleActions();
		
	}
	
	
	
	
	
	private void idleActions() {
		
		if (JKGCowKiller.abc.shouldCheckTabs())
			JKGCowKiller.abc.checkTabs();
		
		if (JKGCowKiller.abc.shouldCheckXP())
			JKGCowKiller.abc.checkXP();
		
		if (JKGCowKiller.abc.shouldExamineEntity())
			JKGCowKiller.abc.examineEntity();
		
		if (JKGCowKiller.abc.shouldLeaveGame())
			JKGCowKiller.abc.leaveGame();
		
		if (JKGCowKiller.abc.shouldMoveMouse())
			JKGCowKiller.abc.moveMouse();
		
		if (JKGCowKiller.abc.shouldPickupMouse())
			JKGCowKiller.abc.pickupMouse();
		
		if (JKGCowKiller.abc.shouldRightClick())
			JKGCowKiller.abc.rightClick();
		
		if (JKGCowKiller.abc.shouldRotateCamera())
			JKGCowKiller.abc.rotateCamera();
		
	}
}