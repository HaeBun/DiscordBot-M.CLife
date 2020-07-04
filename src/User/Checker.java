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
		//���� ��� Ȯ�� --
		if(userCheckPath.exists()==false) 
		{
			userCheckPath.mkdirs();
			File userInfo = new File(userList+member.getId()+"\\Info.txt");
			
			try 
			{
				userInfo.createNewFile();
				
				BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
				
				userInfoWriter.write("�г���:"+member.getEffectiveName());
				userInfoWriter.newLine();
				userInfoWriter.write("��� ����:"+member.getGuild().getId()+"("+guild.getName()+")");
				userInfoWriter.newLine();
				userInfoWriter.write("��:10000000");
				userInfoWriter.newLine();
				userInfoWriter.write("��:0");
				userInfoWriter.newLine();
				userInfoWriter.write("�޷�:0");
				userInfoWriter.newLine();
				userInfoWriter.write("��:0");
				userInfoWriter.newLine();
				userInfoWriter.write("����:0");
				userInfoWriter.newLine();
				userInfoWriter.write("����:0");
				
				userInfoWriter.flush();

			}
			
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			EmbedBuilder accountCreater = new EmbedBuilder();
			accountCreater.setTitle("���� ��� �Ϸ�");
			accountCreater.setDescription(member.getAsMention()+"���� �ʱ��ڱ� 1,000������ ���޵Ǿ����ϴ�.\n\n"
					+ "�� ���񽺴� �񿵸� �������� ���۵� ���� ���� ���α׷��Դϴ�.");
			accountCreater.addField("�����", member.getEffectiveName(), false);
			event.getChannel().sendMessage(accountCreater.build()).queue();
			accountCreater.clear();
		}
		//���� ��� Ȯ�� --
		
	}
}
