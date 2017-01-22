package defaultPackadgeHelpers;

public class Forest extends Modifiers
{

	public Forest( String path)
	{
		this.configPath = path + ".Forest";
		initializeDefaults(configPath);
	}
	private void initializeDefaults(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.spawnModifier = 2.0;
			this.gradeModifier = 2.0;
			this.strikeModifier = 2.0;
		}
		else if(ore.contains("BandedIronFormation"))
		{
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
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
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
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
			this.spawnModifier = 1.0;
			this.gradeModifier = 1.0;
			this.strikeModifier = 1.0;
		}
	}
}
