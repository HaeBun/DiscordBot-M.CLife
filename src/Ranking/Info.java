package Ranking;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Info {
	public static void Chk(String[] args, GuildMessageReceivedEvent event) 
	{	
		if(args.length==2) {
			
			String rankingList = "C:\\Users\\ninin\\Desktop\\Resources\\Discord Bot\\Ranking\\";
			Path rankingTXTPath = Paths.get(rankingList+"Ranking.txt");
			int users = 0;
			

			try {
				BufferedReader valueChecker = Files.newBufferedReader(rankingTXTPath);
				String rankData = "";
				
				for(int i=0; i<1;) {
					rankData = valueChecker.readLine();
					if(rankData!=null) 
					{
						users++;
					}
					else
					{
						i++;
					}
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				event.getChannel().sendMessage("//TODO Auto-generated catch block").queue();
			}
			String userDetailData[][] = new String[users][users];
			
			try {
				BufferedReader rankChecker = Files.newBufferedReader(rankingTXTPath);
				String rankData = new String();

				for(int i=0; i<users; i++) {
					rankData = rankChecker.readLine();
					if(rankData!=null) 
					{
						userDetailData[i][0] = rankData.split(":")[0];
						userDetailData[i][1] = rankData.split(":")[1];
					}
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String top10 = new String();
			
			for(int i=1; i<=users; i++) 
			{
				if(i>10) 
				{
					i=users;
				}
				else
				{
					if(i==1) 
					{
						top10 = top10+":first_place: 　"+userDetailData[i-1][1]+" \t"+userDetailData[i-1][0]+"\n";
					}
					
					else if(i==2)
					{
						top10 = top10+":second_place: 　"+userDetailData[i-1][1]+" \t"+userDetailData[i-1][0]+"\n";
					}
					
					else if(i==3)
					{
						top10 = top10+":third_place: 　"+userDetailData[i-1][1]+" \t"+userDetailData[i-1][0]+"\n";
					}
					
					else if(i==10)
					{
						top10 = top10+i+"위　"+userDetailData[i-1][1]+" \t"+userDetailData[i-1][0];
					}
					
					else 
					{
						top10 = top10+i+"위　"+userDetailData[i-1][1]+" \t"+userDetailData[i-1][0]+"\n";
					}
					
				}
			}
			
			EmbedBuilder WorldRanking = new EmbedBuilder();
			WorldRanking.setTitle(":earth_asia: 앰생투자 월드랭킹");
			WorldRanking.setDescription(top10);
			WorldRanking.addField("내 순위", " 준비중", false);
			WorldRanking.setColor(0x00FF00);
			
			event.getChannel().sendMessage(WorldRanking.build()).queue();
			WorldRanking.clear();
		}
		
	}
}