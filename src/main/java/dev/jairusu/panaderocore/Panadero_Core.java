package dev.jairusu.panaderocore;

import com.github.games647.fastlogin.bukkit.FastLoginBukkit;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Methods.AuthPluginHook;
import dev.jairusu.panaderocore.Methods.LobbyClass;
import dev.jairusu.panaderocore.Methods.StartupClass;
import org.bukkit.plugin.java.JavaPlugin;

public final class Panadero_Core extends JavaPlugin {

    public static final FastLoginBukkit fastLoginBukkit = JavaPlugin.getPlugin(FastLoginBukkit.class);

    @Override
    public void onEnable() {
        fastLoginBukkit.getCore().setAuthPluginHook(new AuthPluginHook());
        this.saveDefaultConfig();
        LobbyClass.makeInventory();
        StartupClass.registerCommands();
        StartupClass.registerEvents();
        MessageFile.checkConfigFile();
        this.getLogger().info("Panadero-Core Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Panadero-Core Plugin has been disabled!");
    }

}
