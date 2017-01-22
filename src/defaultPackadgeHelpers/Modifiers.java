package defaultPackadgeHelpers;

import org.bukkit.configuration.file.FileConfiguration;

public class Modifiers 
{
	public Double spawnModifier;
	public Double gradeModifier;
	public Double strikeModifier;
	public String configPath;
	
	public void logValues(FileConfiguration config) 
	{

		if(!config.contains(configPath + ".SpawnModifier"))
		{
			config.set(configPath + ".SpawnModifier", spawnModifier);
		}
		else
		{
			spawnModifier = config.getDouble(configPath+".SpawnModifier");
		}
		if(!config.contains(configPath + ".GradeModifier"))
		{
			config.set(configPath + ".GradeModifier", gradeModifier);
		}
		else
		{
			gradeModifier = config.getDouble(configPath+".GradeModifier");
		}
		if(!config.contains(this.configPath + ".StrikeModifier"))
		{
			config.set(configPath + ".StrikeModifier", strikeModifier);
		}
		else
		{
			strikeModifier = config.getDouble(configPath + ".StrikeModifier");
		}
	}
}
