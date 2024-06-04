package dev.jairusu.panaderocore.Configuration;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageFile {

   private static FileConfiguration fileConfiguration;
   private static File file;

   public static Component miniMessage(String text) {
      MiniMessage miniMessage = MiniMessage.miniMessage();
      return miniMessage.deserialize(text);
   }

   public static void checkConfigFile() {
      file = new File(ConfigFile.getPlugin.getDataFolder(), "message.yml");
      if (!file.exists()) {
         boolean wasCreated = file.getParentFile().mkdirs();
         if (!wasCreated) ConfigFile.getLogger.info("Password was not created!");
         ConfigFile.getPlugin.saveResource("message.yml", false);
      }
      fileConfiguration = YamlConfiguration.loadConfiguration(file);
   }

   public static FileConfiguration getFileConfig() {
      return fileConfiguration;
   }

   public static void reloadFile() {
      fileConfiguration = YamlConfiguration.loadConfiguration(file);
   }


}
