package com.caved_in.commonsexamples.minigame.game;

import com.caved_in.commons.game.MiniGameState;
import com.caved_in.commons.player.Players;

public class Game {

    /*
    All the values used to make the game run!
    Tossing them in a separate class generally helps, incase you need to tweak it,
    it's easy :)
     */
    public static class Settings {
        /* How many players it takes to start a round of tag */
        public static final int MIN_PLAYER_COUNT = 3;

        /* How many tags can be made before the round is over */
        public static final int MAX_TAGS_PER_ROUND = 20;
    }

    /*
    The stats that are for the current round of tag.

    These stats will be reset at the end of each round.
     */
    public static class Stats {
        public static int TAGS_CURRENT_ROUND = 0;

        public static void clear() {
            TAGS_CURRENT_ROUND = 0;
        }
    }
}
