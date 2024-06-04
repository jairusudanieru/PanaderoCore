package dev.jairusu.panaderocore.Commands;

import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Configuration.PasswordFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ChangePassword implements CommandExecutor, TabCompleter {

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

      if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.password") == null) {
         sender.sendMessage(MessageFile.miniMessage("You need to register first to use this command!"));
         return true;
      }

      if (player.hasMetadata("unlogged")) {
         sender.sendMessage(MessageFile.miniMessage("You need to logged in to use this command!"));
         return true;
      }

      if (args.length != 2) {
         sender.sendMessage(MessageFile.miniMessage("Invalid command Usage!"));
         return true;
      }

      String password = PasswordFile.getPlayerFileConfig(player).getString("data.password");
      if (!args[0].equals(password)) {
         sender.sendMessage(MessageFile.miniMessage("The old password didn't match! Please try again."));
         return true;
      }

      String input = args[1];
      if (input.length() < 8) {
         sender.sendMessage(MessageFile.miniMessage("The minimum password length is 8 characters!"));
         return true;
      }

      PasswordFile.savePasswordFile(player, "data.password", input);
      sender.sendMessage(MessageFile.miniMessage("<yellow>Password changed successfully!"));
      return true;
   }

}
