package examples;

import org.tribot.api.General;
import org.tribot.api2007.Walking;
import org.tribot.api2007.types.RSTile;

public class Movement {

	public static void walk() {
		final RSTile[] path = new RSTile[] {
				new RSTile(3217, 3429, 0),
				new RSTile(3227, 3430, 0),
				new RSTile(3237, 3429, 0),
				new RSTile(3247, 3429, 0),
				new RSTile(3257, 3429, 0),
				new RSTile(3267, 3429, 0),
				new RSTile(3277, 3428, 0),
				new RSTile(3285, 3434, 0),
				new RSTile(3286, 3444, 0),
				new RSTile(3290, 3454, 0),
				new RSTile(3296, 3462, 0),
				new RSTile(3306, 3464, 0),
				new RSTile(3316, 3465, 0),
				new RSTile(3326, 3469, 0),
				new RSTile(3334, 3475, 0),
				new RSTile(3343, 3480, 0),
				new RSTile(3351, 3486, 0),
				new RSTile(3361, 3487, 0),
				new RSTile(3371, 3485, 0),
				new RSTile(3381, 3484, 0),
				new RSTile(3391, 3485, 0),
				new RSTile(3401, 3487, 0)
		};
		RSTile[] randomPath = Walking.randomizePath(path, 1, 1);
		Walking.walkPath(path);
		
		int i = General.random(1, 5);
		
		switch(i) {
		case(1) : {
			
		}
		}
	}
	
}
