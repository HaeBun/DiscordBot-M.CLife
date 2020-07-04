package Art;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


class allCheck{
	
	public static String[] cmdCheck(String[] cmdsList, String directCommand, Path CommandsList) {
		try {
			BufferedReader CmdChecker = Files.newBufferedReader(CommandsList);

			for (int i = 0; i < 1000; i++) {
				String directCheckCommands = CmdChecker.readLine();
				if (directCheckCommands != null) {
					cmdsList[i] = directCommand;
				} else {
					i = 1000;
				}
			}
			
			
			return cmdsList;
		}
		
		catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return cmdsList;
		
	}
	
}

public class CommandReader { // 명령어가 있는지 보여주는 함수
	public static void call(String[] args, GuildMessageReceivedEvent event, Path CommandsList) {


		// 대략 1000개의 명령어를 로드하는 기능을 가지고 있음
		event.getChannel().sendMessage(" 유니버셜 작품들 ").queue();
		// 총 명령어 수를 보여주는 구간

		// allCheck 함수 결과값 호출
		
		// 페이지 수를 상세하게 입력한 경우
		if (args.length == 2 || args[2].equalsIgnoreCase(Integer.toString(1))) {
			try {
				String cmdsList[] = new String[10];
				BufferedReader cmdChecker = Files.newBufferedReader(CommandsList);
				String directCommand = new String();
				int previewCommandsLength = 0;
				for (int i = 0; i < 10; i++) // 상위 10개의 명령어를 보여줌.
				{
					directCommand = cmdChecker.readLine();

					if (directCommand != null) {
						cmdsList[i] = directCommand;
						previewCommandsLength++;
					} else {
						i = 10;
					}
				}
				directCommand = "";
				for (int i = 0; i < previewCommandsLength; i++) {
					directCommand = directCommand + cmdsList[i] + "\n";
				}
				event.getChannel().sendMessage(directCommand).queue();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 페이지 수를 상세하게 입력한 경우 예시: 앰생아 명령어 2...
		else {
			int page = Integer.valueOf(args[2]); // 페이지 수를 파악함
			String cmdsList[] = new String[1000];

			//allCheck 구간 함수 호출
			
			String DirectCommand = new String();
			for (int i = ((page - 1) * 10); i < page; i++) {
				DirectCommand = DirectCommand + "\n" + cmdsList[i];
				event.getChannel().sendMessage(DirectCommand);
			}
		}
	}
}
