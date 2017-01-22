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

import defaultPackadgeHelpers.Bonanza;
import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.VeinSwitch;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.Width;

public class TertiaryVein {

	private double probToSpawn;
	public VeinSwitch vswitch;
	public Grade grade;
	public Strike strike;
	public Width width;
	public Height height;
	public Bonanza bonanza;
	public Branch branch;
	public String string;
	public TertiaryVein(String ore) 
	{
		string = ore + ".TertiaryVein";
		if(ore.contains("Gold"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Iron"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Redstone"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Emerald"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Diamond"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Lapiz"))
		{
			probToSpawn = 2.0;
		}
		grade        = new Grade(string);
		strike       = new Strike(string);
		width        = new Width(string);
		height       = new Height(string);
		bonanza      = new Bonanza(string);
		branch       = new Branch(string);;
		vswitch = new VeinSwitch(string);
	}

	public void setDefaults(FileConfiguration config)
	{
		setProbs(config);
		grade.logValues(config);
		strike.logValues(config);
		width.logValues(config);
		height.logValues(config);
		bonanza.logValues(config);
		branch.logValues(config);
		vswitch.logValues(config);
	}
	
	private void setProbs(FileConfiguration config)
	{
		if(!config.contains(string + ".SpawnProbability"))
		{
			config.set(string + ".SpawnProbability", probToSpawn);
		}
		else
		{
			probToSpawn = config.getDouble(string+".SpawnProbability");
		}
	}
	
	public double getProbToSpawn() 
	{
		Random rand = new Random();
		return rand.nextDouble()*this.probToSpawn;
	}
}
