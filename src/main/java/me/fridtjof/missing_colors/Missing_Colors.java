package me.fridtjof.missing_colors;

import me.fridtjof.puddingapi.bukkit.utils.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import me.fridtjof.puddingapi.bukkit.utils.UpdateChecker;

public final class Missing_Colors extends JavaPlugin {

    private static Missing_Colors instance;

    public Missing_Colors() {
        instance = this;
    }

    public static Missing_Colors getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventManager(), this);

        new UpdateChecker(this, 55489, "missing_colors.update");
        new Metrics(this, 7545);
    }

    @Override
    public void onDisable() {

    }
}