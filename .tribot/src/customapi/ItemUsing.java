package customapi;

import org.tribot.api.Clicking;
import org.tribot.api.DynamicClicking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;

public class ItemUsing {

	public static void usingItemOnObject(String itemname, String objname, String upText, String upTextOverObject,
			int distance) {

		RSItem[] item = Inventory.find(Filters.Items.nameEquals(itemname));
		RSObject[] object = Objects.findNearest(distance, Filters.Objects.nameEquals(itemname));
		do {
			if (Clicking.click("Use", item))
				;
		} while (Game.getItemSelectionState() == 0);

		if (Game.isUptext(upText)) {
			object[0].hover();
			if (Game.getUptext().equals(upTextOverObject)) {
				do {
					DynamicClicking.clickRSObject(object[0], 1);
				} while (Game.getItemSelectionState() == 1);
			}
		}
	}

	public static void usingItemOnItem(String itemname, String upText, String upTextOverObject, int distance) {

		RSItem[] item = Inventory.find(Filters.Items.nameEquals(itemname));
		RSItem[] item2 = Inventory.find(Filters.Items.nameEquals(itemname));
		do {
			if (Clicking.click("Use", item))
				;
		} while (Game.getItemSelectionState() == 0);

		if (Game.isUptext(upText)) {
			item2[0].hover();
			if (Game.getUptext().equals(upTextOverObject)) {
				do {
					Clicking.click(item2[0]);
				} while (Game.getItemSelectionState() == 1);
			}
		}
	}

/*public static boolean operateRoD(String locationChoice) {
	
	
	RSInterfaceChild RING_OF_DUELING_TELEPORT = Interfaces.get(230, 1);
	 
	 (RSInterface children : RING_OF_DUELING_TELEPORT.getParent().getChildren()) {				
		 
		 if (children.getText().contains(locationChoice)) {					
			  
			 return RING_OF_DUELING_TELEPORT.getParent().getChild(children.getIndex()).click(locationChoice);
		  }
	  }
 */  //}
}
