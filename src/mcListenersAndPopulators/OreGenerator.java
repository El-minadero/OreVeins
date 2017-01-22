/*******************************************************************************
 * OreVeins realistic ore distribution plugin
 * Copyright (C) 2014  Kevin Mendoza
 * kevinmendoza@mac.com
 * Major Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package mcListenersAndPopulators;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import java.util.Random;

import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.OreVeins;

public class OreGenerator 
{

	public static void GenerateOres(TwoPoint chunk) 
	{
		Random rand = new Random();
		TwoPoint chunkPoint = new TwoPoint(chunk.x,chunk.z,true);
		chunkPoint.x = chunkPoint.x*16;
		chunkPoint.z = chunkPoint.z*16;
		ThreePoint start = startPoint(chunkPoint);
		String biome = Bukkit.getServer().getWorld("world").getBiome(start.x, start.z).toString();
		double dia = OreVeins.theDefaults.diamond.modifiers.modifySpawn(biome, OreVeins.theDefaults.diamond.probToSpawn);
		double em = OreVeins.theDefaults.emerald.modifiers.modifySpawn(biome, OreVeins.theDefaults.emerald.probToSpawn);
		double lap = OreVeins.theDefaults.lapiz.modifiers.modifySpawn(biome, OreVeins.theDefaults.lapiz.probToSpawn);
		double gold = OreVeins.theDefaults.gold.modifiers.modifySpawn(biome, OreVeins.theDefaults.gold.probToSpawn);
		double iron = OreVeins.theDefaults.iron.modifiers.modifySpawn(biome, OreVeins.theDefaults.iron.probToSpawn);
		double red = OreVeins.theDefaults.redstone.modifiers.modifySpawn(biome, OreVeins.theDefaults.redstone.probToSpawn);
		double coal = OreVeins.theDefaults.coal.modifiers.modifySpawn(biome, OreVeins.theDefaults.coal.probToSpawn);
		double bif = OreVeins.theDefaults.bif.modifiers.modifySpawn(biome, OreVeins.theDefaults.bif.probToSpawn);
		int height =  Bukkit.getServer().getWorld("world").getHighestBlockAt(start.x, start.z).getY();
		if(dia != 0 && rand.nextInt((int)(100/dia))==0)
		{
			DebugLogger.console("Made Diamond Pipe at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask diamondCreate = new CreateOreThread(start, "diamond", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		start = startPoint(chunkPoint);
		if(coal != 0 && rand.nextInt((int)(100/coal))==0)
		{
			DebugLogger.console("Made Coal Seam at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask coalCreate = new CreateOreThread(start, "coal", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(bif != 0 && rand.nextInt((int)(100/bif))==0)
		{
			DebugLogger.console("Made Banded Iron Formation (BIF) at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask bifCreate = new CreateOreThread(start, "bif", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(lap != 0 && rand.nextInt((int)(100/lap))==0)
		{
			DebugLogger.console("Made Lapiz Deposit at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask lapizCreate = new CreateOreThread( start, "lapiz", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(em != 0 && rand.nextInt((int)(100/em))==0)
		{
			DebugLogger.console("Made Emerald Vein at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask emeraldCreate = new CreateOreThread( start, "emerald", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(red != 0 && rand.nextInt((int)(100/red))==0)
		{
			DebugLogger.console("Made Redstone Vein at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask redstoneCreate = new CreateOreThread(start, "redstone", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(gold != 0 && rand.nextInt((int)(100/gold))==0)
		{
			DebugLogger.console("Made Gold vein at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask goldCreate = new CreateOreThread( start, "gold", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
		 start = startPoint(chunkPoint);
		if(iron != 0 && rand.nextInt((int)(100/iron))==0)
		{
			DebugLogger.console("made Iron vein at"+start.toString());
			OreVeins.veinsInProggress++;
			BukkitTask ironCreate = new CreateOreThread( start, "iron", biome, height).runTaskAsynchronously(OreVeins.getPlugin(OreVeins.class));
		}
	}

	private static ThreePoint startPoint(TwoPoint chunk)
	{
		Random rand = ThreadLocalRandom.current();
		ThreePoint start = new ThreePoint();
		start.x+=chunk.x;
		start.z+=chunk.z;
		int height = rand.nextInt(Bukkit.getServer().getWorld("world").getHighestBlockAt(start.x, start.z).getY())+1;
		start.y = height;
		return start;
	}

}
