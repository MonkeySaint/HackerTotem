package ca.elimin8.hackertotem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GetHackerTotem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player to do this.");
            return true;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("hackertotem.get")))
            return true;
        if (p.getInventory().firstEmpty() == -1) {
            p.sendMessage("§4Your Inventory is full.");
            return true;
        }
        p.getInventory().addItem(hackerTotem());
        p.sendMessage("§2Hacker Totem Given.");
        return false;
    }
    public ItemStack hackerTotem() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§4§kHacker Totem");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add("§4Right Click to Open §kHacker §4GUI");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1000, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }
}