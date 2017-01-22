package mcListenersAndPopulators;

import java.util.ArrayList;
import geometryClasses.ThreePoint;
import geometryClasses.ThreePointBlock;
import geometryClasses.TwoPoint;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;

public class SetOres extends BukkitRunnable
{

	private Chunk chunk;
	private TwoPoint coords;
	private ArrayList<ThreePointBlock> blocks;
	public SetOres(TwoPoint coords, ArrayList<ThreePointBlock> blocks) 
	{
		this.coords = coords;// TODO Auto-generated method stub
		this.blocks = blocks;
	}
	@Override
	public void run()
	{
		chunk = Bukkit.getServer().getWorld("world").getChunkAt(coords.x, coords.z);
		Block block;
		ThreePoint point;
		for(int i = 0;i<blocks.size(); i++)
		{
			if(blocks.get(i)!=null)
			{
				point = new ThreePoint(blocks.get(i).x, blocks.get(i).y, blocks.get(i).z);
				block = chunk.getBlock(point.x, point.y, point.z);
				if(isType(block.getType(),Material.STONE)  || isType(block.getType(),Material.GRASS) ||
				   isType(block.getType(),Material.GRAVEL) || isType(block.getType(),Material.DIRT)  ||
				   isType(block.getType(),Material.CLAY)   || isType(block.getType(),Material.SAND)  ||
				   isType(block.getType(),Material.SANDSTONE))
				{
					//DebugLogger.console(blocks.get(i).material.toString());
					chunk.getBlock(point.x, point.y, point.z).setType(blocks.get(i).material);
				}
			}
		}
		chunk.getWorld().refreshChunk(coords.x, coords.z);
	}

	private boolean isType(Material mat, Material second)
	{
		if(mat.compareTo(second)==0)
			return true;
		else
			return false;
	}

}
