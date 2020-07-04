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

public class CommandReader { // ��ɾ �ִ��� �����ִ� �Լ�
	public static void call(String[] args, GuildMessageReceivedEvent event, Path CommandsList) {


		// �뷫 1000���� ��ɾ �ε��ϴ� ����� ������ ����
		event.getChannel().sendMessage(" ���Ϲ��� ��ǰ�� ").queue();
		// �� ��ɾ� ���� �����ִ� ����

		// allCheck �Լ� ����� ȣ��
		
		// ������ ���� ���ϰ� �Է��� ���
		if (args.length == 2 || args[2].equalsIgnoreCase(Integer.toString(1))) {
			try {
				String cmdsList[] = new String[10];
				BufferedReader cmdChecker = Files.newBufferedReader(CommandsList);
				String directCommand = new String();
				int previewCommandsLength = 0;
				for (int i = 0; i < 10; i++) // ���� 10���� ��ɾ ������.
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

		// ������ ���� ���ϰ� �Է��� ��� ����: �ڻ��� ��ɾ� 2...
		else {
			int page = Integer.valueOf(args[2]); // ������ ���� �ľ���
			String cmdsList[] = new String[1000];

			//allCheck ���� �Լ� ȣ��
			
			String DirectCommand = new String();
			for (int i = ((page - 1) * 10); i < page; i++) {
				DirectCommand = DirectCommand + "\n" + cmdsList[i];
				event.getChannel().sendMessage(DirectCommand);
			}
		}
	}
}
