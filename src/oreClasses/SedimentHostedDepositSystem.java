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

import geometryClasses.Ellipse;
import geometryClasses.Line;
import geometryClasses.ThreePoint;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public class SedimentHostedDepositSystem extends OreSuper 
{
	private int strike;
	private int width;
	private int height;
	private int grade;
	private int levels;
	public Random rand = new Random();
	private ArrayList<ThreePoint> levelOffset;
	
	public SedimentHostedDepositSystem(ThreePoint start, String ore, String biome)
	{
		this.ore = ore;
		dip = 5;
		if(ore.contains("coal"))
		{
			this.material = Material.COAL_ORE;
		}
		else
		{
			this.material = Material.IRON_ORE;
		}
		this.start = start;
		this.biome = biome;
		initializeDefaults();
		getPerpVector();
		if(Line.distance(start, end) > 2)
		{
			this.end = Line.getEndPoint(start, strike, rand, false);
			getActualDipStrike(start, end);
			makeSystem();
		}
	}
	
	private void getPerpVector()
	{
		//returned a point that is perpendicular to the current point. offset both startpoint and endpoint
		//by start
		ThreePoint base = new ThreePoint(0,0,0);
		end = Line.getEndPoint(base, strike*2, rand,false);
		levelOffset = Line.bresenHamAlgo(base,end);
		if(end.x==0)
			end.x =1;
		if(end.z==0)
			end.z=1;
		if(end.y==0)
			end.y=1;
		levelOffset = Line.bresenHamAlgo(base,end);
	}
	
	private void makeSystem()
	{
		nodes = Line.bezierCurve(start, end, rand);
		int max = levelOffset.size();
		ThreePoint offset;
		Ellipse newEllipse = new Ellipse(height,width);
		newEllipse.rotateRandom(rand);
		newEllipse.alighnToPoints(start,end);
		crossSection = newEllipse.points;
		//DebugLogger.console(":"+levels+ "max:"+max +"strike:"+ strike);
		for(int randindex=0;randindex<max;randindex+=max/levels)
		{
			offset = levelOffset.get(randindex);
			//DebugLogger.console(offset.toString());
			addSection(offset);
		}
		pushToMainPointMap(material, this.centers);
	}
	
	protected void initializeDefaults()
	{
		if(ore.contains("BIF"))
		{
			strike = (int)Math.round(bif.modifiers.modifyStrike(biome, bif.strike.getRVar()));
			width = (int) bif.width.getRVar();
			height = (int) bif.height.getRVar();
			grade = (int) (100/(bif.modifiers.modifyGrade(biome,bif.grade.getRVar())));
			levels = (int) bif.levels.getRVar();
		}
		else if(ore.contains("COAL"))
		{
			strike = (int)Math.round(coal.modifiers.modifyStrike(biome, coal.strike.getRVar()));
			width = (int) coal.width.getRVar();
			height = (int) coal.height.getRVar();
			grade = (int) (100/(coal.modifiers.modifyGrade(biome,coal.grade.getRVar())));
			levels = (int) coal.levels.getRVar();
		}
		if(strike<50 && levels > 3)
		{
			levels =3;
		}
		if(strike>75 && levels > 5)
			levels=4;
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		ThreePoint tempPoint;
		int length = crossSection.length;
		int temp;
		int breaking;
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		for(int i=0;i<nodes.size();i++)
		{
			//DebugLogger.console(nodes.get(i).toString());
			for(int j=0;j<length/grade;j++)
			{
				breaking=0;
				tempPoint = new ThreePoint(centerPoint);
				while(true)
				{
					temp = rand.nextInt(length);
					if(!chosen.contains(temp))
					{
						chosen.add(temp);
						break;
					}
					if(breaking>length*1.5)
						break;
					breaking++;
				}
				tempPoint.offSet(crossSection[temp]);
				tempPoint.offSet(nodes.get(i));
				if(tempPoint.y>2 && tempPoint.y<128)
					centers.add(tempPoint);
			}
			chosen.clear();
		}
	}

	@Override
	protected void makeBonanza(ThreePoint center) {
		// TODO Auto-generated method stub
		
	}


}
