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



/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

public class TwoPoint extends Point
{
	public int x;
	public int z;
	private Boolean isChunk;
	public TwoPoint(int x, int z,Boolean ch)
	{
		this.x = x;
		this.z = z;
		this.isChunk = ch;
	}
	public TwoPoint(String key)
	{
		String delims = "[:]";
		String[] tokens = key.split(delims);
		this.x = Integer.parseInt(tokens[0]);
		this.z = Integer.parseInt(tokens[1]);
	}
	public String toString(){
        return x + ":" + z; //CSV format
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
			TwoPoint object = (TwoPoint)ob;
			if(object.x == this.x && object.z == this.z)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	@Override
	public String toChunkCoord() 
	{
		if(this.isChunk)
		{
			return x + ":" + z;
		}
		else
		{
			this.x = this.x>>4;
			this.z = this.z>>4;
			return this.x + ":" + this.z;
		}
		
	}
	@Override
	public TwoPoint toChunkCoordPoint() 
	{
		return this;
	}
}
