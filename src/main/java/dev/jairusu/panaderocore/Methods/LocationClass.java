package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class LocationClass {

   public static void loginAuthLocation(Player player) {
      Location spawnLocation = ConfigFile.getLocation("location.authLocation");
      if (ConfigFile.getBoolean("config.teleportOnAuth") && spawnLocation != null) {
         player.teleport(spawnLocation);
      }
   }

   public static void teleportTo(Player player, Location location) {
      if (location == null) return;
      player.teleport(location);
   }


   public static Location authLocation() {
      return ConfigFile.getLocation("location.authLocation");
   }

   public static Location spawnLocation() {
      return ConfigFile.getLocation("location.spawnLocation");
   }

   public static Location creativeLocation() {
      return ConfigFile.getLocation("location.creativeLocation");
   }

   public static Location survivalLocation() {
      return ConfigFile.getLocation("location.survivalLocation");
   }

   public static Location pvpArenaLocation() {
      return ConfigFile.getLocation("location.pvpArenaLocation");
   }

   public static Location tagArenaLocation() {
      return ConfigFile.getLocation("location.tagArenaLocation");
   }


}
