package com.bteoce.java.oceaniaterra.world.gen;

import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.Collections;
import java.util.List;

public class EarthBiomeProvider extends BiomeProvider {
    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        return Biome.PLAINS;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        return Collections.singletonList(Biome.PLAINS);
    }
}
