package net.crafttorch.ctsimpleantirelog;

import java.util.UUID;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskAR extends BukkitRunnable {
    private final Bar bar;
    private final Player player;
    int timeRelog;
    private final HashMap<UUID, Double> progressHash;
    private final HashMap<UUID, Double> timeHash;
    
    public HashMap<UUID, Double> getProgressHash() {
        return this.progressHash;
    }
    
    public HashMap<UUID, Double> getTimeHash() {
        return this.timeHash;
    }
    
    public TaskAR(final Bar bar, final Player player, final AR plugin) {
        this.progressHash = new HashMap<>();
        this.timeHash = new HashMap<>();
        this.bar = bar;
        this.player = player;
        this.timeRelog = plugin.getConfig().getInt("Anti-relog.Time");
        this.progressHash.put(player.getUniqueId(), 1.0);
        this.timeHash.put(player.getUniqueId(), 1.0 / (this.timeRelog * 20));
    }
    
    public void run() {
        if (player != null) {
            bar.getplayerBossBar().get(player.getUniqueId()).setProgress(getProgressHash().get(player.getUniqueId()));
            bar.getplayerBossBar().get(player.getUniqueId()).setTitle(Bar.format("&6Anti-Relog (" + Math.round((getProgressHash().get(player.getUniqueId()) - getTimeHash().get(player.getUniqueId())) * timeRelog) + ")"));
            getProgressHash().put(player.getUniqueId(), getProgressHash().get(player.getUniqueId()) - getTimeHash().get(player.getUniqueId()));
            if (getProgressHash().get(player.getUniqueId()) <= 0.0) {
                bar.getplayerBossBar().get(player.getUniqueId()).removePlayer(player);
                bar.getplayerBossBar().get(player.getUniqueId()).setVisible(false);
                bar.getCastedPlayers().put(player.getUniqueId(), false);
                cancel();
                bar.getPlayerTask().remove(player.getUniqueId());
                bar.sendedMsg.put(player.getUniqueId(), false);
            }
            if (player.isDead()) {
                bar.getplayerBossBar().get(player.getUniqueId()).removePlayer(player);
                bar.getplayerBossBar().get(player.getUniqueId()).setVisible(false);
                bar.getCastedPlayers().put(player.getUniqueId(), false);
                cancel();
                bar.getPlayerTask().remove(player.getUniqueId());
                bar.sendedMsg.put(player.getUniqueId(), false);
            }
        }
    }
}
