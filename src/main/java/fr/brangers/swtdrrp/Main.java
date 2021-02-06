package fr.brangers.swtdrrp;

import fr.brangers.swtdrrp.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {
    public static ArrayList<UUID> localChat = new ArrayList<>();
    public static ArrayList<UUID> GlobalChat = new ArrayList<>();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Chat(this), this);
        for (Player all : Bukkit.getOnlinePlayers()) {
            localChat.add(all.getUniqueId());
            continue;
        }
    }
    @Override
    public void onDisable() {
    }
    @Override
    public void onLoad() {
    }

    public static boolean inRange(Player player, Player receiver, int radius) {
        if (receiver.getLocation().getWorld().equals(player.getLocation().getWorld()) &&
                receiver.getLocation().distanceSquared(player.getLocation()) <= (radius * radius))
            return true;
        return false;
    }
}
