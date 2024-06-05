package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Methods.WorldGroups;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class PlayerDeath implements Listener {

   @EventHandler
   public void onPlayerDeath(PlayerDeathEvent event) {
      Player player = event.getPlayer();
      Component deathMessage = event.deathMessage();
      if (deathMessage == null) return;

      List<String> worldGroups = WorldGroups.worldGroups(player.getWorld());
      for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
         if (!worldGroups.contains(onlinePlayer.getWorld().getName())) continue;
         onlinePlayer.sendMessage(deathMessage);
      }
   }

}