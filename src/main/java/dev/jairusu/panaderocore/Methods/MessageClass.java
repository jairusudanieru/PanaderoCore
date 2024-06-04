package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Configuration.MessageFile;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class MessageClass {

   public static void sendLoginMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.loginMessage");
      if (message == null) return;
      message = message.replace("%player%",player.getName());
      player.sendMessage(MessageFile.miniMessage(message));
   }

   public static void sendRegisterMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.registerMessage");
      if (message == null) return;
      message = message.replace("%player%",player.getName());
      player.sendMessage(MessageFile.miniMessage(message));
   }

   public static void sendLoggedInMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.loggedInMessage");
      if (message == null) return;
      message = message.replace("%player%",player.getName());
      player.sendMessage(MessageFile.miniMessage(message));
   }

   public static Component registeredMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.registeredMessage");
      if (message == null) return null;
      message = message.replace("%player%",player.getName());
      return MessageFile.miniMessage(message);
   }

   public static Component reloadMessage() {
      String message = MessageFile.getFileConfig().getString("message.reloadMessage");
      if (message == null) return null;
      return MessageFile.miniMessage(message);
   }

}
