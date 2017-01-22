package defaultPackadgeHelpers;

public class HighMountains extends Modifiers
{

	public HighMountains( String path)
	{
		this.configPath = path + ".HighMountains";
		initializeDefaults(configPath);
	}
	private void initializeDefaults(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.spawnModifier = 3.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 0.8;
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
			this.spawnModifier = 0.5;
			this.gradeModifier = 0.5;
			this.strikeModifier = 0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.spawnModifier = 0.5;
			this.gradeModifier = 0.5;
			this.strikeModifier = 0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 1.5;
			this.strikeModifier = 1.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.spawnModifier = 0.2;
			this.gradeModifier = 0.2;
			this.strikeModifier = 0.2;
		}
		else if(ore.contains("Lapiz"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 1.5;
			this.strikeModifier = 1.5;
		}
	}
}
