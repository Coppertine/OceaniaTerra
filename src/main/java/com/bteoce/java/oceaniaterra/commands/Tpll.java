package com.bteoce.java.oceaniaterra.commands;

import com.bteoce.java.oceaniaterra.utils.LocationUtil;
import net.buildtheearth.terraminusminus.TerraConstants;
import net.buildtheearth.terraminusminus.TerraMinusMinus;
import net.buildtheearth.terraminusminus.projection.GeographicProjection;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import net.buildtheearth.terraminusminus.projection.dymaxion.BTEDymaxionProjection;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpll implements CommandExecutor {
    private static final GeographicProjection projection = new BTEDymaxionProjection();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tpll")){
            Player player = (Player) sender;
            if (args.length == 2) {
                if (player.hasPermission("oceaniaterra.tpll")) {

                    double[] coordinates = new double[2];
                    coordinates[1] = Double.parseDouble(args[0].replace(",", ""));
                    coordinates[0] = Double.parseDouble(args[1]);


                    double[] mcCoordinates = new double[0];
                    try {
                        mcCoordinates = projection.fromGeo(coordinates[0], coordinates[1]);
                    } catch (OutOfProjectionBoundsException e) {
                        e.printStackTrace();
                    }

                    Location location = new Location(player.getWorld(), mcCoordinates[0], player.getWorld().getHighestBlockYAt((int) mcCoordinates[0], (int) mcCoordinates[1]) + 1, mcCoordinates[1]);

                    player.teleport(location);

                    player.sendMessage("§7Teleported to " + coordinates[0] + ", " + coordinates[1] + ".");
                    return true;
                } else {
                    player.sendMessage( "§7No permission for /tpll");
                    return true;
                }
            }else {
                player.sendMessage("§7Usage: /tpll <longitudes> <latitudes>");
                return true;
            }
        }
        return true;
    }
}
