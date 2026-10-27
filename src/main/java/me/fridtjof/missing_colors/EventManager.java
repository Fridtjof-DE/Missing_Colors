package me.fridtjof.missing_colors;

import me.fridtjof.puddingapi.bukkit.utils.ColorTranslationUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventManager implements Listener {

    ColorTranslationUtils colorUtils = new ColorTranslationUtils('&', "&#", "");

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (colorUtils.isMessageEmptyOrWhitespaceOnly(message)) {
            event.setCancelled(true);
            return;
        }

        if(player.hasPermission("missing_colors.chat.hex_codes") || player.isOp()) {
            message = colorUtils.translateHexCodes(message);
        }

        if(player.hasPermission("missing_colors.chat.color_codes") || player.isOp()) {
            message = colorUtils.translateLegacyColorCodes(message);
        }

        if(player.hasPermission("missing_colors.chat.formatting_codes") || player.isOp()) {
            message = colorUtils.translateLegacyFormattingCodes(message);
        }

        event.setMessage(message);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWrite(SignChangeEvent event) {

        if (event.getPlayer().hasPermission("missing_colors.signs.hex_codes") || event.getPlayer().isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateHexCodes(event.getLine(i)));
            }
        }

        if (event.getPlayer().hasPermission("missing_colors.signs.color_codes") || event.getPlayer().isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateLegacyColorCodes(event.getLine(i)));
            }
        }

        if (event.getPlayer().hasPermission("missing_colors.signs.formatting_codes") || event.getPlayer().isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateLegacyFormattingCodes(event.getLine(i)));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInvClick(InventoryClickEvent event) {

        if(!event.isCancelled()) {
            HumanEntity entity = event.getWhoClicked();
            if(entity instanceof Player){
                Player player = (Player)entity;
                Inventory inv = event.getInventory();

                if(inv instanceof AnvilInventory) {
                    InventoryView view = event.getView();
                    int rawSlot = event.getRawSlot();

                    if(rawSlot == view.convertSlot(rawSlot)) {
                        if(rawSlot == 2) {
                            ItemStack itemStack = event.getCurrentItem();

                            if(itemStack != null) {

                                ItemMeta meta = itemStack.getItemMeta();
                                String displayName = meta.getDisplayName();

                                if (colorUtils.isMessageEmptyOrWhitespaceOnly(displayName)) {
                                    event.setCancelled(true);
                                    return;
                                }

                                if(player.hasPermission("missing_colors.anvil.hex_codes")) {
                                    displayName = colorUtils.translateHexCodes(displayName);
                                }

                                if(player.hasPermission("missing_colors.anvil.color_codes")) {
                                    displayName = colorUtils.translateLegacyColorCodes(displayName);
                                }

                                if(player.hasPermission("missing_colors.anvil.formatting_codes")) {
                                    displayName = colorUtils.translateLegacyFormattingCodes(displayName);
                                }

                                meta.setDisplayName(displayName);
                                itemStack.setItemMeta(meta);
                            }
                        }
                    }
                }
            }
        }
    }
}
