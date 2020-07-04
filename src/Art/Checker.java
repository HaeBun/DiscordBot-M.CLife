package Art;

import java.io.File;
import java.io.IOException;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Checker {
	public static void Chk(GuildMessageReceivedEvent event, File serverPath, String serverList, Guild guild) {
		if(serverPath.exists()==false) 
		{
			event.getChannel().sendMessage("미술관을 등록중입니다...").queue();
			serverPath.mkdirs();
			File listFileCreate = new File(serverList+"\\-list-.txt");
			
			try 
			{
				listFileCreate.createNewFile();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			event.getChannel().sendMessage("등록 완료!").queue();
		} 
	}
}
