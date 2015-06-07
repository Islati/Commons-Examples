package com.caved_in.commonsexamples.minigame.game.state;

import com.caved_in.commons.game.MiniGameState;
import com.caved_in.commons.player.Players;
import com.caved_in.commonsexamples.minigame.game.Game;

public class PendingGameState extends MiniGameState {
    @Override
    public void update() {

    }

    @Override
    public int id() {
        return 1;
    }

    @Override
    public boolean switchState() {
        /*
        If there's less than the minimum amount of players online, then
        we can cancel the event.

        Otherwise, it's gonna send it to the next state!
         */
        if (!Players.isOnline(Game.Settings.MIN_PLAYER_COUNT)) {
            return false;
        }

        return true;
    }

    @Override
    public int nextState() {
        return 2;
    }

    @Override
    public void setup() {

    }

    @Override
    public void destroy() {

    }
}
