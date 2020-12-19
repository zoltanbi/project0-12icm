package net.mcpandemic.core.voting;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class VoteMap {

    private ArrayList<Maps> maps;
    private LinkedHashMap<Maps, Integer> votableMaps;

    public VoteMap() {
        maps = new ArrayList<Maps>();
        loadMaps();
        votableMaps = new LinkedHashMap<Maps, Integer>();
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

    public LinkedHashMap<Maps, Integer> getVotableMaps() {
        return votableMaps;
    }

}
