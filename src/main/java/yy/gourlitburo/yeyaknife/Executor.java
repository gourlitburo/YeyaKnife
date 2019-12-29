package yy.gourlitburo.yeyaknife;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Executor implements CommandExecutor {
    private Map<String, Handler> handlers = new HashMap<>();

    public Executor() {
        this.handlers.put("player", new PlayerHandler());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;

        String handlerName = args[0].toLowerCase();
        Handler handler = this.handlers.get(handlerName);
        
        if (handler == null) {
            sender.sendMessage(String.format("Error: unknown command '%s'.", handlerName));
        } else {
            String result = handler.handle(Arrays.copyOfRange(args, 1, args.length));
            if (result == null) sender.sendMessage("Error: unknown subcommand.");
            else if (result.length() > 0) sender.sendMessage(result);
        }
        return true;
    }

    public Set<String> getHandlerNames() {
        return this.handlers.keySet();
    }

    public Set<String> getSubcommands(String handlerName) {
        if (!this.handlers.containsKey(handlerName)) return null;
        return this.handlers.get(handlerName).getCommands();
    }
}
