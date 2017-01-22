package oreClasses;

import geometryClasses.ThreePoint;
import geometryClasses.VeinMember;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import com.icloud.kevinmendoza.OreVeins.OreVeins;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public class OreCombiner extends BukkitRunnable
{
	private HashMap<String,VeinMember[][][]> centers;
	public OreCombiner(HashMap<String, VeinMember[][][]> tempHashMap)
	{
		this.centers = tempHashMap;
	}
	@Override
	public void run() 
	{
		for(String entry: centers.keySet())
		{
			PointMapping.mergeStrings(entry,centers.get(entry));
		}
		
	}
	
}
