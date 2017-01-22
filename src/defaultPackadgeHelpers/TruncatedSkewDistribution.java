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

import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;

import org.bukkit.configuration.file.FileConfiguration;

public class TruncatedSkewDistribution 
{
	public double min;
	public double max;
	public  double skew;
	public double bias;
	public String configPath;
	
	public void logValues(FileConfiguration config) 
	{
	
		if(!config.contains(configPath + ".Min"))
		{
			config.set(configPath + ".Min", min);
		}
		else
		{
			min = config.getDouble(configPath+".Min");
		}
		if(!config.contains(configPath + ".Max"))
		{
			config.set(configPath + ".Max", max);
		}
		else
		{
			max = config.getDouble(configPath+".Max");
		}
		if(!config.contains(this.configPath + ".Skew"))
		{
			config.set(configPath + ".Skew", skew);
		}
		else
		{
			skew = config.getDouble(configPath + ".Skew");
		}
		if(!config.contains(configPath + ".Bias"))
		{
			config.set(configPath + ".Bias", bias);
		}
		else
		{
			bias = config.getDouble(configPath +".Bias");
		}
	}
	
	public double getRVar()
	{	
	        double range = max - min;
	        double mid = min + ( range / 2.0);
	        double unitGaussian = ThreadLocalRandom.current().nextGaussian();
	        double biasFactor = Math.exp(bias);
	        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
	        return retval;
	}
	
}
