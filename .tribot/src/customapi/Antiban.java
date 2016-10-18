package customapi;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.*;
//import scripts.starfox.api2007.Inventory07;
//import scripts.starfox.api2007.Mouse07;
//import scripts.starfox.api2007.Player07;
//import scripts.starfox.api2007.entities.Entities;
//import scripts.starfox.api2007.entities.Items07;
//import scripts.starfox.api2007.entities.NPCs07;
//import scripts.starfox.api2007.entities.Objects07;
//import scripts.starfox.api2007.walking.Walking07;

public final class Antiban {

	private static final ABCUtil abc;

	private static boolean print_debug;

	private static int resources_won;

	private static int resources_lost;

	public static int run_at;

	public static int eat_at;

	public static boolean should_hover;

	public static boolean should_open_menu;

	public static long last_under_attack_time;

	static {
		abc = new ABCUtil();
		print_debug = false;
		resources_won = 0;
		resources_lost = 0;
		run_at = abc.generateRunActivation();
		eat_at = abc.generateEatAtHP();
		should_hover = abc.shouldHover();
		should_open_menu = abc.shouldOpenMenu() && abc.shouldHover();
		last_under_attack_time = 0;
		General.useAntiBanCompliance(true);
	}

	// private AntiBan() {
	// }

	public static void destroy() {
		abc.close();
	}

	public static ABCUtil getABCUtil() {
		return abc;
	}

	public static ABCProperties getProperties() {
		return getABCUtil().getProperties();
	}

	public static int getWaitingTime() {
		return getProperties().getWaitingTime();
	}

	public static int getReactionTime() {
		resetShouldHover();
		resetShouldOpenMenu();

		ABCProperties properties = getProperties();

		properties.setWaitingTime(getWaitingTime());
		properties.setHovering(should_hover);
		properties.setMenuOpen(should_open_menu);
		properties
				.setUnderAttack(Combat.isUnderAttack() || (Timing.currentTimeMillis() - last_under_attack_time < 2000));
		properties.setWaitingFixed(false);

		return getABCUtil().generateReactionTime();
	}

	public static void setPrintDebug(boolean print) {
		print_debug = print;
	}

	public static int getResourcesWon() {
		return resources_won;
	}

	public static int getResourcesLost() {
		return resources_lost;
	}

	public static void setResourcesWon(int amount) {
		resources_won = amount;
	}

	public static void setResourcesLost(int amount) {
		resources_lost = amount;
	}

	public static void incrementResourcesWon() {
		resources_won++;
	}

	public static void incrementResourcesLost() {
		resources_lost++;
	}

	public static void setLastUnderAttackTime(long time_stamp) {
		last_under_attack_time = time_stamp;
	}

	public static void sleepReactionTime() {
		final int reaction_time = getReactionTime();
		if (print_debug) {
			debug("Reaction time: " + reaction_time + "ms.");
		}
		try {
			getABCUtil().sleep(reaction_time);
		} catch (InterruptedException e) {
			debug("Background thread interrupted sleep");
		}
	}

	public static void generateTrackers(int estimated_wait) {
		final ABCProperties properties = getProperties();

		properties.setWaitingTime(estimated_wait);
		properties.setUnderAttack(false);
		properties.setWaitingFixed(false);

		getABCUtil().generateTrackers();
	}

	public static void resetShouldHover() {
		should_hover = getABCUtil().shouldHover();
	}

	public static void resetShouldOpenMenu() {
		should_open_menu = getABCUtil().shouldOpenMenu() && getABCUtil().shouldHover();
	}

	public static boolean moveCamera() {
		if (getABCUtil().shouldRotateCamera()) {
			if (print_debug) {
				debug("Rotated camera");
			}
			getABCUtil().rotateCamera();
			return true;
		}
		return false;
	}

	public static boolean checkXp() {
		if (getABCUtil().shouldCheckXP()) {
			if (print_debug) {
				debug("Checked xp");
			}
			getABCUtil().checkXP();
			return true;
		}
		return false;
	}

	public static boolean pickUpMouse() {
		if (getABCUtil().shouldPickupMouse()) {
			if (print_debug) {
				debug("Picked up mouse");
			}
			getABCUtil().pickupMouse();
			return true;
		}
		return false;
	}

	public static boolean leaveGame() {
		if (getABCUtil().shouldLeaveGame()) {
			if (print_debug) {
				debug("Left game window");
			}
			getABCUtil().leaveGame();
			return true;
		}
		return false;
	}

	public static boolean examineEntity() {
		if (getABCUtil().shouldExamineEntity()) {
			if (print_debug) {
				debug("Examined entity");
			}
			getABCUtil().examineEntity();
			return true;
		}
		return false;
	}

	public static boolean rightClick() {
		if (getABCUtil().shouldRightClick()) {
			if (print_debug) {
				debug("Right clicked");
			}
			getABCUtil().rightClick();
			return true;
		}
		return false;
	}

	public static boolean mouseMovement() {
		if (getABCUtil().shouldMoveMouse()) {
			if (print_debug) {
				debug("Mouse moved");
			}
			getABCUtil().moveMouse();
			return true;
		}
		return false;
	}

