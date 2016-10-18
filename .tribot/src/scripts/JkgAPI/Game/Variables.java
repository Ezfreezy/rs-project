package scripts.JkgAPI.Game;

import scripts.JkgAPI.Core.Bag;

public class Variables {

    private static final Bag instance = new Bag();

    private Variables() {

    }

    public static Bag getInstance() {
        return instance;
    }
}
