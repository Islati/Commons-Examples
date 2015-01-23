package com.caved_in.commonsexamples.minigame.users;

import com.caved_in.commons.player.User;
import org.bukkit.entity.Player;

/*
The user class is a base for a player wrapper; Containing many methods used to
interact with the player, though most specifically used to handle player-specific data
in an easy, automated, non-convoluted manner.
 */
public class GamePlayer extends User {

    private long joinTime;

    /*
    The GamePlayer is auto-inialized via this method through the parent minigame class

    This normally wouldn't be called via your plugin, unless you're creating your own user manager implementation
    which would be suggested against unless you require specific features not offered by
    the default one in Commons.
     */
    public GamePlayer(Player p) {
        super(p);
    }

    /*
    The joined method is called in our playerconnectionlistener,
    and it sets the timestamp that the player joined the server
     */
    public void joined() {
        joinTime = System.currentTimeMillis();
    }

    /*
    Retrieve the timestamp at which the player joined, used
     */
    public long getJoinTime() {
        return joinTime;
    }
}
