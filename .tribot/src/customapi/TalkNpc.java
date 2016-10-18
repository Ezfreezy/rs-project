package customapi;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSNPC;


public class TalkNpc {

	public static void talkToNpc(String npcname) {
		RSNPC[] npc = NPCs.findNearest(Filters.NPCs.nameEquals(npcname));

		do {
			if (DynamicClicking.clickRSNPC(npc[0], "Talk-to"))
				Timing.waitCondition(Condi.dialogCond(), General.random(5000, 6000));
		} 
		while (NPCChat.getClickContinueInterface() == null);
	}

	public static void continueLoop() {

		do {
			NPCChat.clickContinue(true);
			General.sleep(500, 1000);

		} while (NPCChat.getClickContinueInterface() != null);

	}

}
