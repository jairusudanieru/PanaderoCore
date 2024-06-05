package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WorldGroups {

   public static List<String> worldGroups(World world) {
      ConfigurationSection section = ConfigFile.getConfigSection("worldGroups");
      if (section == null) return new ArrayList<>();
      for (String groupName : section.getKeys(false)) {
         List<String> worldNames = section.getStringList(groupName);
         if (!worldNames.contains(world.getName())) continue;
         return worldNames;
      }
      return new ArrayList<>();
   }

   public static boolean inArenaHub(Player player) {
      Location corner1 = ConfigFile.getLocation("arenaHub.pos1");
      Location corner2 = ConfigFile.getLocation("arenaHub.pos2");
      Location playerLocation = player.getLocation();

      double x1 = Math.min(corner1.getX(), corner2.getX());
      double x2 = Math.max(corner1.getX(), corner2.getX());
      double y1 = Math.min(corner1.getY(), corner2.getY());
      double y2 = Math.max(corner1.getY(), corner2.getY());
      double z1 = Math.min(corner1.getZ(), corner2.getZ());
      double z2 = Math.max(corner1.getZ(), corner2.getZ());

      return (playerLocation.getX() >= x1 && playerLocation.getX() <= x2
              && playerLocation.getY() >= y1 && playerLocation.getY() <= y2
              && playerLocation.getZ() >= z1 && playerLocation.getZ() <= z2);
   }


}
