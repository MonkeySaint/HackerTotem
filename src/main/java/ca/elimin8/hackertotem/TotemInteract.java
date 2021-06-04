package ca.elimin8.hackertotem;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static ca.elimin8.hackertotem.HackerTotem.inv;
public class TotemInteract implements Listener {
    Map<UUID, Boolean> fireBallActive = new HashMap<>();
    Map<String, Long> fireBallCD = new HashMap<String, Long>();
    Map<UUID, Boolean> noFallActive = new HashMap<UUID, Boolean>();
    Map<UUID, Boolean> godModeActive = new HashMap<>();
    Map<String, Long> godModeCD = new HashMap<String, Long>();
    Map<UUID, Boolean> flyActive = new HashMap<>();
    Map<String, Long> flyCD = new HashMap<String, Long>();
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!(p.getInventory().getItemInMainHand().getType() == Material.AIR))
            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§4§kHacker Totem")) {
                if (p.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                    if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        p.openInventory(inv);
                      return;
                 }
                    }
                 return;
        }
        //p.sendMessage(fireBallActive.get(p.getUniqueId()).toString());
            if (fireBallActive.isEmpty())
                return;
            if (fireBallActive.containsKey(p.getUniqueId()))
                p.launchProjectile(Fireball.class);
        }
    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("§4§kHacker §4Inventory"))
            return;
        if (e.getCurrentItem()==null)
            return;
        if (e.getCurrentItem().getItemMeta() == null)
            return;
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if (e.getClickedInventory().getType() == InventoryType.PLAYER)
            return;
        if (e.getSlot()==0){
            if (!(flyActive.isEmpty()))
                if (flyActive.containsKey(p.getUniqueId())){
                    p.sendMessage("§4Already Active");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            if (flyCD.containsKey(p.getName())){
                if(flyCD.get(p.getName()) > System.currentTimeMillis()) {
                    long timeleft = (flyCD.get(p.getName()) - System.currentTimeMillis()) /1000 + 1;
                    p.sendMessage("§6You can hack again in "+timeleft+" second(s)");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            }
            flyActive.put(p.getUniqueId(), true);
            p.setAllowFlight(true);
            p.setFlying(true);
            flyRunnable(p);
            p.closeInventory();
            p.updateInventory();
        }
        if (e.getSlot()==1){
            if (!(godModeActive.isEmpty()))
                if (godModeActive.containsKey(p.getUniqueId())){
                    p.sendMessage("§4Already Active");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            if (godModeCD.containsKey(p.getName())){
                if(godModeCD.get(p.getName()) > System.currentTimeMillis()) {
                    long timeleft = (godModeCD.get(p.getName()) - System.currentTimeMillis()) /1000 + 1;
                    p.sendMessage("§6You can hack again in "+timeleft+" second(s)");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            }
            godModeActive.put(p.getUniqueId(), true);
            godModeRunnable(p);
            p.closeInventory();
            p.updateInventory();
        }
        if (e.getSlot()==2){
            if (!(noFallActive.isEmpty()))
                if (noFallActive.containsKey(p.getUniqueId())){
                    noFallActive.remove(p.getUniqueId());
                    p.sendMessage("§4No Fall Deactivated.");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            noFallActive.put(p.getUniqueId(), true);
            p.sendMessage("§2No Fall Active");
            p.closeInventory();
            p.updateInventory();
        }
        if (e.getSlot()==3){
            if (!(fireBallActive.isEmpty()))
                if (fireBallActive.containsKey(p.getUniqueId())){
                    p.sendMessage("§4Already Active");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            if (fireBallCD.containsKey(p.getName())){
                if(fireBallCD.get(p.getName()) > System.currentTimeMillis()) {
                    long timeleft = (fireBallCD.get(p.getName()) - System.currentTimeMillis()) /1000 + 1;
                    p.sendMessage("§6You can hack again in "+timeleft+" second(s)");
                    p.closeInventory();
                    p.updateInventory();
                    return;
                }
            }
            fireBallActive.put(p.getUniqueId(), true);
            fireBallRunnable(p);
            p.closeInventory();
            p.updateInventory();
        }
        p.getInventory().getItemInMainHand().setAmount(0);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (!(e.getEntity() instanceof Player))
            return;
        Player p = (Player) e.getEntity();
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL){
            if (noFallActive.containsKey(p.getUniqueId()) || flyActive.containsKey(p.getUniqueId()))
                e.setCancelled(true);
        }
        if (godModeActive.containsKey(p.getUniqueId()))
            e.setCancelled(true);
    }
    public void fireBallRunnable(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                fireBallActive.remove(p.getUniqueId());
                fireBallCD.put(p.getName(), System.currentTimeMillis() + (60000));
                return;
            }
        }.runTaskLater(HackerTotem.getPlugin(HackerTotem.class), 100);
        return;
    }
    public void godModeRunnable(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                godModeActive.remove(p.getUniqueId());
                godModeCD.put(p.getName(), System.currentTimeMillis() + (60000));
                return;
            }
        }.runTaskLater(HackerTotem.getPlugin(HackerTotem.class), 300);
        return;
    }
    public void flyRunnable(Player p) {
        new BukkitRunnable() {
            @Override
            public void run() {
                flyActive.remove(p.getUniqueId());
                p.setFlying(false);
                p.setAllowFlight(false);
                flyCD.put(p.getName(), System.currentTimeMillis() + (60000));
                return;
            }
        }.runTaskLater(HackerTotem.getPlugin(HackerTotem.class), 200);
        return;
    }
}
