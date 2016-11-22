package scripts;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

import customapi.Condi;
import customapi.Door;
import customapi.TalkNpc;

@ScriptManifest(authors = { "Jkg58" }, category = "Quests", name = "Waterfall Quest")
public class WaterfallQuest extends Script {

	int bookcase2 = 1989;

	RSArea Area1 = new RSArea(new RSTile(2513, 3493, 0), new RSTile(2515, 3496, 0));
	RSArea Area2 = new RSArea(new RSTile(2522, 3430, 0), new RSTile(2524, 3434, 0));
	RSArea Area3 = new RSArea(new RSTile(2516, 3427, 0), new RSTile(2519, 3431, 0));
	RSArea Area4 = new RSArea(new RSTile(2501, 3193, 0), new RSTile(2505, 3190, 0));
	RSArea Area5 = new RSArea(new RSTile(2535, 3156, 0), new RSTile(2538, 3155, 0));
	RSArea Area6 = new RSArea(new RSTile(2555, 3446, 0), new RSTile(2557, 3443, 0));
	RSArea Area7 = new RSArea(new RSTile(2520, 3494, 0), new RSTile(2522, 3497, 0));

	RSTile gate = new RSTile(2513, 3494, 0);
	RSTile door = new RSTile(2521, 3432, 0);

	RSTile rocks = new RSTile(2512, 3468, 0);
	RSTile bookcase1 = new RSTile(2520, 3426, 0);

	RSTile rafttile = new RSTile(2511, 3494, 0);
	RSTile waterfalltile = new RSTile(2512, 3481, 0);
	RSTile waterfalltile2 = new RSTile(2512, 3476, 0);
	RSTile waterfalltile3 = new RSTile(2513, 3468, 0);
	RSTile housetile = new RSTile(2519, 3432, 0);
	RSTile housetile2 = new RSTile(2518, 3431, 1);
	RSTile stairs = new RSTile(2517, 3429, 0);
	RSTile cavetile = new RSTile(2534, 9555, 0);
	RSTile cavetile2 = new RSTile(2515, 9574, 0);

	@Override
	public void run() {

		// startQuest();
		// raftToWaterfall();
		// talkToHudon();
		// DownTheWaterfall();
		// walkToHouse();
		// talkToHadley();
		// upstairsHouse();
		// readBook();
		// maze();
		// underTheMaze();
		// talkToGolrie();
		// walkToTomb();
		// cave();
		// backToWaterfall();
		// DownTheWaterfall2();
		// finishQuest();

	}

