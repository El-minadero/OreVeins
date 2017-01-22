package defaultPackadgeHelpers;

public class Mesa extends Modifiers
{

	public Mesa( String path)
	{
		this.configPath = path + ".Mesa";
		initializeDefaults(configPath);
	}
	private void initializeDefaults(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.spawnModifier = 0.0;
			this.gradeModifier = 0.0;
			this.strikeModifier = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.spawnModifier = 2.0;
			this.gradeModifier = 2.0;
			this.strikeModifier = 2.0;
		}
		else if(ore.contains("BandedIronFormation"))
		{
			this.spawnModifier = 2.0;
			this.gradeModifier = 3.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.spawnModifier = 2.0;
			this.gradeModifier = 3.0;
			this.strikeModifier = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.spawnModifier = 0.0;
			this.gradeModifier = 0.0;
			this.strikeModifier = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.spawnModifier = 0.0;
			this.gradeModifier = 0.0;
			this.strikeModifier = 0.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.spawnModifier = 0.0;
			this.gradeModifier = 0.0;
			this.strikeModifier = 0.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.spawnModifier = 0.0;
			this.gradeModifier = 0.0;
			this.strikeModifier = 0.0;
		}
	}
}
