package yy.gourlitburo.yeyaknife;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class ServerHandler extends Handler {
    public ServerHandler() {
        final String empty = "";

        this.register("ipbans", (String x, String[] args) -> {
            Set<String> ips = Bukkit.getIPBans();
            if (ips.isEmpty()) return "None.";
            return String.join(", ", ips);
        });

        this.register("banip", (String x, String[] args) -> {
            Bukkit.banIP(args[0]);
            return empty;
        });

        this.register("unbanip", (String x, String[] args) -> {
            Bukkit.unbanIP(args[0]);
            return empty;
        });

        this.register("broadcastmessage", (String x, String[] args) -> {
            String msg = Main.colorize(String.join(" ", args));
            Bukkit.broadcastMessage(msg);
            return empty;
        });

        this.register("onlinemode", (String x, String[] args) -> {
            return Bukkit.getOnlineMode() ? "true" : "false";
        });

        this.register("ip", (String x, String[] args) -> {
            return Bukkit.getIp();
        });

        this.register("maxplayers", (String x, String[] args) -> {
            return Integer.toString(Bukkit.getMaxPlayers());
        });

        this.register("worlds", (String x, String[] args) -> {
            List<World> worlds = Bukkit.getWorlds();
            List<String> names = new ArrayList<>();
            for (World world : worlds) names.add(world.getName());
            return String.join(", ", names);
        });
    }

    @Override
    public String handle(String cmd, String[] args) {
        return this.map.get(cmd).apply(null, args);
    }
}
