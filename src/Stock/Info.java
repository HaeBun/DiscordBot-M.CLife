package Stock;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Info {
	public static void Info(String[] args,TextChannel channel) 
	{
		EmbedBuilder commandsListener = new EmbedBuilder();
		commandsListener.setTitle("��ɾ� ���");
		commandsListener.setDescription("��\n"
				+ "����\n"
				+ "������");
		channel.sendMessage(commandsListener.build()).queue();
		commandsListener.clear();
	}
}
