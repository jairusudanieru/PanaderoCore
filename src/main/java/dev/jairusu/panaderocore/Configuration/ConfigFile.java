package dev.jairusu.panaderocore.Configuration;

import dev.jairusu.panaderocore.Panadero_Core;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ConfigFile {

   public static JavaPlugin getPlugin = JavaPlugin.getProvidingPlugin(Panadero_Core.class);

   public static Logger getLogger = getPlugin.getLogger();

   public static Boolean getBoolean(String path) {
      return getPlugin.getConfig().getBoolean(path);
   }

   public static Location getLocation (String path) {
      return getPlugin.getConfig().getLocation(path);
   }

   public static void setLocation(String path, Location location) {
      getPlugin.getConfig().set(path, location);
      getPlugin.saveConfig();
   }

}
