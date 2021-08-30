package net.crafttorch.ctsimpleantirelog;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor {
    AR plugin;
    
    public Commands(final AR plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("sar")) {
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage("§c[Simple Anti Relog] §fSuccessfully reloaded!");
            }
            else {
                sender.sendMessage("§c[Simple Anti Relog] §fcorrect usage /sar <reload>!");
            }
            return true;
        }
        return true;
    }
}
