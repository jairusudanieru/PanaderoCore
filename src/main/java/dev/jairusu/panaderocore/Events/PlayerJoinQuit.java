package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinQuit implements Listener {

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      event.joinMessage(null);
      Player player = event.getPlayer();

      if (player.isDead()) player.spigot().respawn();
      Bukkit.getScheduler().runTaskLater(ConfigFile.getPlugin, () -> {
         for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(ConfigFile.getPlugin, onlinePlayer);
            onlinePlayer.hidePlayer(ConfigFile.getPlugin, player);
         }
      }, 10L);
   }

}
