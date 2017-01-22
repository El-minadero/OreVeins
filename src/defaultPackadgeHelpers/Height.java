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
package defaultPackadgeHelpers;


public class Height extends TruncatedSkewDistribution {

	public Height(String path) 
	{
		this.configPath = path + ".Height";
		if(path.contains("PrimaryVein"))
		{
			primary(path);
		}
		else if(path.contains("SecondaryVein"))
		{
			secondary(path);
		}
		else if(path.contains("TertiaryVein"))
		{
			tertiary(path);
		}
		else
		{
			theDefault(path);
		}
	}
	
	private void primary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 3;
			this.max =6;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 3;
			this.max =6;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 3;
			this.max =6;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 3;
			this.max =6;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 3;
			this.max =6;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 2;
			this.max =5;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 2;
			this.max =5;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 2;
			this.max =5;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 2;
			this.max =5;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 2;
			this.max =5;
			this.skew = 1.0;
			this.bias = -0.5;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 1;
			this.max =3;
			this.skew = 1.0;
			this.bias = -0.5;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("BandedIronFormation"))
		{
			this.min = 25.0;
			this.max =100.0;
			this.skew = 2.0;
			this.bias = -0.75;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 20.0;
			this.max = 100.0;
			this.skew = 3.0;
			this.bias = 3.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.6;
			this.bias = 0.2;
		}
	}
}