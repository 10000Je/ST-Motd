package com.stuudent.motd;

import com.stuudent.motd.listeners.ServerRefreshed;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MotdCore extends JavaPlugin {

    public static MotdCore instance;
    public static FileConfiguration cf;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        cf = getConfig();
        ServerRefreshed.initListener();
        Bukkit.getPluginManager().registerEvents(new ServerRefreshed(), instance);
        Bukkit.getConsoleSender().sendMessage("§6ST§f-§cM§eO§bT§dD §ev" + getDescription().getVersion() + " §a플러그인이 활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§6ST§f-§cM§eO§bT§dD §ev" + getDescription().getVersion() + " §c플러그인이 비활성화 되었습니다. §f(created by STuuDENT, Discord 민제#5894)");
    }

}
