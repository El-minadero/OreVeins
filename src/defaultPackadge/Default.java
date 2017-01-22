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


import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;




public class Default 
{
	public Lapiz lapiz;
	public BIF bif;
	public Coal coal;
	public Iron iron;
	public Gold gold;
	public Redstone redstone;
	public Emerald emerald;
	public Diamond diamond;
	
	public Default(FileConfiguration config) 
	{
		FileConfigurationOptions options = config.options();
		String header = new String();
		header = "!!!!!!!!!!!!!OreVeins realistic ore distribution plugin!!!!!!!!!\nCopyright (C) 2014  Kevin Mendoza\nkevinmendoza@mac.com\nMajor Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao\n\nThis program is free software; you can redistribute it and/or\nmodify it under the terms of the GNU General Public License\nas published by the Free Software Foundation; either version 2\nof the License, or any later version.\n\nThis program is distributed in the hope that it will be useful,\nbut WITHOUT ANY WARRANTY; without even the implied warranty of\nMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\nGNU General Public License for more details.\n \nYou should have received a copy of the GNU General Public License\nalong with this program; if not, write to the Free Software\nFoundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.\n\nA quick summary of how this config file and the ores themselves work:\n\nIn order to somewhat accurately represent how various ores form in nature, it is \nnecessary to associate each ‘ore’ with a type of geometrical system.\n****** Note, be careful!*************************\nEditing any of these values. changing them slightly may cause the server to become \nunstable, or do unexpected things with the ore distribution.  \n********************************************************\nI have decided that in the name of customizability to include two types of settings:\n*percent based values\n*normally distributed values.\n	percent based values are things like ‘SpawnProbability’. They represent what \npercentage of chunks this thing spawns in or occurs in. So if a value is listed as \n0.1, then this represents 0.1%, or 1 event per 1000 chunks.\n	normally distributed values make up the majority of editable things here. \nThey consist of a truncated normal distribution controlled by a skew factor. In order\nFor this plugin not to crash, they must have a min value, a max value, a skew value, \nand a bias value. Min and max value are self evident: they control the range of \npossible random values you can expect that particular parameter to have. Skew controls\nhow ‘pointy’ the pseudogaussian variable is weighted. 1.0 is fairly even, and 2.0 gets\nfairly spiky in my experience. Bias controls how much you’ll find the random variable \nremoved from the center of the range. Anything less than -0.5 is skewed quite strongly\ntowards the min, and 0.5 is quite strongly to the max. The exact function used for \nyou ‘geeks’ ;) is the following:\n\n  range = max - min;\n  mid = min + ( range / 2.0);\n  unitGaussian = rand.nextGaussian();\n  biasFactor = Math.exp(bias);\n  retval = \n  mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));\n\n	Redstone, Iron, Gold, and Emerald:\n\nThese ores form in ‘veins’, linear features representing deposition inside a fault or \nfracture. Each vein System has three types of veins, and each vein has the following attributes:\nGrade: % of chunks within the vein actually replaced by gold blocks\nStrike:  how long the vein is\nWidth: how wide the vein cross section is\nHeight: how high the vein cross section is\nBonanza: how often in % of chunks a ‘bonanza’ of ore occurs. These bonanzas are\nspherical nodules containing 100% ore\nBranch: how often the vein will split into other veins (Be careful with this one)\nProbability of switching: if the values are less than 1, it will make a primary vein when\nit branches. if its between 1 and 2 it will make a secondary vein upon branch. if its \nbetween 2 and 3 it will make a tertiary vein when it branches. This also applies for\nthe initial vein type. \n\n	Emeralds have additional configurable settings:\n\nEmerald systems (not in real life) have hollow 3D spherical cavities. In game they are\ncustomized by the following\nGeode Width: the x width of a geode\nGeode Height: the y height of a geode\nGeode depth: the z depth of a geode\nGeode thickness: how much of a geode is actually ore.\n\n	Lapiz Lazuli \n\nThis ore forms as rotated spheres within a large spherical region, and has the following \nproperties\nGrade: % of blocks that are actually ores\nBranch: # of alteration ‘halos’ within the larger metasomatic zone\nZone width, height, & depth: the area (in blocks) of the region in which alteration\n‘halos’ can form.\nlode width, height, & depth: the actual area of specific halos.\n\n	Coal & Banded Iron Formations\n\nIn an effort to recreate sedimentary deposition and subsequent folding of layers,\ncoal and iron can form in large planar sheets. Sheets may be stacked and can be highly folded and quite large. The following settings apply:\nGrade: % of blocks that consist of ore\nBranchLevels: how many parallel planes within one structure can exist\nStrike: how long the bed can strech\nWidth: how wide the bed can stretch\nHeight: how high the bed can stretch\n\n	Diamond Diatremes (volcanic pipes)\n\nIn the wild, diamonds are found commercially inside the remnants of ancient volcanic necks.\nIn this game, the pipes themselves are created by superpositioning a hyperbolic cone on\ntop of a cylinder. To control this structure, the following variables are used:\nGrade: % of blocks that are actually diamond\nMinWidth: the minimum cross section x direction at depth of the pipe\nMinLength: the minimum cross section z direction at depth of the pipe\nChangingWidth: how much the pipe flares at the surface, x direction\nChangingLength: how much the pipe flares at the surface, z direction";
		options.header(header);
		iron = new Iron();
		iron.readWriteConfigs(config);
		coal = new Coal();
		coal.setDefaults(config);
		bif = new BIF();
		bif.setDefaults(config);
		redstone = new Redstone();
		redstone.readWriteConfigs(config);
		gold = new Gold();
		gold.readWriteConfigs(config);
		lapiz = new Lapiz();
		lapiz.setDefaults(config);
		emerald = new Emerald();
		emerald.readWriteConfigs(config);
		diamond = new Diamond();
		diamond.readWriteConfigs(config);
	}
	
}