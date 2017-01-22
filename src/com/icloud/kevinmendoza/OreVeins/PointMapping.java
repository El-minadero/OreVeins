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
package com.icloud.kevinmendoza.OreVeins;

import fileIO.VeinChunkReadWrite;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.VeinMember;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class PointMapping 
{
	private static HashMap<String,VeinMember[][][]> addedPoints;
	//^contains all points added in by ore generation
	private static HashMap<String,Boolean> populatedList;
	//^contains list of all chunks already generated
	//^contains list of all chunks already generated and Loaded?
	
	public static void initializeMaps()
	{
		HashMap<String,VeinMember[][][]> tempOne;
		HashMap<String,Boolean> tempTwo;
		addedPoints = new HashMap<String,VeinMember[][][]>();
		populatedList = new HashMap<String,Boolean>();
		tempOne = VeinChunkReadWrite.readPoints();
		tempTwo = VeinChunkReadWrite.readList();
		if(tempOne!=null)
		{
			addedPoints=tempOne;
		}
		if(tempTwo!=null)
		{
			populatedList=tempTwo;
		}
	}
	
	public static void saveMaps()
	{
		VeinChunkReadWrite.saveMaps(addedPoints,populatedList);
	}
	
 	public static void addToPopList(TwoPoint chunk)
	{
		populatedList.remove(chunk.toString());
		populatedList.put(chunk.toString(),false);
	}
	
	public static void mergeStrings(String key, VeinMember[][][] newOres) 
	{
		if(addedPoints.containsKey(key) || addedPoints.get(key)!=null)
		{
			VeinMember[][][] oldOres = addedPoints.get(key);
			for(int x= 0;x<16;x++)
			{
				for(int y = 1;y<131;y++)
				{
					for(int z = 0; z<16;z++)
					{
						if(oldOres[x][y][z]==null && newOres[x][y][z]!=null)
						{
							oldOres[x][y][z] = newOres[x][y][z];
						}
						else if(oldOres[x][y][z]!=null && newOres[x][y][z]!=null)
						{
							oldOres[x][y][z] = newOres[x][y][z];
						}
					}
				}
			}
			addedPoints.remove(key);
			addedPoints.put(key,  oldOres);
		}
		else
		{
			addedPoints.put(key, newOres);
		}
	}

//	//public static void addArrayToPoints(ArrayList<ThreePoint> thePoints, String blockType)
//	{
//		String key;
//		HashMap<String,String[][][]> tempHashMap = new HashMap<String,String[][][]>();
//		ThreePoint thePoint;
//		String[][][] Matrix;
//		for(int i =0;i<thePoints.size();i++)
//		{
//			key = thePoints.get(i).toChunkCoord();
//			if(thePoints.get(i).y<127 && thePoints.get(i).y > 1)
//			{
//				if(tempHashMap.containsKey(key))
//				{
//					Matrix = tempHashMap.get(key);
//					thePoint = thePoints.get(i);
//					thePoint.shiftCoords();
//					Matrix[thePoint.dx][thePoint.y][thePoint.dz] = blockType;
//					tempHashMap.put(key, Matrix);
//				}
//				else
//				{
//					thePoint = thePoints.get(i);
//					thePoint.shiftCoords();
//					Matrix = new String[16][128][16];
//					Matrix[thePoint.dx][thePoint.y][thePoint.dz] = blockType;
//					tempHashMap.put(key, Matrix);
//				}
//			}
//		}
//		for(String entry: tempHashMap.keySet())
//		{
//			mergeStrings(entry,tempHashMap.get(entry));
//		}
//	}
	
	public static void removePoints(TwoPoint chunk) 
	{
		addedPoints.remove(chunk.toString());
	}
	
	public static HashMap<String,VeinMember[][][]> getDrawListAndRemove() 
	{
		HashMap<String,VeinMember[][][]> drawList = new HashMap<String,VeinMember[][][]>();
		TwoPoint chunkCoord;
		HashMap<String,Boolean> tempMap = new HashMap<String,Boolean>();
		for(String entry : populatedList.keySet())//for all the removed ore chunks
		{
			chunkCoord = new TwoPoint(entry);
			if(!populatedList.get(entry))
			{
				removeOres(chunkCoord);
				tempMap.remove(entry);
				tempMap.put(entry, true);
			}
			if(addedPoints.containsKey(entry) && addedPoints.get(entry)!=null)//if there is data here
			{
				if(Bukkit.getServer().getWorld("world").isChunkLoaded(chunkCoord.x, chunkCoord.z));//if chunk is loaded
				{
					drawList.put(entry,addedPoints.get(entry));
					addedPoints.remove(entry);
				}
			}
		}
		for(String key : tempMap.keySet())
		{
			if(tempMap.get(key))
			{
				populatedList.remove(key);
				populatedList.put(key, true);
			}
		}
		return drawList;
	}
	
	public static Boolean popMapExists(String entry)
	{
		if(populatedList.containsKey(entry))
			return true;
		else
			return false;
	}
	
	public static Boolean getPop(String string) 
	{
		return populatedList.get(string);
	}

	private static void removeOres(TwoPoint chunk)
	{
		Chunk theChunk = Bukkit.getServer().getWorld("world").getChunkAt(chunk.x,chunk.z);
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 1; y < 131; y++)
			{
				for (int z = 0; z < 16; z++)
				{
					block = theChunk.getBlock(x, y, z);
					if (block.getType().compareTo(Material.COAL_ORE)==0 
							|| block.getType().compareTo(Material.IRON_ORE)==0 
							|| block.getType().compareTo(Material.GOLD_ORE)==0 
							|| block.getType().compareTo(Material.LAPIS_ORE)==0 
							|| block.getType().compareTo(Material.REDSTONE_ORE)==0 
							|| block.getType().compareTo(Material.DIAMOND_ORE)==0 
							|| block.getType().compareTo(Material.EMERALD_ORE)==0)
					{
						theChunk.getBlock(x, y, z).setType(Material.STONE);
					}
				}
			}
		}
		theChunk.getWorld().refreshChunk(chunk.x, chunk.z);
	}
}
