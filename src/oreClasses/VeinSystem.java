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
package oreClasses;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import defaultPackadge.Default;
import defaultPackadge.PrimaryVein;
import defaultPackadge.SecondaryVein;
import defaultPackadge.TertiaryVein;
import defaultPackadgeHelpers.VeinSwitch;
import geometryClasses.Ellipse;
import geometryClasses.Ellipsoid;
import geometryClasses.Geode;
import geometryClasses.Line;
import geometryClasses.ThreePoint;

public class VeinSystem extends OreSuper 
{
	protected int grade;//frequency to number
	protected int strike;//number
	protected int levels;//number
	protected int bonanza;//frequency to number
	protected int branch;//frequency to number//number
	protected VeinSwitch veinSwitch;//frequency to number
	protected int width;//number
	protected int height;//number
	protected int type;// one for main, two for second, three for tert
	protected int iterationmax = 10;
	protected Ellipse section;
	protected ArrayList<ThreePoint> veinPoints;
	protected int currentIter;
	protected ArrayList<ThreePoint> air;
	public Random rand = new Random();
	
 	public VeinSystem(String ore, int type, ThreePoint start, int iteration, String biome)
	{
		currentIter = iteration;
		if(currentIter<6)
		{
			this.ore = ore;
			this.type = type;
			this.start = start;
			this.biome = biome;
			initializeDefaults();
			//DebugLogger.console(grade+":" +strike+":" +levels+":" +bonanza+":" +
			//branch+"."+ width+":" +height+":" +type);
			end = Line.getEndPoint(start, strike, this.rand, true);
			if(ore.contains("EMERALD"))
				air = new ArrayList<ThreePoint>();
			if(Line.distance(start,end) >= 1)
			{
				//DebugLogger.console("passed distance test");
				//branch prob is 
				nodes = new ArrayList<ThreePoint>();
				veinPoints = new ArrayList<ThreePoint>();
				centers = new ArrayList<ThreePoint>();	
				section = new Ellipse(width,height);
				section.alighnToPoints(start, end, rand);
				crossSection = section.points;
				veinPoints = Line.bezierCurve(start, end, rand);
				getActualDipStrike(start, end);
				if(branch>1)
					branch = veinPoints.size()/branch;
				addPoints(veinPoints);
				pushToMainPointMap(material, centers);
				if(this.air!=null && this.air.size()>0)
					pushToMainPointMap(Material.AIR, air);
				double newType;
				int branchType;
				//DebugLogger.console("branch number:"+nodes.size() + " iterationcurrent:"+currentIter+" veintype:"+ type + "centers"+centers.size());
				for(int i=0;i<nodes.size();i++)
				{
					newType = veinSwitch.getRVar();
					if(newType <1)
					{
						branchType = 1;
					}
					else if(newType <2)
					{
						branchType = 2;
					}
					else
					{
						branchType = 3;
					}
					VeinSystem branchVein = new VeinSystem(ore, branchType,nodes.get(i),currentIter+1,biome);
				}
			}
		}
	}

