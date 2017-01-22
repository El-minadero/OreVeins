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

import geometryClasses.VeinMember;
import geometryClasses.ThreePoint;
import geometryClasses.ThreePointBlock;
import geometryClasses.TwoPoint;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;


import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


import com.icloud.kevinmendoza.OreVeins.OreVeins;

/*this vein drawer class draws any ores within a 3d string object array
 * such simple, so block, much craft, shine daimond!
 * 
 */
public class IdentifyOres extends BukkitRunnable
{
	private Random rand;
	private TwoPoint coords;
	private VeinMember[][][] centers;
	private final Chunk testChunk;
	private ArrayList<ThreePointBlock> blocks;
	public IdentifyOres(Chunk chunkObj, TwoPoint coords, VeinMember[][][] veinMembers)
	{
		this.blocks = new ArrayList<ThreePointBlock>();
		this.coords = coords;
		this.centers = veinMembers;
		this.testChunk = chunkObj;
	}
	
	@Override
	public void run() 
	{
		rand = ThreadLocalRandom.current();
		ChunkSnapshot snapshot = this.testChunk.getChunkSnapshot(true, false, false);
		Block block;
		Material material;
		ThreePoint minePoint;
		ThreePoint localPoint;
		int h;
		for(int x=0;x<16;x++)
		{
			for(int z=0;z<16;z++)
			{
				h = snapshot.getHighestBlockYAt(x, z);
				if(h>130)
					h = 130;
				for(int y=3;y<h;y++)
				{
					if(centers[x][y][z]!=null)
					{
						block = testChunk.getBlock(x, y, z);
						if(!isType(block.getType(),Material.AIR))
						{
							material = centers[x][y][z].material;
							minePoint = new ThreePoint(x+16*testChunk.getX(),y,z+16*testChunk.getZ());
							localPoint = new ThreePoint(x,y,z);
							//code for mineshaft creation
							if(isType(block.getType(),Material.STONE))
							{
								setBlock(material,localPoint);			
							}
							else if(isType(block.getType(),Material.DIRT))
							{
								if(rand.nextInt(4)==0)
								{
									setBlock(material,localPoint);
								}
							}
							else if(isType(block.getType(),Material.GRASS))
							{
								if(rand.nextInt(10)==0)
								{
									setBlock(material,localPoint);
								}
							}
							else if(isType(block.getType(),Material.GRAVEL))
							{
								if(rand.nextInt(3)==0)
								{
									setBlock(material,localPoint);
								}
							}
							else if(isType(block.getType(),Material.SAND))
							{
								if(rand.nextInt(3)==0)
								{
									setBlock(material,localPoint);
								}
							}
							else if(isType(block.getType(),Material.SANDSTONE))
							{
								if(rand.nextInt(5)==0)
								{
									setBlock(material,localPoint);
								}
							}
							else if(isType(block.getType(),Material.STAINED_CLAY) && 
								   (isType(material, Material.COAL_ORE) 		  ||
									isType(material, Material.IRON_ORE)			  ))
							{
								setBlock(material,localPoint);
							}
						}
					}
				}
			}
		}
		BukkitTask forsure = new SetOres(coords,blocks).runTaskLater(OreVeins.getPlugin(OreVeins.class),1);
	}
	
	private boolean isType(Material mat1, Material mat2)
	{
		if(mat1.compareTo(mat2)==0)
			return true;
		else
			return false;
	}
	
	
	private  void setBlock(Material ore, ThreePoint point)
	{
		ThreePointBlock block = new ThreePointBlock(ore,point.x,point.y,point.z);
		ThreePoint minePoint = new ThreePoint(point.x+16*testChunk.getX(),point.y,point.z+16*testChunk.getZ());
		blocks.add(block);
	}
	
}

