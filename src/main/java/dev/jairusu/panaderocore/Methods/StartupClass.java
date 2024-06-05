package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Commands.*;
import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Events.LobbyUse;
import dev.jairusu.panaderocore.Events.PlayerAuth;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.Objects;

public class StartupClass {

   public static void registerCommands() {
      registerCommand("changepassword", new ChangePassword(), new ChangePassword());
      registerCommand("login", new Login(), new Login());
      registerCommand("register", new Register(), new Register());
      registerCommand("authreload", new Reload(), new Reload());
      registerCommand("setlocation", new SetLocation(), new SetLocation());
      registerCommand("spawn", new Spawn(), new Spawn());
   }

   public static void registerEvents() {
      Bukkit.getPluginManager().registerEvents(new LobbyUse(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerAuth(), ConfigFile.getPlugin);
   }

   private static void registerCommand(String command, CommandExecutor executor, TabCompleter completer) {
      PluginCommand pluginCommand = Objects.requireNonNull(Bukkit.getPluginCommand(command));
      pluginCommand.setExecutor(executor);
      pluginCommand.setTabCompleter(completer);
   }

}