	protected void initializeDefaults()
	{
		//DebugLogger.console("initializing");
		if(ore.contains("IRON"))
		{
			this.material = Material.IRON_ORE;
			if(type==1)
			{
				assignPrimary(iron.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(iron.secondaryVein);
			}
			else
			{
				assignTertiary(iron.tertiaryVein);
			}
			grade = (int)Math.round(iron.modifiers.modifyGrade(biome, (double)grade));
			strike = (int)Math.round(iron.modifiers.modifyStrike(biome, (double)strike));
		}
		else if(ore.contains("GOLD"))
		{
			this.material = Material.GOLD_ORE;
			if(type==1)
			{
				assignPrimary(gold.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(gold.secondaryVein);
			}
			else
			{
				assignTertiary(gold.tertiaryVein);
			}
			grade = (int)Math.round(gold.modifiers.modifyGrade(biome, (double)grade));
			strike = (int)Math.round(gold.modifiers.modifyStrike(biome, (double)strike));
		}
		else if(ore.contains("REDSTONE"))
		{
			this.material = Material.REDSTONE_ORE;
			if(type==1)
			{
				assignPrimary(redstone.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(redstone.secondaryVein);
			}
			else
			{
				assignTertiary(redstone.tertiaryVein);
			}
			grade = (int)Math.round(redstone.modifiers.modifyGrade(biome, (double)grade));
			strike = (int)Math.round(redstone.modifiers.modifyStrike(biome, (double)strike));
		}
		else if(ore.contains("EMERALD"))
		{
			this.material = Material.EMERALD_ORE;
			if(type==1)
			{
				assignPrimary(emerald.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(emerald.secondaryVein);
			}
			else
			{
				assignTertiary(emerald.tertiaryVein);
			}
			grade = (int)Math.round(emerald.modifiers.modifyGrade(biome, (double)grade));
			strike = (int)Math.round(emerald.modifiers.modifyStrike(biome, (double)strike));
		}
		else if(ore.contains("DIAMOND"))
		{
			this.material = Material.DIAMOND_ORE;
			assignPrimary(diamond.veinDikes);
			grade = (int)Math.round(diamond.modifiers.modifyGrade(biome, (double)grade));
			strike = (int)Math.round(diamond.modifiers.modifyStrike(biome, (double)strike));
		}
		 section = new Ellipse(width,height);
	}
	
	protected void assignTertiary(TertiaryVein tertiaryVein) 
	{
		grade = (int)((100/tertiaryVein.grade.getRVar()));//frequency to number
		strike = (int)(((tertiaryVein.strike.getRVar())));//number
		bonanza= (int)(((100/tertiaryVein.bonanza.getRVar())));
		branch=(int)(((tertiaryVein.branch.getRVar())));
		veinSwitch=tertiaryVein.vswitch;   //?
		width = (int)(((tertiaryVein.width.getRVar())));
		height = (int)(((tertiaryVein.height.getRVar())));
	}

	protected void assignSecondary(SecondaryVein secondaryVein)
	{
		grade = (int)((100/secondaryVein.grade.getRVar()));//frequency to number
		strike = (int)((secondaryVein.strike.getRVar()));//number
		bonanza= (int)((100/secondaryVein.bonanza.getRVar()));
		branch=(int)((secondaryVein.branch.getRVar()));
		veinSwitch=secondaryVein.vswitch;   //?
		width = (int)((secondaryVein.width.getRVar()));
		height = (int)((secondaryVein.height.getRVar()));
		
	}

	protected void assignPrimary(PrimaryVein primaryVein) 
	{
		grade = (int)((100/primaryVein.grade.getRVar()));//frequency to number
		strike = (int)((primaryVein.strike.getRVar()));//number
		bonanza= (int)((100/primaryVein.bonanza.getRVar()));
		branch=(int)((primaryVein.branch.getRVar()));
		veinSwitch=primaryVein.vswitch;   //?
		width = (int)((primaryVein.width.getRVar()));
		height = (int)((primaryVein.height.getRVar()));
		
	}
	
	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		int breaking; int temp;
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		if(crossSection.length/grade>0)
		{
			for(int i =0;i<crossSection.length/grade;i++)
			{
				breaking=0;
				ThreePoint tempPoint = new ThreePoint(centerPoint);
				while(true)
				{
					temp = rand.nextInt(crossSection.length);
					if(!chosen.contains(temp))
					{
						chosen.add(temp);
						break;
					}
					if(breaking>crossSection.length*1.5)
						break;
					breaking++;
				}
				tempPoint.offSet(crossSection[temp]);
				if(tempPoint.y>2 && tempPoint.y<128)
					centers.add(tempPoint);
			}
			chosen = null;
		}
		else
		{
			for(int i =0;i<crossSection.length;i++)
			{
				if(rand.nextInt(grade)==0)
				{
					ThreePoint tempPoint = new ThreePoint(centerPoint);
					tempPoint.offSet(crossSection[i]);
					if(tempPoint.y>2 && tempPoint.y<128)
						centers.add(tempPoint);
				}
			}
		}
		if(branch>1)
		{
			if(rand.nextInt(branch)==0)
			{
				ThreePoint newPoint = new ThreePoint(centerPoint);
				newPoint.offSet(crossSection[rand.nextInt(crossSection.length)]);
				nodes.add(newPoint);
			}
		}
		if(rand.nextInt(bonanza)==0)
		{
			ThreePoint newPoint = new ThreePoint(centerPoint);
			newPoint.offSet(crossSection[rand.nextInt(crossSection.length)]);
			makeBonanza(newPoint);
		}
	}
	
	@Override
	protected void makeBonanza(ThreePoint center)
	{
		if(!ore.contains("EMERALD"))
		{
			Ellipsoid bon = new Ellipsoid(rand.nextInt(3)+1, rand.nextInt(3)+1, rand.nextInt(3)+1);
			bon.rotateRandom(rand);
			for(int i=0;i<bon.points.length;i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.points[i]);
				centers.add(newPoint);
			}
		}
		else
		{
			//DebugLogger.console("emerald geode at:"+ center.toString());
			Geode bon = new Geode(emerald.geode.depth.getRVar(),
										emerald.geode.height.getRVar(),
										emerald.geode.width.getRVar(),
										emerald.geode.thickness.getRVar());
			for(int i=0;i<bon.theShell.size();i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.theShell.get(i));
				centers.add(newPoint);
			}
			for(int i=0;i<bon.points.length;i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.points[i]);
				air.add(newPoint);
			}
		}
	}

}
