package com.caved_in.commonsexamples.minigame.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.chat.Chat;
import com.caved_in.commons.command.Arg;
import com.caved_in.commons.command.Command;
import com.caved_in.commons.command.FlagArg;
import com.caved_in.commons.command.Flags;
import com.caved_in.commons.game.world.Arena;
import com.caved_in.commons.game.world.ArenaManager;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.menu.ItemFormat;
import com.caved_in.commons.menu.Menus;
import com.caved_in.commons.menu.PageDisplay;
import com.caved_in.commons.player.Players;
import com.caved_in.commonsexamples.minigame.MiniGameExample;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GameCommands {
    private static MiniGameExample game = MiniGameExample.getPlugin(MiniGameExample.class);

    private static HelpScreen screen = Menus.generateHelpScreen("MiniGame Example Commands", PageDisplay.DEFAULT, ItemFormat.SINGLE_DASH, ChatColor.YELLOW,ChatColor.GOLD)
            .addEntry("game","Base for all the commands")
            .addEntry("game ?","Add a spawn point to the current arena")
            .addEntry("game add-spawn","Add a spawn point to the current arena")
            .addEntry("game add-arena","Add an arena to the list of potential arenas.")
            .addEntry("game set-arena","Set the arena that's currently in use.");


    @Command(identifier = "game")
    public void onMurderCommand(Player player) {
        Chat.message(player, "&eUse &a/game ?&e to view the command help menu, or &a/game help&e for command syntax");
    }

    @Command(identifier = "game ?")
    public void onMurderHelpCommand(Player player, @Arg(name = "page", def = "1")int page) {
        screen.sendTo(player,page,7);
    }

    @Command(identifier = "game add-spawn")
    @Flags(identifier = "-cursor", description = "location of your cursor, instead of where you're standing.")
    public void onAddSpawnCommand(Player player, @FlagArg("-cursor") boolean cursor) {
        ArenaManager manager = game.getArenaManager();
        Location loc = null;

        if (cursor) {
            loc = Players.getTargetLocation(player, 100);
        } else {
            loc = player.getLocation();
        }

        manager.addSpawnToActiveArena(loc);
        Chat.message(player, Messages.arenaSpawnAdded(manager.getActiveArena(), loc));
    }

    @Command(identifier = "game add-arena")
    public void onAddArenaCommand(Player p, @Arg(name = "world") String world) {
        ArenaManager manager = game.getArenaManager();
        boolean success = manager.addArena(world);
        if (!success) {
            Chat.message(p, Messages.arenaAddError(world));
            return;
        }

        Chat.message(p, Messages.arenaAdded(world));
    }

    @Command(identifier = "game set-arena")
    public void onSetArenaCommand(Player p, @Arg(name = "world") World world) {
        ArenaManager manager = game.getArenaManager();
        Arena arena = manager.getArena(world);
        manager.setActiveArena(arena);
        manager.teleportAllToArena();
        Chat.message(p, String.format("&aArena cycled to &e%s", arena.getArenaName()));
    }
}
