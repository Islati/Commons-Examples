package com.caved_in.commonsexamples.basic_plugin.gadget;

import com.caved_in.commons.entity.CreatureBuilder;
import com.caved_in.commons.entity.Entities;
import com.caved_in.commons.game.gadget.ItemGadget;
import com.caved_in.commons.item.ItemBuilder;
import com.caved_in.commons.player.Players;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/*
The item gadgets class is a neat-o example of just how much work commons can save you!

Binding actions to items, without the need to specify individual verifiers in listeners
such as name checking; as all that is handled through the gadgets API!

Again: Just content, no boiler-plate.
 */
public class ChickenLauncher extends ItemGadget {

    private static ChickenLauncher instance = null;

    /*
    Using a singleton instance with gadgets is perfectly fine; We're not going to be re-registering the gadget
    at any point, and all actions performed have players passed to them, so there's no need to ever
    have more than one instance :)

    The other method of retrieving a gadget is based on it's id; calling Gadgets.getGadget(id)
     */
    public static ChickenLauncher getInstance() {
        if (instance == null) {
            instance = new ChickenLauncher();
        }
        return instance;
    }

    /*
        Normally spawning creatures involves numerous lines of boiler plating and
        then further modifying them with custom names is an even bigger pain, though
        through a neat-o builder interface we get to minimize this into a single
        head-ache saving statement!

        We're wanting all our chickens to spawn dark-overlord baby chickens with stupid amounts of HP!
         */
    private static CreatureBuilder chickenSpawner = new CreatureBuilder(EntityType.CHICKEN)
            .asBaby(true) //We want the chickens to be babies when they spawn, cause baby chickens are cute!
            .maxHealth(30) //Change their max health to 30
            .health(30) //Along with their current health to 30- By default they're spawned with max health, this is just here for verbosity.
            .name("&cLord Destroyer"); //Yepp! Making a dark overlord is all in a days work!


    protected ChickenLauncher() {
        /*
        The super in an item-gadget (and sub-classes) requires you to define the item
        that the item that has these actions binded to it!

        Giving players this item will allow them usage of the gadget, though you don't need to
        create a new itemstack every time you want to give players the item, you can
        simple call ChickenLauncher().getItem() and give that to them! Example in the commandsExample class!
         */
        super(ItemBuilder.of(Material.BLAZE_ROD).name("&6Chicken Launcher").lore("&eBring darkness and cuteness to one!").item());
    }

    public int id() {
        /*
        The identifier for gadgets is used when registering and retrieving them; It's required to be unique,
        otherwise it'll overwrite the gadget with the already-registered ID.

        The range goes as high as Integer.MAX_INT, so if you're ever worried about conflicting ID's, just
        register in a range of 100k+
         */
        return 1;
    }

    @Override
    public void perform(Player player) {
        /*
        This is the actions that will be performed when the player interacts / uses
        this gadget in their hand. It could be a swing, left click, right click, etc;
        In this case, we're not worried about what they do, we just wanna launch chickens!

        By using the creature builder above, we can spawn chickens fairly easy!

        We're going to spawn them at the players cursor location, and to make things more interesting
        they'll spawn like bats outta hell! (Moving forward)
         */

        LivingEntity chicken = chickenSpawner.spawn(Players.getTargetLocation(player));

        /*
        By knocking them back we give them a forceful push, without actually damaging them!
         */
        Entities.knockbackEntity(chicken,-4);

    }
}
