package MCLife_Crossing;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Main {
	public static void login(String[] args, GuildMessageReceivedEvent event, Member member) {
		if (args.length == 1) {
			EmbedBuilder MC = new EmbedBuilder();
			MC.setImage("https://t1.daumcdn.net/cfile/tistory/99A29C4A5E8C49ED02");
			MC.setFooter("¨Ï2020 TeamHaeBun Ver. Beta.0.1");
			event.getChannel().sendMessage(MC.build()).queue();
			MC.clear();

			
			MCLife_Crossing.PlayerSearch.playerCheck(args, event, member);

		} else {
			
			MCLife_Crossing.PlayerSearch.playerCheck(args, event, member);
		}
	}
}
