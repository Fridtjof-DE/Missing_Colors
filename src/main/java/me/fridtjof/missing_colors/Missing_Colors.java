package me.fridtjof.missing_colors;

import me.fridtjof.puddingapi.bukkit.utils.Logger;
import me.fridtjof.puddingapi.bukkit.utils.Metrics;
import me.fridtjof.puddingapi.bukkit.utils.ModrinthUpdateChecker;
import me.fridtjof.puddingapi.bukkit.utils.PuddingAPIVersionChecker;
import org.bukkit.plugin.java.JavaPlugin;

public final class Missing_Colors extends JavaPlugin {

    private static Missing_Colors instance;

    public Missing_Colors() {
        instance = this;
    }

    public static Missing_Colors getInstance() {
        return instance;
    }

    Logger logger = new Logger(this);

    @Override
    public void onEnable() {

        new PuddingAPIVersionChecker(this, logger, 2503151209L);
        new ModrinthUpdateChecker(this, "hiFvJn4K", "spigot");
        //legacy update checker
        //new UpdateChecker(this, 55489, "missing_colors.update");

        new Metrics(this, 7545);

        getServer().getPluginManager().registerEvents(new EventManager(), this);
    }

    @Override
    public void onDisable() {

    }
}