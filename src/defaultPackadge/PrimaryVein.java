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

//primary vein contains information related to the primary vein,
//grade, branch, strike, bonanza, and 
public class PrimaryVein 
{
	public VeinSwitch vswitch;
	public Grade grade;
	public Strike strike;
	public Width width;
	public Height height;
	public Bonanza bonanza;
	public Branch branch;
	public String string;
	public PrimaryVein(String ore) 
	{
		string = ore + ".PrimaryVein";
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
		grade.logValues(config);
		strike.logValues(config);
		width.logValues(config);
		height.logValues(config);
		bonanza.logValues(config);
		branch.logValues(config);
		vswitch.logValues(config);
	}
}
