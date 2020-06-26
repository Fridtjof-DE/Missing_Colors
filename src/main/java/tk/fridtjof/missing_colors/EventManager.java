package tk.fridtjof.missing_colors;

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
import tk.fridtjof.missing_colors.utils.ColorCodes;
import tk.fridtjof.missing_colors.utils.FormattingCodes;

public class EventManager implements Listener {

    ColorCodes c = new ColorCodes();
    FormattingCodes f = new FormattingCodes();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if(player.hasPermission("missing_colors.chat.color_codes") || player.isOp()) {
            event.setMessage(c.Format(event.getMessage()));
        }

        if(player.hasPermission("missing_colors.chat.formatting_codes") || player.isOp()) {
            event.setMessage(f.Format(event.getMessage()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWrite(SignChangeEvent event) {
        if (event.getPlayer().hasPermission("missing_colors.signs.color_codes") || event.getPlayer().isOp()) {

            event.setLine(0, c.Format(event.getLine(0)));
            event.setLine(1, c.Format(event.getLine(1)));
            event.setLine(2, c.Format(event.getLine(2)));
            event.setLine(3, c.Format(event.getLine(3)));
        }

        if (event.getPlayer().hasPermission("missing_colors.signs.formatting_codes") || event.getPlayer().isOp()) {

            event.setLine(0, f.Format(event.getLine(0)));
            event.setLine(1, f.Format(event.getLine(1)));
            event.setLine(2, f.Format(event.getLine(2)));
            event.setLine(3, f.Format(event.getLine(3)));
        }
    }

    @EventHandler
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

                                if(player.hasPermission("missing_colors.anvil.color_codes")) {
                                    ItemMeta meta = itemStack.getItemMeta();
                                    meta.setDisplayName(c.Format(meta.getDisplayName()));
                                    itemStack.setItemMeta(meta);
                                }

                                if(player.hasPermission("missing_colors.anvil.formatting_codes")) {
                                    ItemMeta meta = itemStack.getItemMeta();
                                    meta.setDisplayName(f.Format(meta.getDisplayName()));
                                    itemStack.setItemMeta(meta);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
