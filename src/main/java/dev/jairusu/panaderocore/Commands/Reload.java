package dev.jairusu.panaderocore.Commands;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Methods.MessageClass;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Reload implements CommandExecutor, TabCompleter {

   @Override
   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
      return new ArrayList<>();
   }

   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
      if (args.length != 0) {
         sender.sendMessage(MessageFile.miniMessage("Invalid command Usage!"));
         return true;
      }

      ConfigFile.getPlugin.reloadConfig();
      MessageFile.reloadFile();
      Component message = MessageClass.reloadMessage();
      if (message == null) return true;
      sender.sendMessage(message);
      return true;
   }

}
