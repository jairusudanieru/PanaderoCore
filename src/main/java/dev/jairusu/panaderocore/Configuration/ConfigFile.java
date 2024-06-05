package dev.jairusu.panaderocore.Configuration;

import dev.jairusu.panaderocore.Panadero_Core;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;

public class ConfigFile {

   public static JavaPlugin getPlugin = JavaPlugin.getProvidingPlugin(Panadero_Core.class);

   public static Logger getLogger = getPlugin.getLogger();

   public static boolean getBoolean(String key) {
      return getPlugin.getConfig().getBoolean(key);
   }

   public static int getInt(String key) {
      return getPlugin.getConfig().getInt(key);
   }

   public static long getLong(String key) {
      return getPlugin.getConfig().getLong(key);
   }

   public static double getDouble(String key) {
      return getPlugin.getConfig().getDouble(key);
   }

   public static String getString(String key) {
      return getPlugin.getConfig().getString(key);
   }

   public static ConfigurationSection getConfigSection(String key) {
      return getPlugin.getConfig().getConfigurationSection(key);
   }

   public static List<String> getStringList(String key) {
      return getPlugin.getConfig().getStringList(key);
   }

   public static Location getLocation(String key) {
      return getPlugin.getConfig().getLocation(key);
   }

   public static World getWorld(String key) {
      String string = getPlugin.getConfig().getString(key);
      if (string == null) return null;
      return Bukkit.getWorld(string);
   }

   public static void setLocation(String key, Location location) {
      getPlugin.getConfig().set(key, location);
      getPlugin.saveConfig();
   }

}
