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
package geometryClasses;

import java.util.Random;

/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

public class ThreePoint extends Point
{
	public int x;
	public int dx;
	public int dz;
	public int y;
	public int z;
	public ThreePoint(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public ThreePoint(ThreePoint other){
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}
	public ThreePoint()
	{
		Random rand = new Random();
		this.x =rand.nextInt(16);
		this.y =rand.nextInt(125)+2;
		this.z =rand.nextInt(16);
	}
	public void shiftCoords()
	{
		this.dx = (x%16+16)%16;
		this.dz = (z%16+16)%16;
	}
	public void offSet(ThreePoint point)
	{
		this.x+=point.x;
		this.y+=point.y;
		this.z+=point.z;
	}
	@Override
	public String toString()
	{
		return x +","+ y +","+ z;
	}

	@Override
	public TwoPoint toChunkCoordPoint() 
	{
		TwoPoint chunkC = new TwoPoint(x>>4,z>>4,true);
		return chunkC;
	}
	@Override
	public String toChunkCoord()
	{
		return (x>>4) + ":" + (z>>4); 
	}
	@Override	
	public boolean equals(Object ob)
	{
		if(ob == null || ob.getClass() != this.getClass())
		{
			return false;
		}
		else
		{
			ThreePoint obj = (ThreePoint)ob;
			if(obj.x == this.x && obj.y == this.y && obj.z == this.z)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
