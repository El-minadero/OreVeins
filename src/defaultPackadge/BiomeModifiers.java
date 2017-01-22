package defaultPackadge;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Forest;
import defaultPackadgeHelpers.HighMountains;
import defaultPackadgeHelpers.Hills;
import defaultPackadgeHelpers.Mesa;
import defaultPackadgeHelpers.Mountains;
import defaultPackadgeHelpers.Ocean;
import defaultPackadgeHelpers.Plains;
import defaultPackadgeHelpers.Plateau;

	
public class BiomeModifiers 
{
	public HighMountains highMountains;
	public Mountains mountains;
	public Hills hills;
	public Mesa mesa;
	public Forest forest;
	public Plains plains;
	public Plateau plateau;
	public Ocean ocean;
	public String string;
	public BiomeModifiers(String ore) 
	{
		string = ore + ".BiomeModifiers";
		highMountains = new HighMountains(string);
		mountains = new Mountains(string);
		hills = new Hills(string);
		mesa = new Mesa(string);
		forest = new Forest(string);
		plains = new Plains(string);
		plateau = new Plateau(string);
		ocean = new Ocean(string);
	}
	public Double modifySpawn(String biome, double var)
	{
		String type = identifyBiome(biome);
		if(type.contains("HighMountains"))
		{
			var=var*highMountains.spawnModifier;
		}
		else if (type.contains("Mountains"))
		{
			var=var*mountains.spawnModifier;
		}
		else if(type.contains("Hills"))
		{
			var=var*hills.spawnModifier;
		}
		else if(type.contains("Mesa"))
		{
			var=var*mesa.spawnModifier;
		}
		else if(type.contains("Plateau"))
		{
			var=var*plateau.spawnModifier;
		}
		else if(type.contains("Forest"))
		{
			var=var*forest.spawnModifier;
		}
		else if(type.contains("Plains"))
		{
			var=var*plains.spawnModifier;
		}
		else if(type.contains("Ocean"))
		{
			var=var*ocean.spawnModifier;
		}
		return var;
	}
	public Double modifyGrade(String biome, double var)
	{
		String type = identifyBiome(biome);
		if(type.contains("HighMountains"))
		{
			var=var*highMountains.gradeModifier;
		}
		else if (type.contains("Mountains"))
		{
			var=var*mountains.gradeModifier;
		}
		else if(type.contains("Hills"))
		{
			var=var*hills.gradeModifier;
		}
		else if(type.contains("Mesa"))
		{
			var=var*mesa.gradeModifier;
		}
		else if(type.contains("Plateau"))
		{
			var=var*plateau.gradeModifier;
		}
		else if(type.contains("Forest"))
		{
			var=var*forest.gradeModifier;
		}
		else if(type.contains("Plains"))
		{
			var=var*plains.gradeModifier;
		}
		else if(type.contains("Ocean"))
		{
			var=var*ocean.gradeModifier;
		}
		return var;
	}
	public Double modifyStrike(String biome, double var)
	{
		String type = identifyBiome(biome);
		if(type.contains("HighMountains"))
		{
			var=var*highMountains.strikeModifier;
		}
		else if (type.contains("Mountains"))
		{
			var=var*mountains.strikeModifier;
		}
		else if(type.contains("Hills"))
		{
			var=var*hills.strikeModifier;
		}
		else if(type.contains("Mesa"))
		{
			var=var*mesa.strikeModifier;
		}
		else if(type.contains("Plateau"))
		{
			var=var*plateau.strikeModifier;
		}
		else if(type.contains("Forest"))
		{
			var=var*forest.strikeModifier;
		}
		else if(type.contains("Plains"))
		{
			var=var*plains.strikeModifier;
		}
		else if(type.contains("Ocean"))
		{
			var=var*ocean.strikeModifier;
		}
		return var;
	}
	
	private String identifyBiome(String string)
	{
		if(string.contains("EXTREME") || string.contains("PLUS"))
		{
			return "HighMountains";
		}
		else if (string.contains("MOUNTAINS") && !string.contains("PLATEAU"))
		{
			return "Mountains";
		}
		else if(string.contains("HILLS"))
		{
			return "Hills";
		}
		else if(string.contains("MESA"))
		{
			return "Mesa";
		}
		else if(string.contains("SAVANNA") && (string.contains("PLATEAU") || string.contains("MOUNTAINS")))
		{
			return "Plateau";
		}
		else if(string.contains("FOREST") || string.contains("TAIGA") || string.contains("BIRCH"))
		{
			return "Forest";
		}
		else if(string.contains("SAVANNA") || string.contains("SAVANNA")|| string.contains("DESERT") || string.contains("SWAMP"))
		{
			return "Plains";
		}
		else if(string.contains("OCEAN"))
		{
			return "Ocean";
		}
		else
		{
			return "Default";
		}
	}

	public void setDefaults(FileConfiguration config)
	{
		highMountains.logValues(config);
		mountains.logValues(config);
		hills.logValues(config);
		mesa.logValues(config);
		forest.logValues(config);
		plains.logValues(config);
		plateau.logValues(config);
		ocean.logValues(config);
	}
}
