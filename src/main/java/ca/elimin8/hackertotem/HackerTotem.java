package ca.elimin8.hackertotem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class HackerTotem extends JavaPlugin {
    public static Inventory inv;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("gethackertotem").setExecutor(new GetHackerTotem());
        getServer().getPluginManager().registerEvents(new TotemCancel(), this);
        getServer().getPluginManager().registerEvents(new TotemInteract(), this);
        createInv();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private void createInv() {
        Inventory inv = Bukkit.createInventory(null, 9, "§4§kHacker §4Inventory");
        List<String> lore = new ArrayList<>();
        ItemMeta meta;
        ItemStack item = new ItemStack(Material.FEATHER);
        meta = item.getItemMeta();
        meta.setDisplayName("§eFlight");
        lore.clear();
        lore.add("");
        lore.add("§7Give flight for 10 seconds");
        meta.setLore(lore);
        item.setItemMeta(meta);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inv.setItem(0,item);
        item.setType(Material.ENCHANTED_GOLDEN_APPLE);
        meta = item.getItemMeta();
        meta.setDisplayName("§eGod Mode");
        lore.clear();
        lore.add("");
        lore.add("§7Give God for 15 seconds");
        meta.setLore(lore);
        item.setItemMeta(meta);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inv.setItem(1,item);
        item.setType(Material.FEATHER);
        meta = item.getItemMeta();
        lore.clear();
        lore.add("");
        lore.add("§7Toggles No Fall");
        meta.setLore(lore);
        meta.setDisplayName("§eNo Fall");
        item.setItemMeta(meta);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inv.setItem(2,item);
        item.setType(Material.FIRE_CHARGE);
        meta = item.getItemMeta();
        meta.setDisplayName("§eFireBall");
        lore.clear();
        lore.add("");
        lore.add("§7Left Click throws Fire Ball for 5 seconds.");
        meta.setLore(lore);
        item.setItemMeta(meta);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        inv.setItem(3,item);
        this.inv=inv;
    }
}
