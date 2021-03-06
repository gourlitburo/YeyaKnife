package yy.gourlitburo.yeyaknife;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public abstract class Handler {
    protected Map<String, BiFunction<String, String[], String>> map = new HashMap<>();
    
    public String handle(String cmd, String[] args) {
        return null;
    }

    public boolean register(String name, BiFunction<String, String[], String> func) {
        if (this.map.containsKey(name)) return false;

        this.map.put(name, func);
        return true;
    }

    public Set<String> getSubcomands() {
        return this.map.keySet();
    }

    public boolean hasSubcommand(String subcommand) {
        return this.map.containsKey(subcommand);
    }
}
