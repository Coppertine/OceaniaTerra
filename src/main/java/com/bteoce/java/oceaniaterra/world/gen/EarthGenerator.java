package com.bteoce.java.oceaniaterra.world.gen;

import net.buildtheearth.terraminusminus.generator.CachedChunkData;
import net.buildtheearth.terraminusminus.generator.ChunkDataLoader;
import net.buildtheearth.terraminusminus.generator.EarthGeneratorSettings;
import net.buildtheearth.terraminusminus.substitutes.BlockState;
import net.buildtheearth.terraminusminus.substitutes.BukkitBindings;
import net.buildtheearth.terraminusminus.substitutes.ChunkPos;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import static java.lang.Math.min;

public class EarthGenerator extends ChunkGenerator {
    private Location spawnLocation = null;

    EarthGeneratorSettings settings = EarthGeneratorSettings.parse(EarthGeneratorSettings.BTE_DEFAULT_SETTINGS);
    ChunkDataLoader loader;

    public EarthGenerator()
    {
        this.loader = new ChunkDataLoader(settings);
    }
    @Override
    public void generateSurface(WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkData chunkData) {
        final int minY = worldInfo.getMinHeight();
        final int maxY = worldInfo.getMaxHeight();

        try {
            CachedChunkData terraData = this.loader.load(new ChunkPos(chunkX, chunkZ)).get(); //new ChunkPos(chunkX, chunkZ)
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {

                    int groundY = terraData.groundHeight(x, z);
                    int waterY = terraData.waterHeight(x, z);
                    //terraData.biome(x,z);
                    BlockState state = terraData.surfaceBlock(x, z);


                    //if (state != null) material = Material.BRICKS;

                    Material material = Material.GRASS_BLOCK;
                    // Generates mountains over 1700m only from stone
                    int randomizer = (int) Math.floor(Math.random()*(1700-1695+1)+1695);
                    if(groundY >= randomizer) {
                        material = Material.STONE;
                    }
                    //--------------------------------------------------------


                    for (int y = minY; y < Math.min(maxY, groundY); y++) chunkData.setBlock(x, y, z, Material.STONE);

                    if (groundY < maxY) {
                        if(state != null){
                            chunkData.setBlock(x, groundY, z, BukkitBindings.getAsBlockData(state));
                        } else {
                            chunkData.setBlock(x, groundY, z, material);
                        }
                    }
                    for (int y = groundY + 1; y <= Math.min(maxY, waterY); y++) chunkData.setBlock(x, y, z, Material.WATER);

                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean shouldGenerateSurface() {
        return false;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateBedrock() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

    @NotNull
    public List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return new ArrayList<BlockPopulator>();
    }

    @Nullable
    public Location getFixedSpawnLocation(@NotNull World world, @NotNull Random random) {
        if (spawnLocation == null)
            spawnLocation = new Location(world, 0, 0, 0);
        return spawnLocation;
    }



}
