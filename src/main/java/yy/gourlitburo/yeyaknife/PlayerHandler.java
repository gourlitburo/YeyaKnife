package yy.gourlitburo.yeyaknife;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerHandler extends Handler {
    public PlayerHandler() {
        this.register("displayname", (Player player, String[] args) -> {
            if (args.length > 0)
                player.setDisplayName(Main.colorize(args[0]));
            return player.getDisplayName();
        });

        this.register("playerlistname", (Player player, String[] args) -> {
            if (args.length > 0)
                player.setPlayerListName(Main.colorize(args[0]));
            return player.getPlayerListName();
        });

        this.register("address", (Player player, String[] args) -> {
            return player.getAddress().toString();
        });

        this.register("kickplayer", (Player player, String[] args) -> {
            player.kickPlayer(args.length > 0 ? String.join(" ", args) : null);
            return "";
        });

        this.register("statistic", (Player player, String[] args) -> {
            Statistic statistic = Statistic.valueOf(args[0]);
            return Integer.toString(player.getStatistic(statistic));
        });

        this.register("playertime", (Player player, String[] args) -> {
            return Long.toString(player.getPlayerTime());
        });

        this.register("giveexp", (Player player, String[] args) -> {
            player.giveExp(Integer.valueOf(args[0]));
            return Integer.toString(player.getTotalExperience());
        });

        this.register("giveexplevels", (Player player, String[] args) -> {
            player.giveExpLevels(Integer.valueOf(args[0]));
            return Integer.toString(player.getLevel());
        });

        this.register("level", (Player player, String[] args) -> {
            if (args.length > 0)
                player.setLevel(Integer.valueOf(args[0]));
            return Integer.toString(player.getLevel());
        });

        this.register("totalexperience", (Player player, String[] args) -> {
            if (args.length > 0)
                player.setTotalExperience(Integer.valueOf(args[0]));
            return Integer.toString(player.getTotalExperience());
        });

        // TODO: allow multi-word arguments using ""
        this.register("sendtitle", (Player player, String[] args) -> {
            String title = Main.colorize(args.length > 0 ? args[0] : "");
            String subtitle = Main.colorize(args.length > 1 ? args[1] : "");
            player.sendTitle(title, subtitle, 10, 70, 20);
            return "";
        });
    }

    @Override
    public String handle(String[] command) {
        String cmd = command[0].toLowerCase();
        if (!this.map.containsKey(cmd)) return null;
        if (command.length < 1) return "Error: No player specified.";

        Player player = Bukkit.getPlayer(command[1]);
        String[] args = Arrays.copyOfRange(command, 2, command.length);
        return this.map.get(cmd).apply(player, args);
    }
}
