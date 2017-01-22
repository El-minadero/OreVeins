package mcListenersAndPopulators;
import java.util.Random;

import geometryClasses.ThreePoint;

import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;
import oreClasses.MetamorphicSystem;
import oreClasses.OreDoneTask;
import oreClasses.SedimentHostedDepositSystem;
import oreClasses.VeinSystem;
import oreClasses.VolcanicSystem;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.icloud.kevinmendoza.OreVeins.OreVeins;

import defaultPackadge.Default;

public class CreateOreThread extends BukkitRunnable 
{
	private final String ore;
	private final String biome;
	private final ThreePoint start;
	private ThreadLocalRandom rand;
	private final int height;
	public CreateOreThread(ThreePoint start, String ore, String biome, int height)
	{
		this.start =start;
		this.ore = ore;
		this.biome = biome;
		this.height = height;
	}
	@Override
	public void run() 
	{
		if(ore.contains("iron"))
		{
			int branchType=1;
			double value = OreVeins.theDefaults.iron.chooseType.getRVar();
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("IRON",branchType, start, 0,biome);
		}
		else if(ore.contains("coal"))
		{
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"COAL",biome);
		}
		else if(ore.contains("bif"))
		{
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"BIF",biome);
		}
		else if(ore.contains("redstone"))
		{
			int branchType=1;
			double value = OreVeins.theDefaults.redstone.chooseType.getRVar();
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("REDSTONE",branchType, start, 0,biome);
		}
		else if(ore.contains("gold"))
		{
			int branchType=1;
			double value = OreVeins.theDefaults.gold.chooseType.getRVar();
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("GOLD",branchType, start, 0,biome);
		}
		else if(ore.contains("emerald"))
		{
			int branchType=1;
			double value = OreVeins.theDefaults.emerald.chooseType.getRVar();
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem emerald = new VeinSystem("EMERALD",branchType, start, 0,biome);
		}
		else if(ore.contains("lapiz"))
		{
			MetamorphicSystem system = new MetamorphicSystem(start,biome);
		}
		else if(ore.contains("diamond"))
		{
			VolcanicSystem pipe = new VolcanicSystem(start,"DIAMOND", biome,height);
		}
			BukkitTask removeCounter = new OreDoneTask().runTaskLater(OreVeins.getPlugin(OreVeins.class),15);
	}

}
