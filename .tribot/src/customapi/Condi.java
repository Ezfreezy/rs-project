package customapi;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSTile;

public class Condi {

	public static Condition animationEquals(int animation) {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return Player.getAnimation() == animation;
			}
		};
	}

	public static Condition areaContains(RSArea area) {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return area.contains(Player.getPosition());
			}
		};
	}

	public static Condition canReach(RSTile tile) {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return PathFinding.canReach(tile, false);
			}
		};

	}

	public static Condition dialogCond() {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return NPCChat.getClickContinueInterface() != null || NPCChat.getSelectOptionInterface() != null;
			}
		};

	}

	public static Condition inCombat() {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return Player.getRSPlayer().isInCombat();
			}
		};

	}

	public static Condition playerPosition(RSTile tile) {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				return Player.getPosition().equals(tile);
			}
		};
	}

	public static Condition interfaceIsValid(int index) {
		return new Condition() {
			@Override
			public boolean active() {
				General.sleep(100, 300);
				RSInterface inface = Interfaces.get(index);
				return inface.isHidden();
			}
		};

	}

}
