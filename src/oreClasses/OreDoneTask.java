package oreClasses;

import geometryClasses.ThreePoint;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.scheduler.BukkitRunnable;

import com.icloud.kevinmendoza.OreVeins.OreVeins;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public class OreDoneTask extends BukkitRunnable
{
	public OreDoneTask()
	{
	}
	@Override
	public void run() 
	{
		OreVeins.removeVeinCounter();
	}
	
}
