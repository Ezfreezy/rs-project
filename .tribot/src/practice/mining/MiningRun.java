/*package practice.mining;

import org.tribot.api.General;
import org.tribot.script.Script;

import customapi.framework.Task;
import customapi.framework.TaskSet;
import practice.mining.methods.Banking;
import practice.mining.methods.Mining;

public class MiningRun extends Script {

	boolean run = true;   
	String status = "";  
	TaskSet taskSet = new TaskSet(); 
	
	@Override 
	public void run() {  
		taskSet.addAll(new Mining(), new Banking());    
		while (run) {           
			General.sleep(50);       
			Task task = taskSet.getValidTask();        
			if (task != null) {           
				status = task.toString();      
				task.execute();       
				
			}   
			
		}  
		
	}
	
}*/

