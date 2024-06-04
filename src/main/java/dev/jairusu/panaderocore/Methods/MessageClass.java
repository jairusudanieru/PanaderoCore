package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Configuration.MessageFile;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class MessageClass {

   public static Component loginMessage(Player player) {
      String message = MessageFile.getFileConfig().getString("message.loginMessage");
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
