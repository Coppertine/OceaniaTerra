package com.bteoce.java.oceaniaterra;

import com.bteoce.java.oceaniaterra.commands.Tpll;
import com.bteoce.java.oceaniaterra.config.ConfigNotImplementedException;
import com.bteoce.java.oceaniaterra.world.gen.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

public final class OceaniaTerra extends JavaPlugin implements Listener {
    private static OceaniaTerra plugin;


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);
        plugin = this;
        getCommand("tpll").setExecutor(new Tpll());

    }


    public static Plugin getPlugin() {
        return plugin;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return new EarthGenerator();
    }

    @Nullable
    @Override
    public BiomeProvider getDefaultBiomeProvider(@NotNull String worldName, @Nullable String id) {
        return new EarthBiomeProvider();
    }
}
