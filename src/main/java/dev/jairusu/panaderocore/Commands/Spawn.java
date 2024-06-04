package dev.jairusu.panaderocore.Commands;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Spawn implements CommandExecutor, TabCompleter {

   @Override
   public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
      return new ArrayList<>();
   }

   @Override
   public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
      if (!(sender instanceof Player)) {
         sender.sendMessage(MessageFile.miniMessage("Only players can use this command!"));
         return true;
      }

      Player player = (Player) sender;

      if (args.length != 0) {
         sender.sendMessage(MessageFile.miniMessage("Invalid command Usage!"));
         return true;
      }

      Location location = ConfigFile.getLocation("location.spawnLocation");
      if (location == null) {
         sender.sendMessage(MessageFile.miniMessage("Spawn Location not Found!"));
         return true;
      }

      player.teleport(location);
      return true;
   }

}
