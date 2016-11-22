package scripts.JKGCowKiller;

import org.tribot.api.util.abc.ABCUtil;
import org.tribot.script.Script;

import scripts.JKGCowKiller.Tasks.Attacking;
import scripts.JKGCowKiller.Tasks.BankingTask;
import scripts.JKGCowKiller.Tasks.Eating;
import scripts.JKGCowKiller.Tasks.TravelToBank;
import scripts.JKGCowKiller.Tasks.TravelToField;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Framework.TaskSet;

public class JKGCowKiller extends Script {
	boolean run = true;
	String status = "";
	TaskSet taskSet = new TaskSet();
	
	public static ABCUtil abc = new ABCUtil();
	
	@Override
	public void run() {
		taskSet.addAll(new Attacking(), new BankingTask(), new Eating(), new TravelToBank(), new TravelToField());
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