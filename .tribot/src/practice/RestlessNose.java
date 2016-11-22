package practice;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Magic;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.ext.Doors;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import customapi.Condi;
import customapi.ItemUsing;
import customapi.TalkNpc;

@ScriptManifest(authors = {"Jkg58" }, category = "Quests", name = "Restless Ghost", description = "Start in Lumbridge Church")
public class RestlessNose extends Script {

	RSArea Area1 = new RSArea(new RSTile(3145, 3170, 0), new RSTile(3151, 3168, 0));
	RSArea Area2 = new RSArea(new RSTile(3246, 3193, 0), new RSTile(3244, 3193, 0));
	RSArea Area3 = new RSArea(new RSTile(3111, 3167, 0), new RSTile(3108, 3169, 0));
	RSArea Area4 = new RSArea(new RSTile(3110, 3163, 0), new RSTile(3107, 3165, 0));
	RSArea Area5 = new RSArea(new RSTile(3105, 3161, 0), new RSTile(3103, 3164, 0));
	RSArea Area6 = new RSArea(new RSTile(3221, 3215, 0), new RSTile(3226, 3221, 0));
	
	RSTile door1 = new RSTile(3147, 3172, 0);
	RSTile door2 = new RSTile(3247, 3193, 0);
	RSTile door3 = new RSTile(3109, 3167, 0);
	RSTile door4 = new RSTile(3107, 3162, 0);

	RSTile shacktile = new RSTile(3147, 3175, 0);
	RSTile crypttile = new RSTile(3249, 3194, 0);
	RSTile towertile = new RSTile(3109, 3164, 0);
	RSTile towertile2 = new RSTile(3106, 3161, 0);

	
	Condition ghostlength = new Condition() {
		@Override
		public boolean active() {
			General.sleep(1000, 2500);
			RSNPC[] ghost = NPCs.findNearest(Filters.NPCs.nameEquals("Restless ghost"));
			return ghost.length > 0;
		}
	};

	@Override
	public void run() {
		
		startQuest();
		walkToShack();
		talkToUrhney();
		walkToCrypt();
		talkToGhost();
		walkToTower();
		finishQuest();
	}

	public void startQuest() {

		RSNPC npc1 = NPCs.findNearest("Father Aereck")[0];
		do {
			if (DynamicClicking.clickRSNPC(npc1, "Talk-to")) {
				Timing.waitCondition(Condi.dialogCond(), General.random(5000, 6000));
			}
		} while (NPCChat.getClickContinueInterface() == null);

		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		NPCChat.selectOption("I'm looking for a quest!", true);
		TalkNpc.continueLoop();
		NPCChat.selectOption("Ok, let me help then.", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
	}

	public void walkToShack() {

		if (WebWalking.walkTo(Area1.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area1), General.random(6000, 9000));

		if (Doors.isDoorAt(door1, false)) {
			Doors.handleDoorAt(door1, true);
			Timing.waitCondition(Condi.canReach(shacktile), General.random(2000, 3000));
		}
	}

	public void talkToUrhney() {

		RSNPC npc2 = NPCs.findNearest("Father Urhney")[0];
		do {
			if (DynamicClicking.clickRSNPC(npc2, "Talk-to")) {
				Timing.waitCondition(Condi.dialogCond(), General.random(5000, 6000));
			}
		} while (NPCChat.getClickContinueInterface() == null);

		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		NPCChat.selectOption("Father Aereck sent me to talk to you.", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		NPCChat.selectOption("He's got a ghost haunting his graveyard.", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
	}

	public void walkToCrypt() {

		RSItem[] gs_amulet = Inventory.find(Filters.Items.nameEquals("Ghostspeak amulet"));

		if (gs_amulet.length > 0);
			Clicking.click(gs_amulet);

		if (WebWalking.walkTo(Area2.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area2), General.random(15000, 18000));

		if (Doors.isDoorAt(door2, false)) {
			do {
				if (Doors.handleDoorAt(door2, true))
					Timing.waitCondition(Condi.canReach(crypttile), General.random(2000, 3000));
			} while (!PathFinding.canReach(crypttile, false));
		}
		RSObject[] coffin = Objects.findNearest(5, Filters.Objects.nameEquals("Coffin"));
		RSNPC[] ghost;
		do {
			if (DynamicClicking.clickRSObject(coffin[0], "Search"))
				Timing.waitCondition(ghostlength, General.random(5000, 6000));
			ghost = NPCs.findNearest(Filters.NPCs.nameEquals("Restless ghost"));
		} while (ghost.length == 0);
	}

	public void talkToGhost() {
		
		RSNPC npc3 = NPCs.findNearest("Restless ghost")[0];
		do {
			if (DynamicClicking.clickRSNPC(npc3, "Talk-to")) {
				Timing.waitCondition(Condi.dialogCond(), General.random(5000, 6000));
			}
		} while (NPCChat.getClickContinueInterface() == null);
	
		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		NPCChat.selectOption("Yep, now tell me what the problem is.", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
	}

	public void walkToTower() {

		if (WebWalking.walkTo(Area3.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area3), General.random(6000, 9000));

		if (Doors.isDoorAt(door3, false)) {
			Doors.handleDoorAt(door3, true);
			Timing.waitCondition(Condi.canReach(towertile), General.random(5000, 6000));
		}

		RSObject[] doors = Objects.findNearest(10,Filters.Objects.nameContains("Door").combine(Filters.Objects.actionsContains("Open"), true));
				
		for (RSObject a : doors) {
			if (a.getPosition().equals(door4)) {
				do {
					if (DynamicClicking.clickRSObject(a, "Open")) {
						Timing.waitCondition(Condi.canReach(towertile2), General.random(5000, 9000));
					}
				} while (PathFinding.canReach(towertile2, false));
			}
		}

	}

	public void finishQuest() {
		
		if (Magic.selectSpell("Lumbridge Home Teleport"));
		Timing.waitCondition(Condi.areaContains(Area6), General.random(13000, 15000));
		
		if (WebWalking.walkTo(Area2.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area2), General.random(15000, 18000));

		if (Doors.isDoorAt(door2, false)) {
			do {
				if (Doors.handleDoorAt(door2, true))
					Timing.waitCondition(Condi.canReach(crypttile), General.random(2000, 3000));
			} while (!PathFinding.canReach(crypttile, false));
		}
		RSObject[] coffin = Objects.findNearest(10, Filters.Objects.nameContains("Coffin"));
		
		if (DynamicClicking.clickRSObject(coffin[0], "Open"));
		ItemUsing.usingItemOnObject("Ghost's skull", "Coffin", "Ghost's skull ->", "Ghost's skull -> Coffin", 5);
	}
}
