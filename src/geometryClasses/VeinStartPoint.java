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

import java.io.Serializable;


public class VeinStartPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6572732891233298111L;
	public ThreePoint start;
	public String ore;
	public int grade;
	public int branch;
	public int cross;
	public VeinStartPoint(ThreePoint threePoint2, String ore2, int grade2,
			int branch2, int cross2, int bonanza2) 
	{
		this.start = threePoint2;
		this.ore =ore2;
		this.grade = grade2;
		this.branch = branch2;
		this.cross = cross2;
		if(this.grade <5)
		{
			this.grade = 30;
		}
		if(this.branch <20)
		{
			this.branch = 70;
		}
		if(this.cross > 50)
		{
			this.cross = 10;
		}			
	}
}
