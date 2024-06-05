package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Methods.LobbyClass;
import dev.jairusu.panaderocore.Methods.LocationClass;
import dev.jairusu.panaderocore.Methods.MessageClass;
import dev.jairusu.panaderocore.Methods.WorldGroups;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.util.List;

public class ChangeWorld implements Listener {

   @EventHandler
   public void onChangeWorld(PlayerChangedWorldEvent event) {
      Player player = event.getPlayer();
      World playerWorld = player.getWorld();
      World previousWorld = event.getFrom();

      String playerWorldName = playerWorld.getName();
      String previousWorldName = previousWorld.getName();
      List<String> worldGroup = WorldGroups.worldGroups(playerWorld);
      List<String> previousGroup = WorldGroups.worldGroups(previousWorld);

      if (worldGroup.contains(playerWorldName) && !worldGroup.contains(previousWorldName)) {
         if (playerWorld.equals(LocationClass.spawnLocation().getWorld())) {
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            LobbyClass.giveHotbarItems(player);
         }

         for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!worldGroup.contains(onlinePlayer.getWorld().getName())) continue;
            player.showPlayer(ConfigFile.getPlugin, onlinePlayer);
            onlinePlayer.showPlayer(ConfigFile.getPlugin, player);
            MessageClass.sendJoinMessage(player, onlinePlayer);
         }
      }

      if (previousGroup.contains(previousWorldName) && !previousGroup.contains(playerWorldName)) {
         for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (!previousGroup.contains(onlinePlayer.getWorld().getName())) continue;
            player.hidePlayer(ConfigFile.getPlugin, onlinePlayer);
            onlinePlayer.hidePlayer(ConfigFile.getPlugin, player);
            MessageClass.sendQuitMessage(player, onlinePlayer);
         }
      }
   }

}