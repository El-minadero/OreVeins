package mcListenersAndPopulators;

import geometryClasses.TwoPoint;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

public abstract class OreRemover 
{

	
	protected void removeOres(TwoPoint chunk)
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
	}
}
