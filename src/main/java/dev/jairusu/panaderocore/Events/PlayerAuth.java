package dev.jairusu.panaderocore.Events;

import com.github.games647.fastlogin.core.PremiumStatus;
import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Configuration.PasswordFile;
import dev.jairusu.panaderocore.Methods.MessageClass;
import dev.jairusu.panaderocore.Panadero_Core;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class PlayerAuth implements Listener {

   @EventHandler
   public void onPlayerJoin(PlayerJoinEvent event) {
      event.joinMessage(null);
      Player player = event.getPlayer();
      final BukkitTask[] task = new BukkitTask[1];
      Bukkit.getScheduler().runTaskLater(ConfigFile.getPlugin, ()-> task[0] = Bukkit.getScheduler().runTaskTimer(ConfigFile.getPlugin, () -> {
         if (!Panadero_Core.fastLoginBukkit.getStatus(player.getUniqueId()).equals(PremiumStatus.UNKNOWN)) {
            this.authenticatePlayer(player);
            task[0].cancel();
         }
      }, 1L, 1L), 1L);
   }

   @EventHandler
   public void onPlayerMove(PlayerMoveEvent event) {
      Player player = event.getPlayer();
      if (!player.hasMetadata("unlogged")) return;
      event.setCancelled(true);
   }

   private void authenticatePlayer(Player player) {
      this.teleportOnSpawn(player);
      if (Panadero_Core.fastLoginBukkit.getStatus(player.getUniqueId()).equals(PremiumStatus.PREMIUM)) {
         if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.username") == null) {
            this.autoRegisterPlayer(player);
         }
      } else {
         this.sendAuthentication(player);
         if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.password") == null) {
            MessageClass.sendRegisterMessage(player);
            return;
         }

         MessageClass.sendLoginMessage(player);
      }
   }

   private void teleportOnSpawn(Player player) {
      Location spawnLocation = ConfigFile.getLocation("location.spawnLocation");
      if (ConfigFile.getBoolean("config.teleportOnSpawn") && spawnLocation != null) {
         player.teleport(spawnLocation);
      }
   }

   private void sendAuthentication(Player player) {
      player.setMetadata("unlogged", new FixedMetadataValue(ConfigFile.getPlugin, "unlogged"));
      player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
      player.setGameMode(GameMode.SPECTATOR);
   }

   private void autoRegisterPlayer(Player player) {
      PasswordFile.savePasswordFile(player, "data.username", player.getName());
      PasswordFile.savePasswordFile(player, "data.uuid", player.getUniqueId().toString());
      PasswordFile.savePasswordFile(player, "data.password", null);
      PasswordFile.savePasswordFile(player, "data.status", "premium");
   }

}
