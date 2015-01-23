package com.caved_in.commonsexamples.basic_plugin.command;

import com.caved_in.commons.command.Arg;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.player.Players;
import com.caved_in.commonsexamples.basic_plugin.gadget.ChickenLauncher;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandsExample {

    /*
    Again, we have our welcome cake; we'll be giving it to our player!
     */
    private ItemStack welcomeCake = ItemBuilder.of(Material.CAKE)
            .name("&aWelcome Cake!")
            .lore("&7Welcome to our server","&7Here's a cake to show thanks")
            .item();

    /*
    Below we create the /cake [amount=1] command.
    Using the @Command annotaion we remove all required boiler plating
    that would normally happen when you create the command through your onCommand
    method.

    It also allows you to modularize and organize your code.

    Another neat-o example is the @Arg() annotation!

    This allows us to retrieve objects of data without the need of parsing strings
    for values.

    By default it can handle the following objects:
        - Strings
        - Integers
        - Doubles
        - Booleans (true false 1 0)
        - Enchantments (name)
        - EntityType (name)
        - ItemStack (id, name, id:value)
        - Material (id / name)
        - MaterialData (id:value)
        - Player (name)
        - World (name)

        Note:
            The objects in brackets are how the values are determined,
            and their valid formatting; Not not what it returns.

    You can create your own argument handlers, and In other examples I'll show how to
    do so.
     */
    @Command(identifier = "cake", onlyPlayers = true)
    public void onCakeCommand(Player p, @Arg(name = "amount",def = "1")int amount) {
        /*
        Though, through commons we make it SOOOOOOPER easy to give players items.
        We'll use the item builder to modify the cake (and set the amount to give to the player)
         */
        Players.giveItem(p,ItemBuilder.of(welcomeCake).amount(amount).item());
    }

    /*
    Now, this command is used to give ourselves a copy of the chicken wand,
    though be careful when spawning dark overlord baby-chickens, they tend to have a temper!
     */
    @Command(identifier = "chickenwand",onlyPlayers = true,permissions = {"commons.example.chickenwand"})
    public void onChickenWandCOmmand(Player p) {
        Players.giveItem(p, ChickenLauncher.getInstance().getItem());
    }
}
