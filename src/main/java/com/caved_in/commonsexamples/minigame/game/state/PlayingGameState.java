package com.caved_in.commonsexamples.minigame.game.state;

import com.caved_in.commons.chat.Title;
import com.caved_in.commons.chat.TitleBuilder;
import com.caved_in.commons.fireworks.Fireworks;
import com.caved_in.commons.game.MiniGameState;
import com.caved_in.commons.player.Players;
import com.caved_in.commonsexamples.minigame.game.Game;

public class PlayingGameState extends MiniGameState{
    private Title startTitle = TitleBuilder.create().title("&6&lGame Start!").subtitle("&aTag Everything!").fadeIn(1).fadeOut(2).stay(3).build();
    private Title stopTitle = TitleBuilder.create().title("&c&lGame Over!").subtitle("&eNext Round in 10 Seconds").fadeIn(1).fadeOut(2).stay(3).build();

    @Override
    public void update() {

    }

    @Override
    public int id() {
        return 2;
    }

    @Override
    public boolean switchState() {
        if (Game.Stats.TAGS_CURRENT_ROUND > Game.Settings.MAX_TAGS_PER_ROUND) {
            return true;
        }
        return false;
    }

    @Override
    public int nextState() {
        return 3;
    }

    @Override
    public void setup() {
        /*
        Broadcast the title to everyone on the server :)
         */
        startTitle.broadcast();
    }

    @Override
    public void destroy() {
        stopTitle.broadcast();

        /*
        Loop through all the players online, via lambda streams, and play
        a random Firework Effect at their location!

        Celebrate the win?! :D
         */
        Players.stream().forEach(p -> {
            Fireworks.playFirework(p.getLocation(),Fireworks.randomFireworkEffect());
        });
    }
}
