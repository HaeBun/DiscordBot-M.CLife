package Art;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {
	public static void Info(GuildMessageReceivedEvent event, Member member, Guild guild) 
	{
		
		EmbedBuilder Somenale = new EmbedBuilder();
		
		Somenale.setImage("https://media.discordapp.net/attachments/670798918820429835/709484560206921748/MStagram.jpg");
		
		event.getChannel().sendMessage(Somenale.build()).queue();
		Somenale.clear();
		
		Somenale.setTitle("- Mstagram -");
		Somenale.setDescription("��ǰ ��� : [ �ڽ�Ÿ ��ǰ ]\n"
				+ "��ǰ ��� : [ �ڽ�Ÿ ��ǰ ��� �̸� URL ] �Դϴ�.\n"
				+ "������:"+member.getEffectiveName()+"\n"
				+ "��� �̸�:"+guild.getName());
		event.getChannel().sendMessage(Somenale.build()).queue();
		Somenale.clear();
		

	}
}
