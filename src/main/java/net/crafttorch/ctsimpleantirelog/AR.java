package net.crafttorch.ctsimpleantirelog;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Trident;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.Objects;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class AR extends JavaPlugin implements Listener {
    public Bar bar;
    
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(getCommand("sar")).setExecutor(new Commands(this));
        Objects.requireNonNull(getCommand("sar")).setTabCompleter(new Tab());
        bar = new Bar(this);
        getServer().getPluginManager().registerEvents(new CustomDeathMessages(this), this);
    }
    
    public void onDisable() {
    }
    
    public void loadConfig() {
        getConfig().options().header("///////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n///////////////////////////\u2588\u2580\u2003\u2588\u2003\u2588\u2580\u2584\u2580\u2588\u2003\u2588\u2580\u2588\u2003\u2588  \u2003\u2588\u2580\u2580\u2003 \u2003\u2584\u2580\u2588\u2003\u2588\u2584 \u2588\u2003\u2580\u2588\u2580\u2003\u2588\u2003 \u2003\u2588\u2580\u2588\u2003\u2588\u2580\u2580\u2003\u2588  \u2003\u2588\u2580\u2588\u2003\u2588\u2580\u2580///////////////////////////\n///////////////////////////\u2584\u2588\u2003\u2588\u2003\u2588 \u2580 \u2588\u2003\u2588\u2580\u2580\u2003\u2588\u2584\u2584\u2003\u2588\u2588\u2584\u2003 \u2003\u2588\u2580\u2588\u2003\u2588 \u2580\u2588\u2003 \u2588 \u2003\u2588\u2003 \u2003\u2588\u2580\u2584\u2003\u2588\u2588\u2584\u2003\u2588\u2584\u2584\u2003\u2588\u2584\u2588\u2003\u2588\u2584\u2588///////////////////////////\n///////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n/////////////////////////////////////////////////\ud83c\udde8\u200b\u200b\u200b\u200b\u200b\ud83c\uddf7\u200b\u200b\u200b\u200b\u200b\ud83c\uddea\u200b\u200b\u200b\u200b\u200b\ud83c\udde6\u200b\u200b\u200b\u200b\u200b\ud83c\uddf9\u200b\u200b\u200b\u200b\u200b\ud83c\uddea\u200b\u200b\u200b\u200b\u200b\ud83c\udde9\u200b\u200b\u200b\u200b\u200b \ud83c\udde7\u200b\u200b\u200b\u200b\u200b\ud83c\uddfe\u200b\u200b\u200b\u200b\u200b 2\ud83c\uddec\u200b\u200b\u200b\u200b\u200b\ud83c\uddf7\u200b\u200b\u200b\u200b\u200b\ud83c\udde6\u200b\u200b\u200b\u200b\u200b\ud83c\uddf2\u200b\u200b\u200b\u200b\u200b\ud83c\uddf8\u200b\u200b\u200b\u200b\u200b\ud83c\uddee\u200b\u200b\u200b\u200b\u200b\ud83c\uddf3\u200b\u200b\u200b\u200b\u200b/////////////////////////////////////////////////\n///////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n///////////////////////////////////////DO NOT REMOVE THIS COPYRIGHT MARK //////////////////////////////////////////\n///////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n\nPlayer re-entry is required for Bar-Color and Bar-Style changes\n\n/sar reload - for reload this config!\n\nBarColor that can use:\nBLUE\nGREEN\nPINK\nPURPLE\nRED\nWHITE\nYELLOW\nBarStyle that can use:\nSEGMENTED_10\nSEGMENTED_12\nSEGMENTED_20\nSEGMENTED_6\nSOLID");
        getConfig().options().copyDefaults(true);
        saveConfig();
        reloadConfig();
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        if (getConfig().getBoolean("Anti-relog.Enable")) {
            final Player player = e.getPlayer();
            if (!bar.getplayerBossBar().containsKey(player.getUniqueId())) {
                bar.createBar(player);
            }
        }
    }
    
    @EventHandler
    public void onLeave(final PlayerQuitEvent e) {
        if (getConfig().getBoolean("Anti-relog.Enable")) {
            final Player player = e.getPlayer();
            if (bar.getplayerBossBar().get(player.getUniqueId()).getPlayers().contains(player)) {
                bar.getplayerBossBar().get(player.getUniqueId()).removePlayer(player);
                bar.getPlayerTask().get(player.getUniqueId()).cancel();
                bar.getPlayerTask().remove(player.getUniqueId());
                bar.getCastedPlayers().remove(player.getUniqueId());
                bar.sendedMsg.remove(player.getUniqueId());
                player.setHealth(0.0);
            }
            if (bar.getplayerBossBar().containsKey(player.getUniqueId())) {
                bar.removeBar(player);
            }
        }
    }
    
    @EventHandler
    public void onDamage(final EntityDamageByEntityEvent e) {
        if (getConfig().getBoolean("Anti-relog.Enable")) {
            if (e.getDamage() == 0.0) {
                return;
            }
            if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
                if (e.getDamager() instanceof Trident) {
                    bar.castPlayer((Player)e.getEntity());
                    return;
                }
                if (e.getDamager() instanceof Arrow) {
                    bar.castPlayer((Player)e.getEntity());
                    return;
                }
                if (getConfig().getBoolean("Anti-relog.Defender-Anti-Relog")) {
                    bar.castPlayer((Player)e.getEntity());
                }
                if (getConfig().getBoolean("Anti-relog.Attack-Anti-Relog")) {
                    bar.castPlayer((Player)e.getDamager());
                }
            }
        }
    }
}
