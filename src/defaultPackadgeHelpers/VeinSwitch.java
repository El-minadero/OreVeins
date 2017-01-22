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

import java.util.Random;

public class VeinSwitch extends TruncatedSkewDistribution 
{


	public VeinSwitch(String path) 
	{
		this.configPath = path + ".ProbabilityOfVeinSwitching";
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
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
	}
}
