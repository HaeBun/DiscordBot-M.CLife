package Stock;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Info {
	public static void Info(String[] args,TextChannel channel) 
	{
		EmbedBuilder commandsListener = new EmbedBuilder();
		commandsListener.setTitle("명령어 목록");
		commandsListener.setDescription("금\n"
				+ "순위\n"
				+ "내정보");
		channel.sendMessage(commandsListener.build()).queue();
		commandsListener.clear();
	}
}