	public void startQuest() {

		TalkNpc.talkToNpc("Almera");
		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		NPCChat.selectOption("How can I help?", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
	}

	public void raftToWaterfall() {

		if (Walking.walkTo(Area1.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area1), General.random(6000, 9000));

		Door.openDoor(gate, rafttile);

		RSObject[] raft = Objects.findNearest(10, Filters.Objects.nameContains("Log raft"));
		if (DynamicClicking.clickRSObject(raft[0], "Board")) {
			Timing.waitCondition(Condi.canReach(waterfalltile), General.random(5000, 6000));
		}
		
		General.sleep(3000, 4000);

	}

	public void talkToHudon() {

		if (Player.getPosition() == waterfalltile)
			TalkNpc.talkToNpc("Hudon");
		NPCChat.clickContinue(true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
	}

	public void DownTheWaterfall() {

		Walking.walkTo(waterfalltile2);

		Camera.turnToTile(waterfalltile3);
		Camera.setCameraAngle(General.random(42, 50));

		RSItem[] rope = Inventory.find(Filters.Items.nameEquals("Rope"));
		RSObject[] rock = Objects.findNearest(15,Filters.Objects.nameEquals("Rock").combine(Filters.Objects.tileEquals(rocks), true));

		if (rope.length > 0)

			if (Clicking.click(rope))

				if (DynamicClicking.clickRSObject(rock[0], 1))
					Timing.waitCondition(Condi.playerPosition(waterfalltile3), General.random(30000, 50000));

		RSObject[] deadtree = Objects.findNearest(5, Filters.Objects.nameEquals("Dead tree"));

		if (Clicking.click(rope))

			if (DynamicClicking.clickRSObject(deadtree[0], 1))

				General.sleep(6000, 8000);

		RSObject[] barrel = Objects.findNearest(10, Filters.Objects.nameContains("Barrel"));
		DynamicClicking.clickRSObject(barrel[0], "Get in");
	}

	public void walkToHouse() {

		if (WebWalking.walkTo(Area2.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area2), General.random(6000, 9000));

		Door.openDoor(door, housetile);
		Camera.setCameraRotation(90);
		Camera.setCameraAngle(61);

		if (WebWalking.walkTo(Area3.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area3), General.random(4000, 6000));
	}

	public void talkToHadley() {

		TalkNpc.talkToNpc("Hadley");
		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
		NPCChat.selectOption("Can you tell me what happened to the elf king?", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
		NPCChat.selectOption("Where else is worth visiting around here?", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
		NPCChat.selectOption("Is there treasure under the waterfall?", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
		NPCChat.selectOption("Thanks then, goodbye.", true);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		General.sleep(500, 1000);
	}

	public void upstairsHouse() {

		RSObject[] staircase = Objects.findNearest(5, Filters.Objects.nameEquals("Staircase"));

		Camera.turnToTile(stairs);
		if (DynamicClicking.clickRSObject(staircase[0], "Climb-up")) {
			Timing.waitCondition(Condi.canReach(housetile2), General.random(5000, 6000));

		}
		while (PathFinding.canReach(housetile2, false))

			General.sleep(3000, 5000);
		
		RSObject[] bookcase = Objects.findNearest(10, Filters.Objects.nameContains("Bookcase"));
		DynamicClicking.clickRSObject(bookcase[1], "Search");

		// RSObject[] bookcase =
		// Objects.findNearest(10,Filters.Objects.nameContains("Bookcase").combine(Filters.Objects.actionsContains("Search"),
		// true));

		// for (RSObject a : bookcase) {
		// if (a.getPosition().equals(bookcase1))

		// DynamicClicking.clickRSObject(a, "Search");

		// RSObject[] bookcase1 = Objects.findNearest(10,
		// Filters.Objects.nameEquals("Bookcase"));
		// RSObject[] bookcase = Objects.findNearest(10, bookcase2);
		// if (Player.getPosition() == housetile2)
		// DynamicClicking.clickRSObject (bookcase[0],"Search");

	}

	public void readBook() {

		RSItem[] book = Inventory.find(Filters.Items.nameEquals("Book on baxtorian"));

		if (book.length > 0)

			Clicking.click(book);
		General.sleep(2000);

		Interfaces.get(49, 85).click("Next Page");
		General.sleep(500, 1000);
		Interfaces.get(49, 85).click("Next Page");
		General.sleep(500, 1000);
		Interfaces.get(49, 85).click("Next Page");
		General.sleep(500, 1000);
		Interfaces.get(49, 85).click("Next Page");
		General.sleep(2000, 3000);
		Interfaces.get(49, 113).click("Ok");
		General.sleep(2000, 3000);

	}

	public void maze() {

		// tele to castle wars

		if (WebWalking.walkTo(Area4.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area4), General.random(6000, 9000));

		if (WebWalking.walkTo(Area5.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area5), General.random(6000, 9000));

		RSObject[] ladder = Objects.findNearest(10, Filters.Objects.nameEquals("Ladder"));

		if (DynamicClicking.clickRSObject(ladder[0], "Climb-down"))
			Timing.waitCondition(Condi.canReach(cavetile), General.random(5000, 6000));

	}

	public void underTheMaze() {

		// search boxes
		
		WebWalking.walkTo(cavetile2);

		RSObject[] cavegate = Objects.findNearest(10, Filters.Objects.nameEquals("Door"));
		RSItem[] key = Inventory.find(Filters.Items.nameEquals("A key"));

		if (Clicking.click(key))

			if (DynamicClicking.clickRSObject(cavegate[0], 1))

				General.sleep(6000, 8000);

	}

	public void talkToGolrie() {

		TalkNpc.talkToNpc("Golrie");
		NPCChat.clickContinue(false);
		General.sleep(500, 1000);
		TalkNpc.continueLoop();
		Timing.waitCondition(Condi.dialogCond(), General.random(7000, 8000));
		TalkNpc.continueLoop();
		Timing.waitCondition(Condi.dialogCond(), General.random(4000, 5000));
		TalkNpc.continueLoop();
	}

	public void walkToTomb() {

		//teleport to barb outpost

		if (WebWalking.walkTo(Area6.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area6), General.random(6000, 9000));

		RSObject[] tomb = Objects.findNearest(10, Filters.Objects.nameEquals("Glarial's tombstone"));
		RSItem[] pebble = Inventory.find(Filters.Items.nameEquals("Glarial's pebble"));

		if (Clicking.click(pebble))

			if (DynamicClicking.clickRSObject(tomb[0], 1))

				General.sleep(6000, 8000);

	}

	public void cave() {

		// get items
		// back out

	}

	public void backToWaterfall() {

		if (WebWalking.walkTo(Area7.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area7), General.random(6000, 9000));

		if (Walking.walkTo(Area1.getRandomTile()))
			Timing.waitCondition(Condi.areaContains(Area1), General.random(6000, 9000));

		Door.openDoor(gate, rafttile);

		RSObject[] raft = Objects.findNearest(10, Filters.Objects.nameContains("Log raft"));
		if (DynamicClicking.clickRSObject(raft[0], "Board")) {
			Timing.waitCondition(Condi.canReach(waterfalltile), General.random(5000, 6000));
		}
		General.sleep(3000, 4000);

	}

	public void DownThwWaterfall2() {

		Walking.walkTo(waterfalltile2);

		Camera.turnToTile(waterfalltile3);
		Camera.setCameraAngle(General.random(42, 50));

		RSItem[] rope = Inventory.find(Filters.Items.nameEquals("Rope"));
		RSObject[] rock = Objects.findNearest(15,
				Filters.Objects.nameEquals("Rock").combine(Filters.Objects.tileEquals(rocks), true));

		if (rope.length > 0)

			if (Clicking.click(rope))

				if (DynamicClicking.clickRSObject(rock[0], 1))
					Timing.waitCondition(Condi.playerPosition(waterfalltile3), General.random(30000, 50000));

		RSObject[] deadtree = Objects.findNearest(5, Filters.Objects.nameEquals("Dead tree"));

		if (Clicking.click(rope))

			if (DynamicClicking.clickRSObject(deadtree[0], 1))

				General.sleep(6000, 8000);
		
		RSObject[] ledge = Objects.findNearest(10, Filters.Objects.nameContains("Ledge"));
		DynamicClicking.clickRSObject(ledge[0], "Open");
		
	}

	public void finishQuest() {

		// search boxes
		// key on door
		// runes on pillars
		// necklace on statue urn on goblet

	}

}
