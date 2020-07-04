package KimInTae;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite.Channel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class Commands extends ListenerAdapter {

	public static JDA jda;

	String serverList = LinkPath.Main.serverList;
	String userList = LinkPath.Main.userList;
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		String[] args = event.getMessage().getContentRaw().split("\\s+");
		
		Member member = event.getMember();
		Guild guild = event.getChannel().getGuild();
		
		
		if (args[0].equalsIgnoreCase("앰생아") || args[0].equalsIgnoreCase("앰생이")) {
			MClife.Main.main(args, serverList, guild, event, member);
		}
		
		else if (args[0].equalsIgnoreCase("앰스타") || args[0].equalsIgnoreCase("앰스타그램")) {
			Art.Main.main(args, guild, event, member);
		}
		
		else if (args[0].equalsIgnoreCase("앰생투자") || args[0].equalsIgnoreCase("앰투")) {
			String userPath = userList + member.getId();
			File userCheckPath = new File(userPath);
			Path Info = Paths.get(userPath + "\\Info.txt");

			User.Checker.Chk(Info, userCheckPath, userList, member, guild, event);

			if (args.length == 1) {
				
				Stock.Info.Info(args, event.getChannel());
				
			} else {
				if (args[1].equalsIgnoreCase("금")) {
					Stock.GoldStock.Gold(event, args, Info);
				}

				else if (args[1].equalsIgnoreCase("내정보")) {
					
					User.InfoLoader.Report(args, Info, event, member);
					
				}

				else if (args[1].equalsIgnoreCase("순위")) {
					
					Ranking.Info.Chk(args, event);
					
				}

				else if (args[1].equalsIgnoreCase("달러")) 
					Stock.Exchange_Rate.USDStock(event, args, member, Info);
					
				else if (args[1].equalsIgnoreCase("엔")) 
					Stock.Exchange_Rate.JPYStock(event, args, member, Info);
					
				else if (args[1].equalsIgnoreCase("유로")) 
					Stock.Exchange_Rate.EURStock(event, args, member, Info);
					
				else if (args[1].equalsIgnoreCase("위안")) 
					Stock.Exchange_Rate.CNYStock(event, args, member, Info);

			}
		}

		else if (args[0].equalsIgnoreCase("낚시")) 
		{
			Fishing.Check.Check(args, event);
		} 
		
		else if (args[0].equalsIgnoreCase("마당")) 
		{
			Garden.Info.myGuarden(args, event);
		} 
		
		else if (args[0].equalsIgnoreCase("앰생의숲") || args[0].equalsIgnoreCase("앰숲")) 
		{
			MCLife_Crossing.Main.login(args, event, member);
		} 
		
		else if (args[0].equalsIgnoreCase("춤춰")) 
		{
			
			EmbedBuilder Dance = new EmbedBuilder();
			
			Dance.setImage("https://file3.instiz.net/data/file3/2019/05/18/5/3/4/534a35303be1dce7caeaa94b08e46a93.gif");
			
			event.getChannel().sendMessage(Dance.build()).queue();
			Dance.clear();
			
			KimInTae.Main.setStatus(event.getGuild().getName(), "춤추기");
		}
		else if (args[0].equalsIgnoreCase("카무이"))
		{
			if (args.length==2) {
				Guild guildEntry = jda.getGuildById(args[1]);
		        if (guildEntry != null) {
		        	event.getChannel().sendMessage("서버를 찾음 :"+guildEntry.getName()).queue();
		        }
		        else {
		        	System.out.println("서버를 찾지 못함.");
		        }
			}
		}
	}
}	
