package dev.jairusu.panaderocore.Commands;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Configuration.PasswordFile;
import dev.jairusu.panaderocore.Methods.LobbyClass;
import dev.jairusu.panaderocore.Methods.MessageClass;
import dev.jairusu.panaderocore.Methods.LocationClass;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Login implements CommandExecutor, TabCompleter {

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

      if (!PasswordFile.getPlayerFile(player).exists() || PasswordFile.getPlayerFileConfig(player).get("data.password") == null ) {
         sender.sendMessage(MessageFile.miniMessage("You are not registered yet!"));
         return true;
      }

      if (!player.hasMetadata("unlogged")) {
         sender.sendMessage(MessageFile.miniMessage("You are already logged in!"));
         return true;
      }

      if (args.length != 1) {
         sender.sendMessage(MessageFile.miniMessage("Invalid Command Usage!"));
         return true;
      }

      String input = args[0];
      String password = PasswordFile.getPlayerFileConfig(player).getString("data.password");

      if (!input.equals(password)) {
         player.sendMessage(MessageFile.miniMessage("Wrong Password, please try again!"));
         return true;
      }

      if (player.hasPotionEffect(PotionEffectType.BLINDNESS)) player.removePotionEffect(PotionEffectType.BLINDNESS);
      if (player.getGameMode().equals(GameMode.SPECTATOR)) player.setGameMode(GameMode.ADVENTURE);
      player.removeMetadata("unlogged", ConfigFile.getPlugin);
      MessageClass.sendLoggedInMessage(player);
      LocationClass.teleportTo(player, LocationClass.spawnLocation());
      LobbyClass.giveHotbarItems(player);
      return true;
   }

}
