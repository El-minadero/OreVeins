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

import java.util.HashMap;
import java.util.List;

import geometryClasses.TwoPoint;


import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.scheduler.BukkitTask;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.OreVeins;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public final class EventListeners implements Listener 
{

	@EventHandler
	public void onLoad(ChunkLoadEvent event) 
	{
		if (event.getWorld().getName().equalsIgnoreCase("world") && event.isNewChunk()) 
		{
			BukkitTask removeDefaultOres = new RemoveOreTask(event.getChunk()).runTask(OreVeins.getPlugin(OreVeins.class));
		}
	}
	
	@EventHandler 
	public void  onUnload(ChunkUnloadEvent event)
	{
	}
	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		
	}
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		if(event.getWorld().getEnvironment().toString().contains("NORMAL"))
		{
			TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ(),true);
			OreGenerator.GenerateOres(chunk);
		}
	}
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event)
	{
		//PointMapping.refreshLoadedPoints();
	}
}
