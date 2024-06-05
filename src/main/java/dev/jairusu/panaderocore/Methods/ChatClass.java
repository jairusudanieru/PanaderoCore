package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Configuration.MessageFile;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ChatClass {

   public static HashMap<UUID, UUID> lastReceived = new HashMap<>();

   public static void senderSendMessage(Player sender, Player target, String message) {
      String senderMessage = MessageFile.getString("message.senderMessage");
      if (senderMessage != null) {
         senderMessage = senderMessage.replace("%sender%",sender.getName())
                 .replace("%target%",target.getName())
                 .replace("%message%",message);
         sender.sendMessage(MessageFile.miniMessage(senderMessage));
      }
   }

   public static void targetSendMessage(Player sender, Player target, String message) {
      String targetMessage = MessageFile.getString("message.targetMessage");
      if (targetMessage != null) {
         targetMessage = targetMessage.replace("%sender%",sender.getName())
                 .replace("%target%",target.getName())
                 .replace("%message%",message);
         target.sendMessage(MessageFile.miniMessage(targetMessage));
      }
   }

}
