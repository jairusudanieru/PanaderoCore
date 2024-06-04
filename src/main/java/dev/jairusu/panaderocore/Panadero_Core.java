package dev.jairusu.panaderocore;

import com.github.games647.fastlogin.bukkit.FastLoginBukkit;
import dev.jairusu.panaderocore.Configuration.MessageFile;
import dev.jairusu.panaderocore.Methods.AuthPluginHook;
import dev.jairusu.panaderocore.Methods.StartupClass;
import org.bukkit.plugin.java.JavaPlugin;

public final class Panadero_Core extends JavaPlugin {

    public static FastLoginBukkit fastLoginBukkit = JavaPlugin.getPlugin(FastLoginBukkit.class);

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        StartupClass.registerCommands();
        StartupClass.registerEvents();
        MessageFile.checkConfigFile();
        fastLoginBukkit.getCore().setAuthPluginHook(new AuthPluginHook());
        this.getLogger().info("Panadero-Core Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Panadero-Core Plugin has been disabled!");
    }

}
