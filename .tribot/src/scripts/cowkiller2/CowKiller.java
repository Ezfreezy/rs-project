package scripts.cowkiller2;

import java.util.ArrayList;
import java.util.Collections;

import org.tribot.api.General;
import org.tribot.script.Script;

import scripts.cowkiller2.Node;


public class CowKiller extends Script {
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
					sleep(General.random(100, 300)); // time in between
														// executing nodes
				}
			}
		}
	}
}
