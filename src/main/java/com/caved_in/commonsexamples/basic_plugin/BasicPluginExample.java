package com.caved_in.commonsexamples.basic_plugin;

import com.caved_in.commons.plugin.BukkitPlugin;
import com.caved_in.commonsexamples.basic_plugin.gadget.ChickenLauncher;
import com.caved_in.commonsexamples.basic_plugin.listeners.WelcomeListener;

/*
Normal bukkit plugins extends the JavaPlugin class, though commons
wraps this class and provides extra functionality on top of it through
the BukkitPlugin class.

New additions ontop of the BukkitPlugin class are:
    - A(n) XML Serializer instance

    - A sync and async scheduled executor service which
      can be used to create futures and promises,
      scheduled listeners, and a load more goodies to be explored!

    - A scoreboard manager! The board manager is an easy way to provide
      scoreboards on a per-player basis -or globally- with a load of syntactic sugar goodness,
      through builders and wrapped elements. Handles all the leg-work that goes with registering
      and handling scoreboard data, leaving you to just control the values and operations on
      the board itself.

    - A runnable manager; Provides an easy-to-use manager for threads, that can be registered
      and indexed with a string-based key (or integer id), and by using it
      commons automagically handles the initialization & destruction of threads.

    - An ItemMessage class (Supplied by desht) that allows you to send messages to a player
      (if protocollib is enabled on the server) through using ItemMeta and packets;
      Differs from the action bar & chat as it shows where the item-name's would be shown.

    - A Command Handler, which handles ALL the leg-work of our delightful annotation based
      command system (to be displayed in later examples)
 */
public class BasicPluginExample extends BukkitPlugin {

    @Override
    public void startup() {

        /*
        Register our welcome listener, it's going to give the player 2 welcome cakes when they join,
        and send them a message saying hello!

        The registerListners method actually has var-args, so you can register an
        unlimited amount of listeners for the server without repeated calls of the method...
        YAY!
         */
        registerListeners(new WelcomeListener());

        /*
        Register our chicken launcher gadget so we can start spreading some foul-love to all those
        who cross our path; More info on the chicken lancher and gadget creation in it's respective class!
         */
        registerGadgets(ChickenLauncher.getInstance());
    }

    @Override
    public void shutdown() {

    }

    @Override
    public String getVersion() {
        return "1.0 - Test";
    }

    @Override
    public String getAuthor() {
        return "Brandon Curtis - TheGamersCave";
    }

    @Override
    public void initConfig() {

    }
}
