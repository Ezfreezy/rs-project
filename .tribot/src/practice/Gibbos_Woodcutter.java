package practice;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

@ScriptManifest(authors = { "Jkg58" }, category = "Skills", name = "Gibbos Woodcutter", description = "Start in Lumbridge Church")
public class Gibbos_Woodcutter extends Script{

int[] normaltrees = {1276, 1278};
int axe_id = 1351;

RSTile banktile = new RSTile(3253, 3420);

Condition cuttingTree = new Condition() {
	@Override
	public boolean active() {
		General.sleep(1000, 1500);
		return Player.getAnimation() == 879;}
};

@Override
public void run() {
	
	while(loop());}

public boolean loop() {
		
	if(!Equipment.isEquipped(axe_id)) {
			equiptAxe();
			return true;}
		
	if(!Inventory.isFull()) {
			chop();
			return true;}
	else
			walkToBank();
			return true;}

public void equiptAxe() {
		
	RSItem[] axe = Inventory.find(axe_id);
		axe[0].click("Wield");
		}
	
public void chop() {
         RSObject[] tree = Objects.findNearest(10, normaltrees); 
    if(Player.getAnimation() == -1){
    if(DynamicClicking.clickRSObject (tree[0],"Chop down")) {
        	 Timing.waitCondition(cuttingTree, General.random(4000, 5000));}
     }
	 }
	 
public void walkToBank() {
		RSTile startingTile = Player.getPosition(); 
	if(WebWalking.walkTo(banktile)) {
	do{Banking.openBank();
	   General.sleep(2000, 3000);}
	while(!Banking.isBankScreenOpen());
			
			Banking.depositAll();
			General.sleep(400, 800);
			Banking.close();}
		WebWalking.walkTo(startingTile);}
	 
}