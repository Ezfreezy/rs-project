package scripts.JKGMiner.Tasks;

import scripts.JkgAPI.Framework.Task;

public class MineOre implements Task {

	@Override
	public int priority() {
		return 0;
	}

	@Override
	public boolean validate() {
		return false;
	}

	@Override
	public void execute() {
	}
}
