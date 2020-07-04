package MCLife_Crossing;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


class Data {
	
	String userLink = new String();
	
	public void userLink(GuildMessageReceivedEvent event) {
		String playerId = event.getMember().getId();
		userLink = "C:\\Users\\ninin\\Desktop\\Resources\\Discord Bot\\Server List\\"+playerId;
		
	}
	
	public void UIShow() {
		
		
		
	}
	
}

public class PlayerSearch {
	public static void playerCheck(String[] args,GuildMessageReceivedEvent event, Member member) {
		int nb = 1;
		
		String UIData = new String();
		String playerId = event.getMember().getId();
		String userLink = "C:\\Users\\ninin\\Desktop\\Resources\\Discord Bot\\MCLife Crossing\\"+playerId;
		
		
		if(args.length == 1) {
			File userData = new File(userLink);
			EmbedBuilder UI = new EmbedBuilder();
			
			
			if(userData.exists()==false) //유저 데이터가 없을 때
			{
				if(nb==1) {
					UIData += ":one:. ";
					nb++;
				}
				UIData += "유저 등록하기\n";
			} else 	
			{
				if(nb==1) {
					UIData += ":one:. ";
					nb++;
				}
				Path userNamePath = Paths.get(userLink+"\\name.txt");
				
				String name = new String();
				try {
					BufferedReader brname = Files.newBufferedReader(userNamePath);
					name = brname.readLine();
					brname.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				UIData += name+"으로 시작\n";
			}
			
			
			if(nb==2) {
				UIData += ":two:. ";
				nb++;
			}
			UIData += "다른 일하기\n";
			
			UI.addField("명령어를 입력하세요", "앰생의숲 [:regional_indicator_n:]", false);
			UI.setDescription(UIData);
			event.getChannel().sendMessage(UI.build()).queue();
			UI.clear();
		}
		
		else if(args.length == 2) 
		{
			
			EmbedBuilder UI = new EmbedBuilder();
			if(args[1].equalsIgnoreCase("1")) { //유저 등록하기 or 플레이어로 시작
				File userDataCreator = new File(userLink);
				File userNameCreator = new File(userLink+"\\name.txt");
				
				if(!userDataCreator.exists()) {
					userDataCreator.mkdirs();
					event.getChannel().sendMessage("userDataCreator 실행").queue();
				}				
				Path bwNamePath = Paths.get(userLink+"\\name.txt");
				
				try {
					BufferedWriter bwName = Files.newBufferedWriter(bwNamePath, StandardCharsets.UTF_8);

					bwName.write(member.getEffectiveName());
					bwName.flush();
					bwName.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else if(args[1].equalsIgnoreCase("2")) { //다른 일 하기
				
				event.getChannel().sendMessage("준비중");
			}
			
			
		}
	
	}
}
