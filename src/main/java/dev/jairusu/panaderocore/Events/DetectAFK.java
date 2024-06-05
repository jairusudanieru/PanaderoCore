package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Methods.AFKClass;
import dev.jairusu.panaderocore.Methods.LocationClass;
import dev.jairusu.panaderocore.Methods.WorldGroups;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.List;

public class DetectAFK implements Listener {

   @EventHandler
   public void onPlayerMoveAFK(PlayerMoveEvent event) {
      if (event.hasChangedOrientation()) return;
      Player player = event.getPlayer();
      List<String> worldGroups = WorldGroups.worldGroups(LocationClass.survivalLocation().getWorld());
      if (!worldGroups.contains(player.getWorld().getName())) return;

      AFKClass.lastMovementHashMap.put(player, System.currentTimeMillis());
      if (!AFKClass.afkStatusHashMap.containsKey(player)) return;
      AFKClass.afkStatusHashMap.remove(player);
      AFKClass.removeToAFKTeam(player, AFKClass.afkTeam());
   }

}
