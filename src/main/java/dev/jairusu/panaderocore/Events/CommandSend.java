package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Methods.LocationClass;
import dev.jairusu.panaderocore.Methods.WorldGroups;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandSend implements Listener {

   @EventHandler
   public void onArenaCommand(PlayerCommandSendEvent event) {
      Player player = event.getPlayer();
      World playerWorld = player.getWorld();

      Collection<String> commands = event.getCommands();
      if (playerWorld.equals(LocationClass.pvpArenaLocation().getWorld()) && !WorldGroups.inArenaHub(player)) return;
      commands.remove("arenastart");
   }

   @EventHandler
   public void onSuicideCommand(PlayerCommandSendEvent event) {
      Player player = event.getPlayer();
      World playerWorld = player.getWorld();

      List<World> worlds = new ArrayList<>();
      worlds.add(LocationClass.spawnLocation().getWorld());
      worlds.add(LocationClass.creativeLocation().getWorld());
      worlds.add(LocationClass.authLocation().getWorld());

      Collection<String> commands = event.getCommands();
      if (worlds.contains(playerWorld)) commands.remove("suicide");
   }

   @EventHandler
   public void onLoginCommand(PlayerCommandSendEvent event) {
      Player player = event.getPlayer();
      World playerWorld = player.getWorld();

      Collection<String> commands = event.getCommands();
      if (!playerWorld.equals(LocationClass.authLocation().getWorld())) {
         commands.removeAll(Arrays.asList("register", "login", "log", "reg"));
         return;
      }

      commands.clear();
      commands.addAll(Arrays.asList("register", "login", "log", "reg"));
   }

   @EventHandler
   public void onAFKCommand(PlayerCommandSendEvent event) {
      Player player = event.getPlayer();
      World playerWorld = player.getWorld();

      Collection<String> commands = event.getCommands();
      List<String> worldGroups = WorldGroups.worldGroups(LocationClass.survivalLocation().getWorld());
      if (worldGroups.contains(playerWorld.getName())) return;
      commands.remove("afk");
   }

}