package Server;

import java.io.File;
import java.io.IOException;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Checker {
	public static void Chk(GuildMessageReceivedEvent event, File serverPath, String serverList, Guild guild) {
		if(serverPath.exists()==false) 
		{
			event.getChannel().sendMessage("서버를 등록중입니다...").queue();
			serverPath.mkdirs();
			File listFileCreate = new File(serverList+guild.getId()+"\\-list-.txt");
			
			try 
			{
				listFileCreate.createNewFile();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("다 넣음 ㅋ").queue();
		} 
	}
}
