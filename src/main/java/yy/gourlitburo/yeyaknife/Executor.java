package yy.gourlitburo.yeyaknife;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Executor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String handlerName = args[0].toLowerCase();

        Handler handler = null;
        switch (handlerName) {
            case "player": handler = new PlayerHandler(); break;
        }
        
        if (handler != null) {
            String result = handler.handle(Arrays.copyOfRange(args, 1, args.length));
            if (result == null) sender.sendMessage("Error.");
            else if (result.length() > 0) sender.sendMessage(result);
        } else {
            sender.sendMessage(String.format("Error: unknown command '%s'.", handlerName));
        }
        return true;
    }
}
