package com.caved_in.commonsexamples.minigame.listener;

import com.caved_in.commons.game.MiniGame;
import com.caved_in.commons.game.event.UserJoinEvent;
import com.caved_in.commonsexamples.minigame.MiniGameExample;
import com.caved_in.commonsexamples.minigame.users.GamePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/*
This listener will attach to a few Events related specifically to that of the
UserManager / Commons suite-

Whenever a player joins, through a minigame in this case, they have a custom user object
added to a manager (UserManager) to hold data specific to this player.

Whether it be a custom inventory, their team, their stats, etc- The best way to work
with players and their data is to have an object per-player, if it's an easy thing like
the "User" class, then you'll have no problem!

The User class DOESN'T use a WeakReference for the player, rather it caches their UUID and
calls a method to retrieve the player when called upon- So if the user isn't online their saved data
could still potentially be saved, handled, etc.
 */
public class UserConnectionListener implements Listener {

    @EventHandler
    public void onUserJoin(UserJoinEvent e) {
        /*
        Assure that the plugin calling this event is an instance of the MiniGameExample
        plugin, miningame, as it's best to not muck with the data of other plugins.

         */
        if (!(e.getPlugin() instanceof MiniGameExample)) {
            return;
        }

        /*
        Though when the plugin is ours, we know we can cast it to the GamePlayer
        and that's what we're listning on :)
         */
        GamePlayer player = (GamePlayer)e.getUser();

        player.joined();
    }

}
