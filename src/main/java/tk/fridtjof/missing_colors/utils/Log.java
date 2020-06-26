package tk.fridtjof.missing_colors.utils;

import org.bukkit.Bukkit;
import tk.fridtjof.missing_colors.Missing_Colors;
import tk.fridtjof.missing_colors.Missing_Colors;

import java.util.logging.Logger;

public class Log {

    static Missing_Colors plugin = Missing_Colors.getInstance();

    public static Logger log = Bukkit.getLogger();

    public static void info(String msg) {
        log.info("[" + plugin.getName() + "] " + msg);
    }

    public static void warning(String msg) {
        log.warning("[Yggdrasil] " + msg);
    }
}