package defaultPackadgeHelpers;

public class Plateau extends Modifiers
{

	public Plateau( String path)
	{
		this.configPath = path + ".Plateau";
		initializeDefaults(configPath);
	}
	private void initializeDefaults(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.spawnModifier = 1.5;
			this.gradeModifier = 1.5;
			this.strikeModifier = 1.5;
		}
		else if(ore.contains("BandedIronFormation"))
		{
			this.spawnModifier = 0.75;
			this.gradeModifier = 0.75;
			this.strikeModifier = 0.75;
		}
		else if(ore.contains("Iron"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.spawnModifier = 0.75;
			this.gradeModifier = 0.75;
			this.strikeModifier = 0.75;
		}
		else if(ore.contains("Redstone"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.spawnModifier = 1.25;
			this.gradeModifier = 1.25;
			this.strikeModifier = 1.25;
		}
		else if(ore.contains("Diamond"))
		{
			this.spawnModifier = 3.0;
			this.gradeModifier = 2.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.spawnModifier = 1.25;
			this.gradeModifier = 1.25;
			this.strikeModifier = 1.25;
		}
	}
}
