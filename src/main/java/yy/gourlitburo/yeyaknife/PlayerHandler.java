package yy.gourlitburo.yeyaknife;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerHandler extends Handler {
    public PlayerHandler() {
        final String empty = "";

        this.register("displayname", (String x, String[] args) -> {
            if (args.length > 0)
                p(x).setDisplayName(Main.colorize(args[0]));
            return p(x).getDisplayName();
        });

        this.register("playerlistname", (String x, String[] args) -> {
            if (args.length > 0)
                p(x).setPlayerListName(Main.colorize(args[0]));
            return p(x).getPlayerListName();
        });

        this.register("address", (String x, String[] args) -> {
            return p(x).getAddress().toString();
        });

        this.register("kickplayer", (String x, String[] args) -> {
            p(x).kickPlayer(args.length > 0 ? String.join(" ", args) : null);
            return empty;
        });

        this.register("statistic", (String x, String[] args) -> {
            Statistic statistic = Statistic.valueOf(args[0]);
            return Integer.toString(p(x).getStatistic(statistic));
        });

        this.register("playertime", (String x, String[] args) -> {
            return Long.toString(p(x).getPlayerTime());
        });

        this.register("giveexp", (String x, String[] args) -> {
            p(x).giveExp(Integer.valueOf(args[0]));
            return Integer.toString(p(x).getTotalExperience());
        });

        this.register("giveexplevels", (String x, String[] args) -> {
            p(x).giveExpLevels(Integer.valueOf(args[0]));
            return Integer.toString(p(x).getLevel());
        });

        this.register("level", (String x, String[] args) -> {
            if (args.length > 0)
                p(x).setLevel(Integer.valueOf(args[0]));
            return Integer.toString(p(x).getLevel());
        });

        this.register("totalexperience", (String x, String[] args) -> {
            if (args.length > 0)
                p(x).setTotalExperience(Integer.valueOf(args[0]));
            return Integer.toString(p(x).getTotalExperience());
        });

        // TODO: allow multi-word arguments using ""
        this.register("sendtitle", (String x, String[] args) -> {
            String title = Main.colorize(args.length > 0 ? args[0] : "");
            String subtitle = Main.colorize(args.length > 1 ? args[1] : "");
            p(x).sendTitle(title, subtitle, 10, 70, 20);
            return empty;
        });
    }

    @Override
    public String handle(String cmd, String[] args) {
        if (args.length < 1) return "Error: No player specified.";
        return this.map.get(cmd).apply(
            args[0],
            Arrays.copyOfRange(args, 1, args.length)
            );
    }

    private Player p(String name) {
        return Bukkit.getPlayer(name);
    }
}
