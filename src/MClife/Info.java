package MClife;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {
	public static void Info(GuildMessageReceivedEvent event, Member member, Guild guild) 
	{
		event.getChannel().sendMessage("뭐 이 시발러마\n"
				+ "[ 앰생아 명령어 ]를 입력해보세요\n"
				+ "시전자:"+member.getEffectiveName()+"\n"
				+ "길드 이름:"+guild.getName()).queue();
	}
}
