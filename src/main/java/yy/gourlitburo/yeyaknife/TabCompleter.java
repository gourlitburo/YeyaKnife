package yy.gourlitburo.yeyaknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

public class TabCompleter implements org.bukkit.command.TabCompleter {
    private Executor executor;

    public TabCompleter(Executor executor) { this.executor = executor; }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> emptyList = new ArrayList<>();

        if (args.length == 1) { // complete handlers
            return StringUtil.copyPartialMatches(
                args[0],
                this.executor.getHandlerNames(),
                emptyList
                );
        } else if (args.length == 2) { // complete subcommands
            Set<String> subcommands = this.executor.getSubcommands(args[0]);
            if (subcommands == null) return emptyList;
            return StringUtil.copyPartialMatches(args[1], subcommands, emptyList);
        }
        
        return null;
    }
}
