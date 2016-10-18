package scripts.JKGRunecrafter.Altars;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.ext.Filters.Items;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;

public class FireAltar extends AbstractAltar {

	private final RSArea bankArea = new RSArea(new RSTile(3381, 3269, 0), new RSTile(3383, 3267, 0));
	private final RSArea altarArea = new RSArea(new RSTile(2560, 4860, 0), new RSTile(2603, 4824, 0));
	private final RSTile altarLocation = new RSTile(3312, 3254, 0);

	@Override
	public int index() {
		return 3;
	}

	@Override
	public RSTile getAltarLocation() {
		return altarLocation;
	}

	@Override
	public RSArea getBankArea() {
		return bankArea; // duel arena
	}

	@Override
	public String getRuneName() {
		return "Fire";
	}

	@Override
	public Filter<RSItem> getTiaraID() {
		return Items.nameEquals("Fire tiara");
	}

	@Override
	public int getTalismanID() {
		return 1442;
	}

	@Override
	public RSArea getAltarArea() {
		return altarArea;
	}

	@Override
	public boolean requirePureEssence() {
		return false;
	}
}
