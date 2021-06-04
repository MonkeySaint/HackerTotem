package ca.elimin8.hackertotem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import static ca.elimin8.hackertotem.HackerTotem.inv;

public class TotemCancel implements Listener {
    @EventHandler
    public void onTotemUse(EntityResurrectEvent e){
        if (!(e.getEntity() instanceof Player))
            return;
        Player p = (Player) e.getEntity();
        if (!(p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§4§kHacker Totem")))
            return;
        e.setCancelled(true);
    }
}
