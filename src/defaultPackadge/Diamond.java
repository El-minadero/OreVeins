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

import defaultPackadgeHelpers.Diatreme;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Width;

public class Diamond 
{
	public double probToSpawn;
	public PrimaryVein veinDikes;
	public Diatreme diatreme;
	public BiomeModifiers modifiers;
	private String path = "DiamondVeinSystem";
	private int type =1;
	public Diamond()
	{
		probToSpawn = 0.15;
		veinDikes = new PrimaryVein(path);
		diatreme = new Diatreme(path);
		modifiers = new BiomeModifiers(path);
	}
	
	public int getType()
	{
		return type;
	}
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		modifiers.setDefaults(configs);
		diatreme.setDefaults(configs);
		veinDikes.setDefaults(configs);
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
