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
package fileIO;
//this is the IO write to file class
//for some reason, delete doesn't work :/
//annd yeah, i mean its pretty simple


import geometryClasses.VeinMember;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.HashMap;


import com.icloud.kevinmendoza.OreVeins.DebugLogger;

public class VeinChunkReadWrite 
{
	public static void delete(String path)
	{
		try
		{
			File file;
			file = new File(path);
			if(file.delete())
			{
				//DebugLogger.console(file.getName() + " is deleted!");
			}
			else
			{
				//DebugLogger.console("Delete operation is failed.");
			}
		}
		catch(Exception e)
		{
			DebugLogger.console("Exception Delete operation is failed.");
		}
	}
	
	public static HashMap<String,Boolean> readList()
	{
		try{
		FileInputStream fin;
		File popListFile = new File("plugins/OreVeins/popList.txt");
		popListFile.createNewFile();
		fin = new FileInputStream("plugins/OreVeins/popList.txt");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Object obj =  ois.readObject();
		ois.close();
		fin.close();
		HashMap<String, Boolean> theMap = new HashMap<String,Boolean>();
		
			if(theMap.getClass() == obj.getClass() )
			{
				try
				{
					HashMap<String, Boolean> popList = (HashMap<String, Boolean> ) obj;
					//DebugLogger.console("successful fetch!");
					delete("plugins/OreVeins/popList.txt");
					return popList;
				}
				catch (Exception e)
				{
					DebugLogger.console("ERROR!!1");
					return null;
				}

			}
			else
			{
				DebugLogger.console("ERROR!!2");
				return null;
			}
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	public static HashMap<String, VeinMember[][][]> readPoints()
	{
		try{
			FileInputStream fin;
			File popListFile = new File("plugins/OreVeins/pointList.txt");
			popListFile.createNewFile();
			fin = new FileInputStream("plugins/OreVeins/pointList.txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			HashMap<String,VeinMember[][][]> theMap = new HashMap<String,VeinMember[][][]>();
			
				if(theMap.getClass() == obj.getClass() )
				{
					try
					{
						HashMap<String,VeinMember[][][]> popList = (HashMap<String,VeinMember[][][]>) obj;
						//DebugLogger.console("successful fetch!");
						delete("plugins/OreVeins/pointList.txt");
						return popList;
					}
					catch (Exception e)
					{
						DebugLogger.console("ERROR!!1");
						return null;
					}

				}
				else
				{
					DebugLogger.console("ERROR!!2");
					return null;
				}
			}
			catch (Exception ex)
			{
				return null;
			}
	}
	
	public static void saveMaps(HashMap<String,VeinMember[][][]> points,HashMap<String,Boolean> popList)
	{
		delete("plugins/OreVeins/pointList.txt");
		delete("plugins/OreVeins/popList.txt");
		try 
		{
			FileOutputStream chunkdir;
			File veinFile = new File("plugins/OreVeins/pointList.txt");
			veinFile.createNewFile();
			chunkdir = new FileOutputStream("plugins/OreVeins/pointList.txt");
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(points);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("can't save dir, chunk key is missing");
		}
		
		try 
		{
			FileOutputStream chunkdir;
			File veinFile = new File("plugins/OreVeins/popList.txt");
			veinFile.createNewFile();
			chunkdir = new FileOutputStream("plugins/OreVeins/popList.txt");
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(popList);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("Couldn't save popList. Dir is missing");
		}
	}

	
}
