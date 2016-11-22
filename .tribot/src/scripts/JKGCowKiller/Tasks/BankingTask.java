package scripts.JKGCowKiller.Tasks;

import scripts.JkgAPI.Framework.Task;

public class BankingTask implements Task {

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
