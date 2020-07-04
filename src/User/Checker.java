package User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Checker {
	public static void Chk(Path Info ,File userCheckPath, String userList, Member member, Guild guild, GuildMessageReceivedEvent event)
	{
		//유저 등록 확인 --
		if(userCheckPath.exists()==false) 
		{
			userCheckPath.mkdirs();
			File userInfo = new File(userList+member.getId()+"\\Info.txt");
			
			try 
			{
				userInfo.createNewFile();
				
				BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
				
				userInfoWriter.write("닉네임:"+member.getEffectiveName());
				userInfoWriter.newLine();
				userInfoWriter.write("등록 서버:"+member.getGuild().getId()+"("+guild.getName()+")");
				userInfoWriter.newLine();
				userInfoWriter.write("원:10000000");
				userInfoWriter.newLine();
				userInfoWriter.write("금:0");
				userInfoWriter.newLine();
				userInfoWriter.write("달러:0");
				userInfoWriter.newLine();
				userInfoWriter.write("엔:0");
				userInfoWriter.newLine();
				userInfoWriter.write("유로:0");
				userInfoWriter.newLine();
				userInfoWriter.write("위안:0");
				
				userInfoWriter.flush();

			}
			
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			EmbedBuilder accountCreater = new EmbedBuilder();
			accountCreater.setTitle("계정 등록 완료");
			accountCreater.setDescription(member.getAsMention()+"님의 초기자금 1,000만원이 지급되었습니다.\n\n"
					+ "본 서비스는 비영리 목적으로 제작된 투자 연습 프로그램입니다.");
			accountCreater.addField("사용자", member.getEffectiveName(), false);
			event.getChannel().sendMessage(accountCreater.build()).queue();
			accountCreater.clear();
		}
		//유저 등록 확인 --
		
	}
}
