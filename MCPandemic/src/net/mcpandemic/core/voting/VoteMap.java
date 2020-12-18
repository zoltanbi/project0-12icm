package net.mcpandemic.core.voting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class VoteMap {

    private ArrayList<Maps> maps;
    private HashMap<Maps, Integer> votableMaps;

    public VoteMap() {
        maps = new ArrayList<Maps>();
        loadMaps();
        votableMaps = new HashMap<Maps, Integer>();
        randomMaps();
    }

    public void loadMaps() {
        maps.addAll(Arrays.asList(Maps.values()));
    }

    public void randomMaps() {
        Collections.shuffle(maps);
        votableMaps.put(maps.get(0), 0);
        votableMaps.put(maps.get(1), 0);
        votableMaps.put(maps.get(2), 0);
        votableMaps.put(maps.get(3), 0);
        votableMaps.put(maps.get(4), 0);
    }

    public HashMap<Maps, Integer> getVotableMaps() {
        return votableMaps;
    }

}
