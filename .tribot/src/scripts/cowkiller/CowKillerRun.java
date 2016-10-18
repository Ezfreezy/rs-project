/*package scripts.cowkiller;

import java.util.ArrayList;
import java.util.Collections;

import org.tribot.api.General;
import org.tribot.script.Script;

import customapi.framework.TaskSet;
import scripts.cowkiller.methods.Attack;
import scripts.cowkiller.methods.Eat;

public class CowKillerRun extends Script {

	private boolean run = true;
	String status = "";
	TaskSet taskSet = new TaskSet();

	public static ArrayList<Node> nodes = new ArrayList<>();

	@Override
	public void run() {
		Collections.addAll(nodes, new Attack(), new Eat());
		loop(20, 40);
	}

	private void loop(int min, int max) {
		
		while (true) {
			
			for (final Node node : nodes) {
				
				if (node.validate()) {
					node.execute();
					sleep(General.random(min, max));
					
				}
			}
		}
	}
*///}
