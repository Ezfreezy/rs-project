package scripts;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.script.Script;

import customapi.Antiban;

public class CowKiller extends Script {

	private static long start_time = 5000;
	private static int estimated_wait = 5000;

	private static Condition startAttacking = new Condition() {
		@Override
		public boolean active() {
			General.sleep(50);
			return Player.getRSPlayer().isInCombat();
		}
	};

	private static Condition stopAttacking = new Condition() {
		@Override
		public boolean active() {
			General.sleep(50);
			Antiban.timedActions();
			return !Player.getRSPlayer().isInCombat();
		}
	};

	@Override
	public void run() {

		while (killCows());
			
	}

	public static boolean killCows() {

		RSObject[] cows = Objects.findNearest(20, "Cow");

		if (cows.length > 0) {

			RSObject cow = Antiban.selectNextTarget(cows);

			if (cow != null && cow.isOnScreen()) {

				start_time = Timing.currentTimeMillis();

				if (DynamicClicking.clickRSObject(cow, "Attack")
						&& Timing.waitCondition(startAttacking, General.random(5000, 10000))) {
					Antiban.generateTrackers(estimated_wait);
					Timing.waitCondition(stopAttacking, General.random(6000, 9000));
					estimated_wait += Timing.currentTimeMillis() - start_time;
					estimated_wait /= 2;
					Antiban.sleepReactionTime();

				}
			}
		}
		return true;
	}
}
