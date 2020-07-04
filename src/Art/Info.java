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
		Somenale.setDescription("작품 목록 : [ 앰스타 작품 ]\n"
				+ "작품 등록 : [ 앰스타 작품 등록 이름 URL ] 입니다.\n"
				+ "시전자:"+member.getEffectiveName()+"\n"
				+ "길드 이름:"+guild.getName());
		event.getChannel().sendMessage(Somenale.build()).queue();
		Somenale.clear();
		

	}
}
