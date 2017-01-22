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

import java.util.Random;

import net.minecraft.util.io.netty.util.internal.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.icloud.kevinmendoza.OreVeins.PointMapping;

import geometryClasses.Ellipsoid;
import geometryClasses.ThreePoint;

public class MetamorphicSystem extends OreSuper 
{
	public int width;
	public int height;
	public int depth;
	public int branch;
	public Random rand = new Random();
	public MetamorphicSystem(ThreePoint start, String biome)
	{
		this.material = Material.LAPIS_ORE;
		this.start = start;
		this.biome = biome;
		dip = 6;
		initializeDefaults();
		Ellipsoid theMetaArea = new Ellipsoid(width,height,depth);
		int size = theMetaArea.points.length;
		int iteration;
		ThreePoint tempPoint;
		for(int i=0;i<branch;i++)
		{
			iteration = rand.nextInt(size);
			tempPoint = new ThreePoint(theMetaArea.points[iteration]);
			tempPoint.offSet(start);
			addSection(tempPoint);
		}
		pushToMainPointMap(material, centers);
	}
	
	protected void initializeDefaults()
	{
		width = (int) lapiz.mineralizationwidth.getRVar();
		height = (int) lapiz.mineralizationlength.getRVar();
		depth = (int) lapiz.mineralizationdepth.getRVar();
		branch = (int) lapiz.branch.getRVar();
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		int a = (int) lapiz.lodedepth.getRVar();
		int b = (int) lapiz.lodelength.getRVar();
		int c =  (int) lapiz.lodewidth.getRVar();
		int grade = (int)(100/lapiz.modifiers.modifyGrade(biome, lapiz.grade.getRVar()));
		Ellipsoid soid = new Ellipsoid(a,b,c);
		soid.rotateRandom(ThreadLocalRandom.current());
		for(int i=0;i<soid.points.length;i++)
		{
			if(rand.nextInt(grade)==0)
			{
				ThreePoint offset = new ThreePoint(centerPoint);
				offset.offSet(soid.points[i]);
				this.centers.add(offset);
			}
		}
	}

	@Override
	protected void makeBonanza(ThreePoint center) {
		// TODO Auto-generated method stub
		
	}

}
