package me.fridtjof.missing_colors.utils;

public class FormattingCodes {

    public String Format(String output) {
        String f21 = output.replace("&k", "§k");
        String f22 = f21.replace("&l", "§l");
        String f23 = f22.replace("&m", "§m");
        String f24 = f23.replace("&n", "§n");
        String f25 = f24.replace("&o", "§o");
        String back = f24.replace("&r", "§r");

        return back;
    }
}