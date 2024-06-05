package dev.jairusu.panaderocore.Events;

import dev.jairusu.panaderocore.Configuration.ConfigFile;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Methods.LobbyClass;
import dev.jairusu.panaderocore.Methods.LocationClass;
import dev.jairusu.panaderocore.Methods.WorldGroups;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class LobbyUse implements Listener {

   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent event) {
      Player player = event.getPlayer();
      ItemStack item = event.getItem();

      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      Action action = event.getAction();
      if (!action.isRightClick() || item == null) return;

      if (item.equals(LobbyClass.COMPASS())) {
         Inventory inventory = LobbyClass.getInventory();
         player.openInventory(inventory);
      } else if (item.equals(LobbyClass.LIME_DYE())) {
         LobbyClass.hideOthers(player);
         player.getInventory().setItem(8, LobbyClass.GRAY_DYE());
      } else if (item.equals(LobbyClass.GRAY_DYE())) {
         LobbyClass.showOthers(player);
         player.getInventory().setItem(8, LobbyClass.LIME_DYE());
      } else if (item.equals(LobbyClass.INFO_BOOK())) {
         player.sendMessage(MessageFile.miniMessage("<reset>Welcome To Pandesal!"));
      }
   }

   @EventHandler
   public void onInventoryDrag(InventoryDragEvent event) {
      Player player = (Player) event.getWhoClicked();
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      if (player.getGameMode().equals(GameMode.CREATIVE)) return;
      if (!event.getInventory().equals(LobbyClass.getInventory())) return;
      event.setCancelled(true);
   }

   @EventHandler
   public void onItemDrop(PlayerDropItemEvent event) {
      Player player = event.getPlayer();
      ItemStack itemDrop = event.getItemDrop().getItemStack();
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      if (player.getGameMode().equals(GameMode.CREATIVE)) return;

      List<ItemStack> lobbyItems = Arrays.asList(
              LobbyClass.LIME_DYE(),
              LobbyClass.GRAY_DYE(),
              LobbyClass.COMPASS(),
              LobbyClass.INFO_BOOK()
      );

      if (!lobbyItems.contains(itemDrop)) return;
      event.setCancelled(true);
   }

   @EventHandler
   public void onInventoryClick(InventoryClickEvent event) {
      Player player = (Player) event.getWhoClicked();
      Inventory customInventory = LobbyClass.getInventory();
      Inventory playerInventory = player.getInventory();

      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      if (player.getGameMode().equals(GameMode.CREATIVE)) return;

      Inventory clickedInventory = event.getClickedInventory();
      if (clickedInventory == null) return;
      if (clickedInventory.equals(customInventory)) event.setCancelled(true);

      List<ItemStack> selectorItems = Arrays.asList(
              customInventory.getItem(10),
              customInventory.getItem(13),
              customInventory.getItem(16)
      );

      List<ItemStack> lobbyItems = Arrays.asList(
              playerInventory.getItem(0),
              playerInventory.getItem(4),
              playerInventory.getItem(8)
      );

      if (event.getClick().equals(ClickType.SWAP_OFFHAND)) {
         event.setCancelled(true);
         return;
      }

      if (clickedInventory.equals(customInventory)) {
         if (selectorItems.contains(event.getCurrentItem())) event.setCancelled(true);
         if (event.getClick().isKeyboardClick()) {
            int slot = event.getHotbarButton();
            ItemStack itemStack = player.getInventory().getItem(slot);
            if (itemStack == null) return;
            if (lobbyItems.contains(itemStack)) event.setCancelled(true);
         }

         if (!event.getClick().isLeftClick()) return;
         if (event.getSlot() == 10) {
            event.setCancelled(true);
            LocationClass.teleportTo(player, LocationClass.creativeLocation());
            Bukkit.getScheduler().runTask(ConfigFile.getPlugin, () -> player.closeInventory());
         } else if (event.getSlot() == 13) {
            event.setCancelled(true);
            LocationClass.teleportTo(player, LocationClass.survivalLocation());
            Bukkit.getScheduler().runTask(ConfigFile.getPlugin, () -> player.closeInventory());
         } else if (event.getSlot() == 16) {
            event.setCancelled(true);
            LocationClass.teleportTo(player, LocationClass.pvpArenaLocation());
            Bukkit.getScheduler().runTask(ConfigFile.getPlugin, () -> player.closeInventory());
         }
      }

      if (clickedInventory.equals(playerInventory)) {
         if (lobbyItems.contains(event.getCurrentItem())) event.setCancelled(true);
         if (event.getClick().isKeyboardClick()) {
            int slot = event.getHotbarButton();
            ItemStack itemStack = player.getInventory().getItem(slot);
            if (itemStack == null) return;
            if (lobbyItems.contains(itemStack)) event.setCancelled(true);
         }
      }
   }

   @EventHandler
   public void onItemSwap(PlayerSwapHandItemsEvent event) {
      Player player = event.getPlayer();
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;
      if (player.getGameMode().equals(GameMode.CREATIVE)) return;
      event.setCancelled(true);
   }

   @EventHandler
   public void onLaunchPads(PlayerInteractEvent event) {
      Player player = event.getPlayer();
      if (!player.getWorld().equals(LocationClass.spawnLocation().getWorld())) return;

      if (event.getAction() != Action.PHYSICAL) return;
      Block block = event.getClickedBlock();
      if (block == null) return;
      Material material = block.getType();
      Location blockUnder = player.getLocation();
      blockUnder.setY(blockUnder.getY() - 1);

      double power = ConfigFile.getDouble("config.launchpadPower");
      double height = ConfigFile.getDouble("config.launchpadHeight");

      if (!material.name().contains("PRESSURE_PLATE")) return;
      event.setCancelled(true);

      if (!blockUnder.getBlock().getType().equals(Material.GOLD_BLOCK)) return;
      player.playSound(player.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 1, 1);
      player.setVelocity(player.getLocation().getDirection().multiply(power).setY(height));
   }


}
