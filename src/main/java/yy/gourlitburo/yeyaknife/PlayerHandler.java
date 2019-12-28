package yy.gourlitburo.yeyaknife;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerHandler implements Handler {
    @Override
    public String handle(String[] command) {
        String cmd = command[0].toLowerCase();
        String name = command[1];
        boolean hasArgs = command.length > 2;
        String[] args = Arrays.copyOfRange(command, 2, command.length);

        Player player = Bukkit.getPlayer(name);

        switch (cmd) {
            case "displayname":
                if (hasArgs) player.setDisplayName(args[0]);
                return player.getDisplayName();
            case "playerlistname":
                if (hasArgs) player.setPlayerListName(args[0]);
                return player.getPlayerListName();
            case "address":
                return player.getAddress().toString();
            case "kickplayer":
                player.kickPlayer(args.length > 0 ? String.join(" ", args) : null);
                return "";
            case "statistic":
                Statistic statistic = Statistic.valueOf(args[0]);
                return Integer.toString(player.getStatistic(statistic));
            case "playertime":
                return Long.toString(player.getPlayerTime());
            case "giveexp":
                player.giveExp(Integer.valueOf(args[0]));
                return Integer.toString(player.getTotalExperience());
            case "giveexplevels":
                player.giveExpLevels(Integer.valueOf(args[0]));
                return Integer.toString(player.getLevel());
            case "level":
                if (hasArgs) player.setLevel(Integer.valueOf(args[0]));
                return Integer.toString(player.getLevel());
            case "totalexperience":
                if (hasArgs) player.setTotalExperience(Integer.valueOf(args[0]));
                return Integer.toString(player.getTotalExperience());
            case "sendtitle":
                String title = ChatColor.translateAlternateColorCodes('&', args.length > 0 ? args[0] : "");
                String subtitle = ChatColor.translateAlternateColorCodes('&', args.length > 1 ? args[1] : "");
                player.sendTitle(title, subtitle, 10, 70, 20);
                return "";
        }

        return null;
    }
}
