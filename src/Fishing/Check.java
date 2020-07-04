package Fishing;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Check {
	public static void Check(String args[], GuildMessageReceivedEvent event)
	{
		if (args.length==1)
		{
			event.getChannel().sendMessage("¸í·É¾î Test").queue();
		}
	}
}
