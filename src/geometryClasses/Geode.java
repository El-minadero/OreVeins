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
package geometryClasses;

import java.util.ArrayList;
import java.util.Random;

public class Geode extends Shape
{
	public ArrayList<ThreePoint> theShell;
	public Geode(double a, double b, double c, double h)
	{
		int d = (int)(a);
		int e = (int) (b);
		int f = (int) (c);
		int g = (int)(h);
		if(d<e && d<f)
		{
			if(d<g)
				g=1;
		}
		else if(e<d && e<f)
		{
			if(e<g)
				g=1;
		}
		else
		{
			if(f<g)
				g=1;
		}
		int dx = d-g;
		int dy = e-g;
		int dz = f-g;
		
		Ellipsoid shell = new Ellipsoid(d+1,e+1,f+1);
		Ellipsoid pocket = new Ellipsoid(dx,dy,dz);
		Random rand = new Random();
		int x = rand.nextInt(360);
		int y = rand.nextInt(360);
		int z = rand.nextInt(360);
		shell.rotateX(x);shell.rotateY(y); shell.rotateZ(z);
		pocket.rotateX(x);shell.rotateY(y); shell.rotateZ(z);
		theShell = new ArrayList<ThreePoint>();
		createPointList(shell,pocket);
		this.points = pocket.points;
	}
	
	private void createPointList(Ellipsoid shell, Ellipsoid pocket) 
	{
		Boolean bool = true;
		for(int i=0;i<shell.points.length;i++)
		{
			for(int j=0;j<pocket.points.length;j++)
			{
				if(shell.points[i].equals(pocket.points[j]))
				{
					bool = false;
				}
			}
			if(bool)
			{
				this.theShell.add(shell.points[i]);
			}
			else
			{
				bool = true;
			}
		}
	}
}
