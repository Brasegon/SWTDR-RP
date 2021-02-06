package fr.brangers.swtdrrp.chat;

import fr.brangers.swtdrrp.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Chat implements Listener {
    private Main main;
    private List<Entity> entity = new ArrayList<>();

    public Chat(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoined(PlayerJoinEvent event) {
        main.localChat.add(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerJoin(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();
        Set<Player> recipients = event.getRecipients();
        int mode = 0;
        if (Main.localChat.contains(p.getUniqueId()))
            if (event.getMessage().charAt(0) == '!') {
                String format = "%1$s crie: " + ChatColor.RED + "%2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage().replaceFirst("!", ""));
                mode = 1;
            } else if (event.getMessage().charAt(0) == '#') {
                String format = "%1$s chuchote: " + ChatColor.GREEN + "%2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage().replaceFirst("#", ""));
                mode = 2;
            } else if (event.getMessage().charAt(0) == '*') {
                String format = "%1$s " + ChatColor.GOLD + "%2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage().replaceFirst("\\*", ""));
                mode = 3;
            } else if (event.getMessage().charAt(0) == ':') {
                String format = ChatColor.GOLD + "[GENERAL] %1$s " + ChatColor.WHITE + "%2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage().replaceFirst(":", ""));
                mode = 4;

            } else if (event.getMessage().charAt(0) == '(') {
                String format = ChatColor.GRAY + "[HRP] %1$s " + ChatColor.GRAY + "%2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage().replaceFirst("\\(", ""));
                mode = 5;

            } else {
                String format = "%1$s dit: %2$s";
                event.setFormat(format);
                event.setMessage(event.getMessage());
                mode = 6;
            }
        for (Player receiver : Bukkit.getOnlinePlayers()) {
            if (mode == 1) {
                recipients.remove(receiver);
                if (Main.inRange(p, receiver, 100)) {
                    recipients.add(p);
                    recipients.add(receiver);
                }
            } else if (mode == 2) {
                recipients.remove(receiver);
                if (Main.inRange(p, receiver, 3)) {
                    recipients.add(p);
                    recipients.add(receiver);
                }
            } else if (mode == 3) {
                recipients.remove(receiver);
                if (Main.inRange(p, receiver, 20)) {
                    recipients.add(p);
                    recipients.add(receiver);
                }
            } else if (mode == 4) {
                recipients.remove(receiver);
                recipients.add(p);
                recipients.add(receiver);
            } else if (mode == 5) {
                recipients.remove(receiver);
                if (Main.inRange(p, receiver, 20)) {
                    recipients.add(p);
                    recipients.add(receiver);
                }
            } else if (mode == 6) {
                recipients.remove(receiver);
                if (Main.inRange(p, receiver, 20)) {
                    recipients.add(p);
                    recipients.add(receiver);
                }
            }
        }
    }
}
