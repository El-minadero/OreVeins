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

import java.util.Random;




public class Shape

{
	
	public ThreePoint[] points;
	
	protected void makeEllipsoid(int a, int b, int c)
	{
		//bonanza's are ellipsoids
		
		int aa =a*a, bb=b*b,cc=c*c, i=1;
		int abc = aa*bb*cc;
		double XX,YY,ZZ;
		ThreePoint[] temp= new ThreePoint[((a+1)*(b+1)*(c+1)*8)+1];
		temp[0] = new ThreePoint(0,0,0);
		//DebugLogger.console("the dimensions are "+a+" "+b+ " "+c+ " "+" the size is then "+(a*b*c)*8);
		for(int x=0;x<=a;x++)
		{
			for(int y=0;y<=b;y++)
			{
				for(int z=0;z<=c;z++)
				{
					XX = x*x*bb*cc;
					YY = y*y*aa*cc;
					ZZ = z*z*aa*bb;
					if(abc >= XX+YY+ZZ)
					{
						if(x==0 && y==0 && z ==0)
						{
							
						}
						if(x==0 && y==0 && z!=0)
						{
							temp[i] = new ThreePoint(0,0,z);
							temp[i+1] = new ThreePoint(0,0,-z);
							i = i+2;

						}
						else if(x==0 & z==0 && y!=0)
						{
							temp[i] = new ThreePoint(0,y,0);
							temp[i+1] = new ThreePoint(0,-y,0);
							i = i+2;
						}
						else if(z==0 && y == 0 && x!=0)
						{
							temp[i] = new ThreePoint(x,0,0);
							temp[i+1] = new ThreePoint(-x,0,0);
							i = i+2;
						}
						else
						{
							temp[i] = new ThreePoint(x,y,z);
							temp[i+1] = new ThreePoint(x,y,-z);
							temp[i+2] = new ThreePoint(x,-y,z);
							temp[i+3] = new ThreePoint(x,-y,-z);
							temp[i+4] = new ThreePoint(-x,y,z);
							temp[i+5] = new ThreePoint(-x,y,-z);
							temp[i+6] = new ThreePoint(-x,-y,z);
							temp[i+7] = new ThreePoint(-x,-y,-z);
							i = i+8;
						}
					}
				}
			}
		}
	concantenate(temp);
	}
	protected void makeEllipse(int a, int b)
	{
		int i=1;
		int a2 = a *a;
	    int b2 = b * b;
	    int fa2 = 4 * a2, fb2 = 4 * b2;
	    int x0, y0, x, y, sigma, xtemp=1;
	    ThreePoint[] temp = new ThreePoint[4*(a+1)*(b+1)];
	    temp[0] = new ThreePoint(0,0,0);
	    /* first half, fill in by scanning up to y*/
	    for (x = 0, y = b, sigma = 2*b2+a2*(1-2*b); b2*x <= a2*y; x++)
	    {
	    	if(x==0)
	    	{
	    		for(y0=1;y0<=y;y0++)
	    		{
	    			temp[i]   = new ThreePoint(0,y0,0);
	    			temp[i+1] = new ThreePoint(0,-y0,0);
	    			i=i+2;
	    		}
	    	}
	    	else
	    	{
	    		for(y0=0;y0<=y;y0++)
	    		{
	    			temp[i]   = new ThreePoint(x,y0,0);
	    			temp[i+1] = new ThreePoint(x,-y0,0);
	    			temp[i+2] = new ThreePoint(-x,y0,0);
	    			temp[i+3] = new ThreePoint(-x,-y0,0);
	    			i=i+4;
	    		}
	    	}
	        if (sigma >= 0)
	        {
	            sigma += fa2 * (1 - y);
	            y--;
	        }
	        sigma += b2 * ((4 * x) + 6);
	        xtemp=x;
	    }
	    xtemp++;
	    /* second half, fill up by scanning from x. Start point is 1 more than the x value provided before */
	    for (x = a, y = 0, sigma = 2*a2+b2*(1-2*a); a2*y <= b2*x; y++)
	    {
	    	if(y==0)
	    	{
	    		for(x0=xtemp;x0<=x;x0++)
	    		{
	    			temp[i]   = new ThreePoint(x0,0,0);
	    			temp[i+1] = new ThreePoint(-x0,0,0);
	    			i=i+2;
	    		}
	    	}
	    	else
	    	{
	    		for(x0=xtemp;x0<=x;x0++)
	    		{
	    			temp[i]   = new ThreePoint(x0,y,0);
	    			temp[i+1] = new ThreePoint(x0,-y,0);
	    			temp[i+2] = new ThreePoint(-x0,y,0);
	    			temp[i+3] = new ThreePoint(-x0,-y,0);
	    			i=i+4;
	    		}
	    	}
	        if (sigma >= 0)
	        {
	            sigma += fb2 * (1 - x);
	            x--;
	        }
	        sigma += a2 * ((4 * y) + 6);
	    }
		concantenate(temp);
	}
	
