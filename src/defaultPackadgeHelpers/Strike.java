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


public class Strike extends TruncatedSkewDistribution 
{
	public Strike(String path)
	{
		this.configPath = path +".Strike";
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
			this.min = 40;
			this.max = 200;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 40;
			this.max = 200;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 40;
			this.max = 200;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 40;
			this.max = 200;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 4;
			this.max = 30;
			this.skew = 2.0;
			this.bias = 1.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 20;
			this.max = 70;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 20;
			this.max = 70;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 20;
			this.max = 70;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 20;
			this.max = 70;
			this.skew = 2.0;
			this.bias = 1.0;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 10;
			this.max = 40;
			this.skew = 2.0;
			this.bias = 1.0;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("BandedIronFormation"))
		{
			this.min = 50;
			this.max =300;
			this.skew = 1.0;
			this.bias = -1;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 30.0;
			this.max = 300.0;
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
