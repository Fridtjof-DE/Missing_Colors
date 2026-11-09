package me.fridtjof.missing_colors;

import me.fridtjof.puddingapi.bukkit.utils.ColorTranslationUtils;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.view.AnvilView;

public class EventManager implements Listener {

    ColorTranslationUtils colorUtils = new ColorTranslationUtils('&', "&#", "");

    // chat
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

    // signs
    @EventHandler(priority = EventPriority.LOWEST)
    public void onWrite(SignChangeEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("missing_colors.signs.hex_codes") || player.isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateHexCodes(event.getLine(i)));
            }
        }

        if (player.hasPermission("missing_colors.signs.color_codes") || player.isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateLegacyColorCodes(event.getLine(i)));
            }
        }

        if (player.hasPermission("missing_colors.signs.formatting_codes") || player.isOp()) {
            for (int i = 0; i < 4; i++) {
                event.setLine(i, colorUtils.translateLegacyFormattingCodes(event.getLine(i)));
            }
        }
    }


    // anvil
    @EventHandler(priority = EventPriority.LOWEST)
    public void onPrepareAnvil(PrepareAnvilEvent event)
    {
        AnvilInventory anvilInventory = event.getInventory();
        AnvilView anvilView = event.getView();

        // check if there is an input item
        ItemStack inputItem = anvilInventory.getItem(0);
        if(inputItem == null || inputItem.getType().isAir())
        {
            return;
        }

        // get input text
        String inputText = anvilView.getRenameText();

        // check if there is input text
        if(inputText == null || inputText.isEmpty())
        {
            return;
        }

        // check if message is empty or whitespace only
        if(colorUtils.isMessageEmptyOrWhitespaceOnly(inputText))
        {
            event.setResult(null);
            return;
        }

        // check if output item is empty
        ItemStack outputItemStack = event.getResult();
        if(outputItemStack == null || outputItemStack.getType().isAir())
        {
            return;
        }

        // check if output item's item meta is empty
        ItemMeta outputItemMeta = outputItemStack.getItemMeta();
        if(outputItemMeta == null)
        {
            return;
        }

        String displayName = inputText;
        HumanEntity viewer = anvilView.getPlayer();

        if(!(viewer instanceof Player)) {
            return;
        }

        Player player = (Player) viewer;

        if(player.hasPermission("missing_colors.anvil.hex_codes") || player.isOp()) {
            displayName = colorUtils.translateHexCodes(displayName);
        }

        if(player.hasPermission("missing_colors.anvil.color_codes") || player.isOp()) {
            displayName = colorUtils.translateLegacyColorCodes(displayName);
        }

        if(player.hasPermission("missing_colors.anvil.formatting_codes") || player.isOp()) {
            displayName = colorUtils.translateLegacyFormattingCodes(displayName);
        }

        outputItemMeta.setDisplayName(displayName);
        outputItemStack.setItemMeta(outputItemMeta);
    }
}
