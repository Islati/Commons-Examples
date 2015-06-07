package com.caved_in.commonsexamples.basic_plugin.listeners;

import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.player.Players;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

/*
Listeners are still created and handled as they are in Bukkit.
 */
public class WelcomeListener implements Listener {
    /*
    Using the ItemBuilder class, we're going to make a "welcome cake" for the players;
    whenever they join they'll be given the cake!
     */
    private ItemStack welcomeCake = ItemBuilder.of(Material.CAKE)
            .name("&aWelcome Cake!")
            .lore("&7Welcome to our server","&7Here's a cake to show thanks")
            .item();


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        /*
        Using the Chat class, send the player who joined a simple "Hello!"

        To view all the available methods for Chat (and other classes) be sure to
        view the java docs.
         */
        Chat.sendMessage(e.getPlayer(),"Hello!");

        /*
        Set the players first item (0-based index) on their hotbar to be
        a cake; Cause who doesn't love cake? :)
         */
        Players.setHotbarItem(p,welcomeCake,0);

        /*
        Because we're feeling generous, we'll give them another cake, but if their inventories
        full we'll actually want to drop the cake next to them!

        The first arg is the player recieving the cake,
        the second the item that we're giving them (this case, it's the cake)
        and lastly we set the drop value to true; If set to true
        and the players inventory is full, it'll drop the item at the players feet
         */
        Players.giveItem(p,welcomeCake,true);
    }
}
