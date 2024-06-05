package dev.jairusu.panaderocore.Commands;

import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Methods.AFKClass;
import dev.jairusu.panaderocore.Methods.LocationClass;
import dev.jairusu.panaderocore.Methods.WorldGroups;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AFK implements TabCompleter, CommandExecutor {

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
      List<String> worldGroups = WorldGroups.worldGroups(LocationClass.survivalLocation().getWorld());
      if (!worldGroups.contains(player.getWorld().getName())) {
         sender.sendMessage("Unknown command. Type \"/help\" for help.");
         return true;
      }

      if (AFKClass.afkTeam().hasEntry(player.getName())) {
         AFKClass.removeToAFKTeam(player, AFKClass.afkTeam());
         return true;
      }

      AFKClass.addToAFKTeam(player, AFKClass.afkTeam());
      return true;
   }

}