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

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.OreVeins;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import defaultPackadge.Default;

import geometryClasses.Paraboloid;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

public class VolcanicSystem extends OreSuper
{
	public TwoPoint change;
	public TwoPoint offset;
	public int grade;
	public int branch;
	public double branchfreq;
	public Random rand = new Random();
	
	public VolcanicSystem(ThreePoint start,String ore, String biome,int theheight)
	{
		start.y=2;
		dip = 7;
		this.material = Material.DIAMOND_ORE;
		this.biome = biome;
		initializeDefaults();
		grade = (int)Math.round(diamond.modifiers.modifyGrade(biome, (double)grade));
		int height = theheight;
		centers = new ArrayList<ThreePoint>();
		branchfreq = 1/(double)(branch);
		Paraboloid shape = new Paraboloid(change, offset,height);
		for(int i=0;i<shape.centers.size();i++)
		{
			ThreePoint point = new ThreePoint(shape.centers.get(i));
			point.offSet(start);
			addSection(point);
		}
		pushToMainPointMap(material, centers);
		
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		if(rand.nextInt(this.grade)==0)
		{
			this.centers.add(centerPoint);
		}
	}

	protected void initializeDefaults()
	{
		offset = new TwoPoint((int)(diamond.diatreme.offsetx.getRVar())
				, (int)(diamond.diatreme.offsety.getRVar()), false);
		change = new TwoPoint((int)(diamond.diatreme.changex.getRVar())
				, (int)(diamond.diatreme.changey.getRVar()), false);
		grade = (int)(100/(diamond.diatreme.grade.getRVar()));
		branch = (int)(diamond.veinDikes.branch.getRVar());
		grade = (int)Math.round(diamond.modifiers.modifyGrade(biome, (double)grade));
	}

	@Override
	protected void makeBonanza(ThreePoint center) 
{
		// TODO Auto-generated method stub
		
	}

}
