package com.caved_in.commonsexamples.minigame;

import com.caved_in.commons.game.MiniGame;
import com.caved_in.commonsexamples.minigame.commands.GameCommands;
import com.caved_in.commonsexamples.minigame.game.state.EndGameState;
import com.caved_in.commonsexamples.minigame.game.state.PendingGameState;
import com.caved_in.commonsexamples.minigame.game.state.PlayingGameState;
import com.caved_in.commonsexamples.minigame.listener.UserConnectionListener;
import com.caved_in.commonsexamples.minigame.users.GamePlayerManager;

/*
 * Create the base for your minigame by extending the MiniGame class.
 * This class extends BukkitPlugin, and takes an instance of a UserManager to
 * be initialized.
 *
 * Refer to the Javadocs for more information on the specifics.
 *
 * Essentially, it provides a state manager, automatic player data handling,
 * an arena manager, a neat-o gadget system for custom items / actions for items.
 */
public class MiniGameExample extends MiniGame<GamePlayerManager> {
    @Override
    public void startup() {
        /*
        Register our user manager class, to handle all incoming player data, automagically.
         */
        registerUserManager(GamePlayerManager.class);

        /*
        Register our game states, the ones that control what's up!
         */
        registerGameStates(
                new PendingGameState(),
                new PlayingGameState(),
                new EndGameState()
        );

        /*
        Register the commands used to manage and control aspects of the game,
        like arenas, spawns, rounds, etc!.
         */
        registerCommands(
                new GameCommands()
        );

        registerListeners(
                new UserConnectionListener()
        );
    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getAuthor() {
        return "Brandon Curtis";
    }

    @Override
    public void initConfig() {

    }

    @Override
    public long tickDelay() {
        return 20;
    }
}
