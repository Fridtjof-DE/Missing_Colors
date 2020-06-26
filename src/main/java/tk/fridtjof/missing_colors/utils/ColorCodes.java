package tk.fridtjof.missing_colors.utils;

public class ColorCodes {

    public String Format(String output) {
        String f1 = output.replace("&0", "§0");
        String f2 = f1.replace("&1", "§1");
        String f3 = f2.replace("&2", "§2");
        String f4 = f3.replace("&3", "§3");
        String f5 = f4.replace("&4", "§4");
        String f6 = f5.replace("&5", "§5");
        String f7 = f6.replace("&6", "§6");
        String f8 = f7.replace("&7", "§7");
        String f9 = f8.replace("&8", "§8");
        String f10 = f9.replace("&9", "§9");
        String f11 = f10.replace("&a", "§a");
        String f12 = f11.replace("&b", "§b");
        String f13 = f12.replace("&c", "§c");
        String f14 = f13.replace("&d", "§d");
        String f15 = f14.replace("&e", "§e");
        String back = f15.replace("&f", "§f");

        return back;
    }
}