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
package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.TruncatedSkewDistribution;
import defaultPackadgeHelpers.VeinSwitch;


public class Redstone
{
	public PrimaryVein primaryVein;
	public SecondaryVein secondaryVein;
	public TertiaryVein tertiaryVein;
	public double probToSpawn;
	public String path = "RedstoneVeinSystem";
	public VeinSwitch chooseType;
	public BiomeModifiers modifiers;
	public Redstone()
	{
		probToSpawn = 0.9;
		modifiers = new BiomeModifiers(path);
		primaryVein = new PrimaryVein(path);
		secondaryVein = new SecondaryVein(path);
		tertiaryVein = new TertiaryVein(path);
		chooseType = new VeinSwitch("RedstoneVeinSystem.VeinTypeInitial");
	}
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		modifiers.setDefaults(configs);
		chooseType.logValues(configs);
		primaryVein.setDefaults(configs);
		secondaryVein.setDefaults(configs);
		tertiaryVein.setDefaults(configs);
	}
	private void setProbs(FileConfiguration config)
	{
		if(!config.contains(path + ".SpawnProbability"))
		{
			config.set(path + ".SpawnProbability", probToSpawn);
		}
		else
		{
			probToSpawn = config.getDouble(path+".SpawnProbability");
		}
		
	}

}
