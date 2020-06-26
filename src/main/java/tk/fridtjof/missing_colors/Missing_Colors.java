package tk.fridtjof.missing_colors;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import tk.fridtjof.missing_colors.utils.UpdateChecker;

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
        UpdateChecker updateChecker = new UpdateChecker(this);
        updateChecker.checkForUpdate();

        getServer().getPluginManager().registerEvents(new EventManager(), this);

        int pluginId = 7545;
        new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {

    }
}