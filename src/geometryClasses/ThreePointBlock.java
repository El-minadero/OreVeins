package geometryClasses;

import org.bukkit.Material;



public class ThreePointBlock extends ThreePoint
{
	public Material material;
	
	public ThreePointBlock(Material mat,int x,int y,int z)
	{
		this.x = x; this.y = y; this.z = z;
		this.material = mat;
	}
}
