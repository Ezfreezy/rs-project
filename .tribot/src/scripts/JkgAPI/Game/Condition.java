package scripts.JkgAPI.Game;

import org.tribot.api.General;
import org.tribot.api2007.Banking;

public abstract class Condition extends org.tribot.api.types.generic.Condition {

	public static Condition UntilBankOpen = new Condition() {
		public boolean active() {
			General.sleep(50);
			return Banking.isBankScreenOpen();
		}
	};

}