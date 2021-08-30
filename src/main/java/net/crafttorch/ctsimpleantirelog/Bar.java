package net.crafttorch.ctsimpleantirelog;



import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;
import org.bukkit.boss.BossBar;
import java.util.UUID;
import java.util.HashMap;

public class Bar {
    private final AR plugin;
    HashMap<UUID, Boolean> castedPlayers;
    HashMap<UUID, BossBar> playerBossBar;
    HashMap<UUID, TaskAR> playerTask;
    HashMap<UUID, Boolean> sendedMsg;
    
    public Bar(final AR plugin) {
        this.castedPlayers = new HashMap<>();
        this.playerBossBar = new HashMap<>();
        this.playerTask = new HashMap<>();
        this.sendedMsg = new HashMap<>();
        this.plugin = plugin;
    }
    
    public HashMap<UUID, BossBar> getplayerBossBar() {
        return this.playerBossBar;
    }
    
    public HashMap<UUID, Boolean> getCastedPlayers() {
        return this.castedPlayers;
    }
    
    public HashMap<UUID, TaskAR> getPlayerTask() {
        return this.playerTask;
    }
    
    public void createBar(final Player player) {
        final String color = plugin.getConfig().getString("Anti-relog.Bar-Color");
        final String style = plugin.getConfig().getString("Anti-relog.Bar-Style");
        final BossBar bossBar = Bukkit.createBossBar(format("&6Anti-Relog"), BarColor.valueOf(color), BarStyle.valueOf(style));
        getplayerBossBar().put(player.getUniqueId(), bossBar);
    }
    
    public void removeBar(final Player player) {
        getplayerBossBar().remove(player.getUniqueId());
    }
    
    public void castPlayer(final Player player) {
        if (sendedMsg.get(player.getUniqueId()) == null || !sendedMsg.get(player.getUniqueId())) {
            final List<String> castMessage = plugin.getConfig().getStringList("Anti-relog.Anti-Relog-Message");
            for (final String messages : castMessage) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages));
            }
            sendedMsg.put(player.getUniqueId(), true);
        }
        if (getCastedPlayers().get(player.getUniqueId()) != null && getCastedPlayers().get(player.getUniqueId())) {
            getPlayerTask().get(player.getUniqueId()).cancel();
            getCastedPlayers().put(player.getUniqueId(), false);
            getPlayerTask().remove(player.getUniqueId());
        }
        getplayerBossBar().get(player.getUniqueId()).addPlayer(player);
        getplayerBossBar().get(player.getUniqueId()).setVisible(true);
        getCastedPlayers().put(player.getUniqueId(), true);
        final TaskAR task = new TaskAR(this, player, plugin);
        getPlayerTask().put(player.getUniqueId(), task);
        getPlayerTask().get(player.getUniqueId()).runTaskTimer(plugin, 0L, 0L);
    }
    
    public static String format(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
