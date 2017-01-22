package mcListenersAndPopulators;

import geometryClasses.TwoPoint;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public class RemoveOreTask extends BukkitRunnable
{
	private final TwoPoint chunk;
	
	public RemoveOreTask(Chunk thechunk) 
	{
		this.chunk = new TwoPoint(thechunk.getX(),thechunk.getZ(),true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() 
	{
		removeOres(chunk);
		//DebugLogger.console("removed ores at "+ theChunk.toString());
		PointMapping.addToPopList(chunk);// TODO Auto-generated method stub
	}
	
	private void removeOres(TwoPoint chunk)
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