	protected void concantenate(ThreePoint[] temp)
	{
		int count = 0;
		for(int i=0;i<temp.length;i++)
		{
			if(temp[i]!=null)
			{
				count++;
			}
		}
		this.points = new ThreePoint[count];
		count =0;
		for(int i=0;i<temp.length;i++)
		{
			if(temp[i]!=null)
			{
				this.points[count] = temp[i];
				count++;
			}
		}
	}
	
	public void rotateX(int theta)
	{
		double tau = Math.toRadians(theta);
		double yprime; double zprime;
		for(int i=0;i<this.points.length;i++)
		{
			if(this.points[i]!=null)
			{
				ThreePoint point = points[i];
				yprime = Math.round((double)(point.y)*Math.cos(tau)- (double)(point.z)*Math.sin(tau));
				zprime = Math.round((double)(point.y)*Math.sin(tau) + (double)(point.z)*Math.cos(tau));
				points[i].y = (int)yprime;
				points[i].z = (int)zprime;
				//DebugLogger.console("X the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

	public void rotateY(int theta)
	{
		double tau = Math.toRadians(theta);
		double xprime; double zprime;
		for(int i=0;i<points.length;i++)
		{
			if(points[i]!=null)
			{
				ThreePoint point = points[i];
				xprime = Math.round((double)(point.x)*Math.cos(tau)+ (double)(point.z)*Math.sin(tau));
				zprime = Math.round(-(double)(point.x)*Math.sin(tau) + (double)(point.z)*Math.cos(tau));
				points[i].x = (int)xprime;
				points[i].z = (int)zprime;
				//DebugLogger.console("Y the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

	public void rotateZ(int theta)
	{
		double tau = Math.toRadians(theta);
		double xprime; double yprime;
		for(int i=0;i<points.length;i++)
		{
			if(points[i]!=null)
			{
				ThreePoint point = points[i];
				xprime = Math.round((double)(point.x)*Math.cos(tau)- (double)(point.y)*Math.sin(tau));
				yprime = Math.round((double)(point.x)*Math.sin(tau) + (double)(point.y)*Math.cos(tau));
				points[i].x = (int)xprime;
				points[i].y = (int)yprime;
				//DebugLogger.console("Z the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

	public void rotateRandom(Random rand) 
	{
		rotateY(rand.nextInt(180)-90);
		rotateX(rand.nextInt(180)-90);
		rotateZ(rand.nextInt(180)-90);
	}

	public void alighnToPoints(ThreePoint start, ThreePoint end, Random rand)
	{
		double vx = end.x - start.x;
		double vy = end.y - start.y;
		double vz = end.z - start.z;
		rotateZ(rand.nextInt(180)-90);
		double r = Math.sqrt( vx*vx + vy*vy + vz*vz);
		double dotProd = vz/r;
		if(dotProd < .95)
		{
			double cvx, cvy;
			cvx = vy/r;
			cvy = -vx/r;
			double h = (1-dotProd) / (1-(dotProd*dotProd));
			double a1 = dotProd +h*cvx*cvx;
			double a2 = h*cvy*cvx;
			double a3 = cvy;
			double b1 = h*cvy*cvx;
			double b2 = dotProd + h*cvy*cvy;
			double b3 = -cvx;
			double c1 = -cvy;
			double c2 = cvx;
			double c3 = dotProd;
			int xrot, yrot, zrot;
			for(int i=0;i<this.points.length;i++)
			{
				ThreePoint point = this.points[i];
				xrot = (int) Math.round((point.x*a1 + point.y*a2 + point.z*a3));
				yrot = (int) Math.round((point.x*b1 + point.y*b2 + point.z*b3));
				zrot = (int) Math.round((point.x*c1 + point.y*c2 + point.z*c3));
				this.points[i].x = xrot;
				this.points[i].y = yrot;
				this.points[i].z = zrot;
			}
		}
	}
	public void alighnToPoints(ThreePoint start, ThreePoint end)
	{
		double vx = end.x - start.x;
		double vy = end.y - start.y;
		double vz = end.z - start.z;
		double r = Math.sqrt( vx*vx + vy*vy + vz*vz);
		double dotProd = vz/r;
		if(dotProd < .95)
		{
			double cvx, cvy;
			cvx = vy/r;
			cvy = -vx/r;
			double h = (1-dotProd) / (1-(dotProd*dotProd));
			double a1 = dotProd +h*cvx*cvx;
			double a2 = h*cvy*cvx;
			double a3 = cvy;
			double b1 = h*cvy*cvx;
			double b2 = dotProd + h*cvy*cvy;
			double b3 = -cvx;
			double c1 = -cvy;
			double c2 = cvx;
			double c3 = dotProd;
			int xrot, yrot, zrot;
			for(int i=0;i<this.points.length;i++)
			{
				ThreePoint point = this.points[i];
				xrot = (int) Math.round((point.x*a1 + point.y*a2 + point.z*a3));
				yrot = (int) Math.round((point.x*b1 + point.y*b2 + point.z*b3));
				zrot = (int) Math.round((point.x*c1 + point.y*c2 + point.z*c3));
				this.points[i].x = xrot;
				this.points[i].y = yrot;
				this.points[i].z = zrot;
			}
		}
	}
}
