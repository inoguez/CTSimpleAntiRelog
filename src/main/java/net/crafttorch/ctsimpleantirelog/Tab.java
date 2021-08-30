package net.crafttorch.ctsimpleantirelog;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class Tab implements TabCompleter {
    public List<String> onTabComplete(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (strings.length == 0) {
            return null;
        }
        if (strings.length == 1) {
            final List<String> lista = new ArrayList<>();
            lista.add("reload");
            return lista;
        }
        return null;
    }
}
