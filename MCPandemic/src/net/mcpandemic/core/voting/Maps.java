package net.mcpandemic.core.voting;

import net.mcpandemic.core.Config;
import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;

import java.util.HashMap;

/**
 * Maps enum for getting all the maps and their locations.
 */
public enum Maps {

    BAILOUT("Bailout", Config.getArenaSpawn(0)),
    BAYVIEW("Bayview", Config.getArenaSpawn(1)),
    CENTRALCITY("Central City", Config.getArenaSpawn(2)),
    DOME("Dome", Config.getArenaSpawn(3)),
    ESTATE("Estate", Config.getArenaSpawn(4)),
    FALLEN("Fallen", Config.getArenaSpawn(5)),
    FIRINGRANGE("Firing Range", Config.getArenaSpawn(6)),
    GRIND("Grind", Config.getArenaSpawn(7)),
    HIJACKED("Hijacked", Config.getArenaSpawn(8)),
    INTERCHANGE("Interchange", Config.getArenaSpawn(9)),
    JUNGLE("Jungle", Config.getArenaSpawn(10));

    private String mapName;
    private Location location;

    Maps(String mapName, Location location) {
        this.mapName = mapName;
        this.location = location;
    }

    public String getMapName() {
        return mapName;
    }

    public Location getLocation() {
        return location;
    }

}
