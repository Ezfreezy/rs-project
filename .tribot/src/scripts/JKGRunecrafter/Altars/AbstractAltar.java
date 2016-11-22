package scripts.JKGRunecrafter.Altars;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;

public abstract class AbstractAltar {

	public abstract int index();

	public abstract RSTile getAltarLocation();

	public abstract RSArea getBankArea();

	public abstract RSArea getAltarArea();

	public abstract String getRuneName();

	public abstract Filter<RSItem> getTiaraID();

	public abstract int getTalismanID();

	public abstract boolean requirePureEssence();

}