	public static boolean checkTabs() {
		if (getABCUtil().shouldCheckTabs()) {
			if (print_debug) {
				debug("Tab checked");
			}
			getABCUtil().checkTabs();
		}
		return false;
	}

	public static void timedActions() {
		moveCamera();
		checkXp();
		pickUpMouse();
		leaveGame();
		examineEntity();
		rightClick();
		mouseMovement();
		checkTabs();
	}

	@SuppressWarnings("unchecked")
	public static <T extends Positionable> T selectNextTarget(T[] targets) {

		return (T) getABCUtil().selectNextTarget(targets);
	}

/*	public static boolean activateRun() {
		if (Game.getRunEnergy() >= run_at && !Game.isRunOn()) {
			Mouse07.fixSelected();
			if (Options.setRunOn(true)) {
				if (print_debug) {
					debug("Turned run on at " + run_at + "%");
				}
				run_at = getABCUtil().generateRunActivation();
				return true;
			}
		}
		return false;
	}

	public static boolean eat(String option, final String name) {
		return eat(option, Inventory07.getItem(name));
	}

	public static boolean eat(String option, final int id) {
		return eat(option, Inventory07.getItem(id));
	}

	public static boolean eat(String option, RSItem item) {
		if (option == null || item == null) {
			return false;
		}

		final int current_hp = Player07.getHPPercent();

		if (current_hp <= eat_at) {
			Mouse07.fixSelected();
			if (option.isEmpty()) {
				String[] actions = Items07.getActions(item);
				for (String action : actions) {
					if (action.contains("Eat") || action.contains("Drink")) {
						option = action;
						break;
					}
				}
			}
			if (!option.isEmpty() && Clicking.click(option, item)) {
				if (print_debug) {
					debug("Ate food at " + eat_at + "%");
				}
				if (Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						sleep();
						return Player07.getHPPercent() > eat_at;
					}
				}, 3000)) {
					eat_at = getABCUtil().generateEatAtHP();
					return true;
				}
			}
		}
		return false;
	}

	public static boolean goToAnticipated(Positionable anticipated) {
		if (anticipated != null) {
			sleepReactionTime();
			return Walking07.straightWalk(anticipated.getPosition());
		}
		return false;
	}

	public static boolean shouldSwitchResources(int player_count) {
		double win_percent = ((double) (resources_won + resources_lost) / (double) resources_won);
		return win_percent < 50.0 && getABCUtil().shouldSwitchResources(player_count);
	}

	public static void sleep() {
		Client.sleep(25, 75);
	}

	public static void sleep(int iterations) {
		for (int i = 0; i < iterations; i++) {
			sleep();
		}
	}

	public static void hoverNextObject(final RSObject currentlyInteracting) {
		if (currentlyInteracting == null || !should_hover) {
			return;
		}

		String name = "";

		RSObjectDefinition definition = currentlyInteracting.getDefinition();
		if (definition != null) {
			String definitionName = definition.getName();
			if (definitionName != null) {
				name = definitionName;
			}
		}
		if (name.isEmpty()) {
			return;
		}

		final String objectName = name;
		final RSObject next = Objects07.getObject(new Filter<RSObject>() {
			
			@Override
			public boolean accept(RSObject o) {
				if (o == null) {
					return false;
				}
				final RSObjectDefinition def = o.getDefinition();
				if (def != null) {
					final String name = def.getName();
					if (name != null) {
						return name.equals(objectName) && !o.getPosition().equals(currentlyInteracting.getPosition())
								&& Objects07.isValid(o, true);
					}
				}
				return false;
			}
		}, 15);
		if (next != null) {
			if (!Entities.isHovering(next.getModel()) && Clicking.hover(next)) {
				Timing.waitCondition(new Condition() {
					@Override
					public boolean active() {
						General.sleep(25);
						return Entities.isHovering(next.getModel());
					}
				}, 500);
			}
		}
	}

	public static void hoverNextNPC() {
		if (!should_hover) {
			return;
		}

		final RSCharacter interacting = Player07.getInteractingCharacter();

		if (interacting != null) {
			final String name = interacting.getName();
			if (name != null) {
				final RSNPC next = NPCs07.getNPC(new Filter<RSNPC>() {

					@Override
					public boolean accept(RSNPC npc) {
						if (npc == null) {
							return false;
						}
						RSNPCDefinition def = npc.getDefinition();
						if (def == null) {
							return false;
						}
						String def_name = def.getName();
						if (def_name == null || !def_name.equals(name)) {
							return false;
						}
						return npc.isOnScreen() && npc.isClickable()
								&& !npc.getPosition().equals(interacting.getPosition());
					}

				});
				if (next != null) {
					if (!Entities.isHovering(next.getModel()) && Clicking.hover(next)) {
						Timing.waitCondition(new Condition() {
							@Override
							public boolean active() {
								General.sleep(25);
								return Entities.isHovering(next.getModel());
							}
						}, 500);
					}
				}
			}
		}
	}
*/
	private static void debug(Object message) {
		System.out.println("[ABC2] " + message);
	}
}
