package com.caved_in.commonsexamples.minigame;

import com.caved_in.commons.game.MiniGame;
import com.caved_in.commonsexamples.minigame.users.GamePlayerManager;

public class MiniGameExample extends MiniGame<GamePlayerManager> {
    @Override
    public void startup() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public void initConfig() {

    }

    @Override
    public long tickDelay() {
        return 0;
    }
    /*
    This example will be completed in the next patch, displaying a simple TAG Minigame!
     */
}
