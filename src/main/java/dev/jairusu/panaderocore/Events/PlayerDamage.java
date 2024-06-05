package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Methods.LocationClass;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {

   @EventHandler
   public void onPlayerDamage(EntityDamageEvent event) {
      if (!event.getEntity().getType().equals(EntityType.PLAYER)) return;
      Player player = (Player) event.getEntity();
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      event.setCancelled(true);
   }

   @EventHandler
   public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
      EntityType entityDamager = event.getDamager().getType();
      EntityType entityType = event.getEntityType();
      if (!entityType.equals(EntityType.PLAYER)) return;
      if (!entityDamager.equals(EntityType.PLAYER)) return;

      Player player = (Player) event.getDamager();
      if (!player.getWorld().equals(LocationClass.pvpArenaLocation().getWorld())) return;
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      event.setCancelled(true);
   }

}
