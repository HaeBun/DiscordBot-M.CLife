package MClife;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {
	public static void Info(GuildMessageReceivedEvent event, Member member, Guild guild) 
	{
		event.getChannel().sendMessage("�� �� �ù߷���\n"
				+ "[ �ڻ��� ��ɾ� ]�� �Է��غ�����\n"
				+ "������:"+member.getEffectiveName()+"\n"
				+ "��� �̸�:"+guild.getName()).queue();
	}
}
