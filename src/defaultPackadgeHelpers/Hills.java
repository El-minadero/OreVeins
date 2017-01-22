package defaultPackadgeHelpers;

public class Hills extends Modifiers
{

	public Hills( String path)
	{
		this.configPath = path + ".Hills";
		initializeDefaults(configPath);
	}
	private void initializeDefaults(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 3.0;
			this.strikeModifier = 3.0;
		}
		else if(ore.contains("BandedIronFormation"))
		{
			this.spawnModifier = 0.5;
			this.gradeModifier = 0.5;
			this.strikeModifier = 0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 1.5;
			this.strikeModifier = 1.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 1.5;
			this.strikeModifier = 1.5;
		}
	}
}
