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


public class Grade extends TruncatedSkewDistribution 
{
	public Grade(String path)
	{
		this.configPath = path + ".Grade";
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
			this.min = 1.0;
			this.max = 4.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 4.5;
			this.max = 15.0;
			this.skew = 2.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 1.0;
			this.max = 5.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1.0;
			this.max = 3.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 1.0;
			this.max = 3.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 3.0;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 1.0;
			this.max = 5.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1.0;
			this.max = 5.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.75;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.5;
			this.max = 30.0;
			this.skew = 1.0;
			this.bias = -1.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 5.0;
			this.max = 25.0;
			this.skew = 2.0;
			this.bias = -1.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 3.0;
			this.max = 15.0;
			this.skew = 2.0;
			this.bias = -1.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 3.0;
			this.max = 30.0;
			this.skew = 2.0;
			this.bias = -1.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.04;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("BandedIronFormation"))
		{
			this.min = 3.0;
			this.max =12.0;
			this.skew = 1.0;
			this.bias = 0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = -0.8;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 5.0;
			this.max = 15.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.0;
			this.max = 5.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
}
