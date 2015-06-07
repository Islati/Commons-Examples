package com.caved_in.commonsexamples.minigame.game.state;

import com.caved_in.commons.game.MiniGameState;
import com.caved_in.commons.inventory.ArmorBuilder;
import com.caved_in.commons.inventory.ArmorInventory;
import com.caved_in.commons.inventory.ArmorSlot;
import com.caved_in.commons.item.ArmorSet;
import com.caved_in.commons.item.Items;
import com.caved_in.commons.player.Players;
import com.caved_in.commons.time.BasicTicker;
import com.caved_in.commons.utilities.ArrayUtils;
import com.caved_in.commons.utilities.ListUtils;
import com.caved_in.commons.utilities.NumberUtil;
import org.bukkit.Color;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class EndGameState extends MiniGameState{
    private BasicTicker gameTickCounter = new BasicTicker(15);

    private List<ArmorInventory> discoSuits = new ArrayList<>();

    @Override
    public void update() {
        gameTickCounter.tick();

        /*
         * We're going to give all the players disco suits! Make them have
         * a random color of leather armor every update tick! :D
         */
        Players.stream().forEach(p -> {
            Players.setArmor(p, ListUtils.getRandom(discoSuits));
        });
    }

    @Override
    public int id() {
        return 3;
    }

    @Override
    public boolean switchState() {
        return gameTickCounter.allow();
    }

    @Override
    public int nextState() {
        return 1;
    }

    @Override
    public void setup() {
        /*
        Generate 10 sets of colored leather armor! :)
         */
        for (int i = 0; i < 10; i++) {
            ArmorInventory inv = new ArmorInventory();
            Color color = Color.fromRGB(NumberUtil.getRandomInRange(1, 255), NumberUtil.getRandomInRange(1, 255), NumberUtil.getRandomInRange(1, 255));

            inv.setItem(ArmorSlot.HELMET, Items.makeLeatherItem(Material.LEATHER_HELMET, color));
            inv.setItem(ArmorSlot.CHEST,Items.makeLeatherItem(Material.LEATHER_CHESTPLATE, color));
            inv.setItem(ArmorSlot.LEGGINGS,Items.makeLeatherItem(Material.LEATHER_LEGGINGS, color));
            inv.setItem(ArmorSlot.BOOTS,Items.makeLeatherItem(Material.LEATHER_BOOTS, color));

            discoSuits.add(inv);
        }
    }

    @Override
    public void destroy() {
        gameTickCounter.reset();
    }
}
