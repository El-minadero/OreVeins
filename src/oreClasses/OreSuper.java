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
package oreClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import mcListenersAndPopulators.CreateOreThread;
import mcListenersAndPopulators.IdentifyOres;
import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.OreVeins;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import defaultPackadge.BIF;
import defaultPackadge.Coal;
import defaultPackadge.Default;
import defaultPackadge.Diamond;
import defaultPackadge.Emerald;
import defaultPackadge.Gold;
import defaultPackadge.Iron;
import defaultPackadge.Lapiz;
import defaultPackadge.PrimaryVein;
import defaultPackadge.Redstone;
import defaultPackadge.SecondaryVein;
import defaultPackadge.TertiaryVein;
import defaultPackadgeHelpers.Bonanza;
import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.VeinSwitch;
import defaultPackadgeHelpers.Width;

import geometryClasses.Ellipse;
import geometryClasses.Line;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.VeinMember;

public abstract class OreSuper 
{
	protected String ore;
	protected ThreePoint start;//starting point of the vein
	protected ThreePoint end;
	protected ArrayList<ThreePoint> nodes= new ArrayList<ThreePoint>();;//branch points
	protected ArrayList<ThreePoint> centers = new ArrayList<ThreePoint>();//all the points that will be drawn by the vein
	protected ThreePoint[] crossSection;//cross section of the vein
	protected Coal coal = OreVeins.theDefaults.coal;
	protected BIF bif = OreVeins.theDefaults.bif;
	protected Iron iron = OreVeins.theDefaults.iron;
	protected Gold gold = OreVeins.theDefaults.gold;
	protected Redstone redstone = OreVeins.theDefaults.redstone;
	protected Lapiz lapiz = OreVeins.theDefaults.lapiz;
	protected Emerald emerald = OreVeins.theDefaults.emerald;
	protected Diamond diamond = OreVeins.theDefaults.diamond;
	protected String biome;
	protected Material material;
	protected byte dip;
	protected byte theStrike;
	protected abstract void initializeDefaults();
	
	protected abstract void  addSection(ThreePoint centerPoint);
	
	protected abstract void makeBonanza(ThreePoint center);
	
	protected void getActualDipStrike(ThreePoint start, ThreePoint end)
	{
		//strike
		if( start.x-end.x==0)
		{
			 theStrike = 0;
		}
		else
		{
			double temp = Math.atan2((double)(start.x-end.x), (double)(start.z-end.z));
			temp = temp/Math.PI;
			temp = Math.round(temp*4)/4;
			if(temp<0)
				temp = 8 - temp;
			theStrike = (byte)temp;
		}
		//dip
		if( start.y-end.y==0)
		{
			 dip = 3;
		}
		else if(ore.contains("BIF") || ore.contains("COAL"))
		{
			dip = 5;
		}
		else
		{
			double distance = Math.sqrt(
					Math.pow((start.x-end.x),2) + 
					Math.pow((start.y-end.y),2) + 
					Math.pow((start.z-end.z),2));
			double temp = Math.asin((start.y-end.y) / distance);
			temp = temp/Math.PI;
			temp = Math.round(temp*2)/2 +2;
			dip = (byte)temp;
		}
		
	}
	
	protected void addPoints(ArrayList<ThreePoint> iterateOverPoints)
	{
		this.centers = new ArrayList<ThreePoint>();
		for(int i=0;i<iterateOverPoints.size();i++)
		{
			addSection(iterateOverPoints.get(i));
		}
	}
	
	protected void pushToMainPointMap(Material mat, ArrayList<ThreePoint> centers)
	{
		String key;
		HashMap<String,VeinMember[][][]> tempHashMap = new HashMap<String,VeinMember[][][]>();
		ThreePoint thePoint;
		VeinMember[][][] tempArray;
		VeinMember addingMember;
		for(int i =0;i<centers.size();i++)
		{
			key = centers.get(i).toChunkCoord();
			if(centers.get(i).y<130 && centers.get(i).y > 1)
			{
				if(tempHashMap.containsKey(key))
				{
					tempArray = tempHashMap.get(key);
					thePoint = centers.get(i);
					thePoint.shiftCoords();
					addingMember = new VeinMember(mat, dip, theStrike);
					tempArray[thePoint.dx][thePoint.y][thePoint.dz] = addingMember;
					tempHashMap.put(key, tempArray);
				}
				else
				{
					tempArray = new VeinMember[16][132][16];
					thePoint = centers.get(i);
					thePoint.shiftCoords();
					addingMember = new VeinMember(mat, dip, theStrike);
					tempArray[thePoint.dx][thePoint.y][thePoint.dz] = addingMember;
					tempHashMap.put(key, tempArray);
				}
			}
		}
		BukkitTask mergeOres = new OreCombiner(tempHashMap).runTask(OreVeins.getPlugin(OreVeins.class));
	}
	
}
