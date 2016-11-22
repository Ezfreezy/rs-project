package scripts.JKGMiner;

import org.tribot.script.Script;

import scripts.JKGMiner.Tasks.BankingTask;
import scripts.JKGMiner.Tasks.MineOre;
import scripts.JKGMiner.Tasks.TravelToBank;
import scripts.JKGMiner.Tasks.TravelToMine;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Framework.TaskSet;

public class JKGMiner extends Script {
	boolean run = true;
	String status = "";
	TaskSet taskSet = new TaskSet();
	
	@Override
	public void run() {
		taskSet.addAll(new BankingTask(), new MineOre(), new TravelToBank(), new TravelToMine());
		while (run) {
			sleep(60);
			Task task = taskSet.getValidTask();
			if (task != null) {
				status = task.toString();
				task.execute();
			}
		}
	}
}