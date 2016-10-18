package scripts.JKGRunecrafter;

import org.tribot.script.Script;

import scripts.JKGRunecrafter.Altars.AbstractAltar;
import scripts.JKGRunecrafter.Altars.AirAltar;
import scripts.JKGRunecrafter.Altars.BodyAltar;
import scripts.JKGRunecrafter.Altars.EarthAltar;
import scripts.JKGRunecrafter.Altars.FireAltar;
import scripts.JKGRunecrafter.Altars.WaterAltar;
import scripts.JKGRunecrafter.Tasks.BankingTask;
import scripts.JKGRunecrafter.Tasks.CraftRunes;
import scripts.JKGRunecrafter.Tasks.EnterAltar;
import scripts.JKGRunecrafter.Tasks.ExitAltar;
import scripts.JKGRunecrafter.Tasks.FetchTiaraOrTalisman;
import scripts.JKGRunecrafter.Tasks.TravelToAltar;
import scripts.JKGRunecrafter.Tasks.TravelToBank;
import scripts.JkgAPI.Framework.Task;
import scripts.JkgAPI.Framework.TaskSet;

public class JKGRunecrafter extends Script {
	boolean run = true;
	String status = "";
	TaskSet taskSet = new TaskSet();
	
	public static AbstractAltar[] ActiveAltars = {
            new AirAltar(),
            new EarthAltar(),
            new FireAltar(),
            new WaterAltar(),
            new BodyAltar(),
    };

	@Override
	public void run() {
		taskSet.addAll(new BankingTask(), new CraftRunes(), new EnterAltar(), new ExitAltar(), new FetchTiaraOrTalisman(), new TravelToAltar(), new TravelToBank());
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
