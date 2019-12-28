package yy.gourlitburo.yeyaknife;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    static Logger logger;
    Server server = this.getServer();
    PluginManager manager = Bukkit.getPluginManager();

    @Override
    public void onEnable() {
        logger = this.getLogger();

        PluginCommand command = getCommand("yk");
        command.setExecutor(new Executor());
        
        logger.info("YeyaKnife ready.");
    }
}
