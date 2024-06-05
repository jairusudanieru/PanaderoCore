package dev.jairusu.panaderocore.Methods;

import dev.jairusu.panaderocore.Commands.*;
import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Events.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.Objects;

public class StartupClass {

   public static void registerCommands() {
      registerCommand("afk", new AFK(), new AFK());
      registerCommand("arenastart", new ArenaStart(), new ArenaStart());
      registerCommand("changepassword", new ChangePassword(), new ChangePassword());
      registerCommand("login", new Login(), new Login());
      registerCommand("register", new Register(), new Register());
      registerCommand("authreload", new Reload(), new Reload());
      registerCommand("reply", new Reply(), new Reply());
      registerCommand("setlocation", new SetLocation(), new SetLocation());
      registerCommand("spawn", new Spawn(), new Spawn());
      registerCommand("whisper", new Whisper(), new Whisper());
   }

   public static void registerEvents() {
      Bukkit.getPluginManager().registerEvents(new AdvancementGain(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new ChangeWorld(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new CommandSend(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new DetectAFK(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new FarmProtect(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new LobbyUse(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerAuth(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerChat(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerDamage(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerDeath(), ConfigFile.getPlugin);
      Bukkit.getPluginManager().registerEvents(new PlayerJoinQuit(), ConfigFile.getPlugin);
   }

   private static void registerCommand(String command, CommandExecutor executor, TabCompleter completer) {
      PluginCommand pluginCommand = Objects.requireNonNull(Bukkit.getPluginCommand(command));
      pluginCommand.setExecutor(executor);
      pluginCommand.setTabCompleter(completer);
   }

}
