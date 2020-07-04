package Garden;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {
	public static void myGuarden(String[] args, GuildMessageReceivedEvent event ) {
		EmbedBuilder myGuarden = new EmbedBuilder();
		
		if (args.length==1)
		{
			String Garden = new String();
			
			for(int i=0; i<3; i++) 
			{
				for(int j=0; j<8; j++)
				{
					if(i!=0 && i!=2)
					{
						if(j==2||j==5) 
						{
							Garden += ":sunflower:";
						}
						else
						{
							Garden += ":green_square:";
						}
					}
					else
					{
						Garden +=":green_square:";
					}
				}
				Garden += "\n";
			}
			myGuarden.setTitle("¸¶´ç");
			myGuarden.setDescription(Garden);
			event.getChannel().sendMessage(myGuarden.build()).queue();
		}
		
		
	}
}
