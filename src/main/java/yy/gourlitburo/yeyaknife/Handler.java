package yy.gourlitburo.yeyaknife;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import org.bukkit.entity.Player;

public abstract class Handler {
    protected Map<String, BiFunction<Player, String[], String>> map = new HashMap<>();
    
    public String handle(String[] args) {
        return null;
    }

    public boolean register(String name, BiFunction<Player, String[], String> func) {
        if (this.map.containsKey(name)) return false;

        this.map.put(name, func);
        return true;
    }

    public Set<String> getCommands() {
        return this.map.keySet();
    }
}
