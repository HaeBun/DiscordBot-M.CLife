package MClife;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Main 
{
	public static void main(String[] args, String serverList, Guild guild, GuildMessageReceivedEvent event, Member member) {
		Path CommandsList = Paths.get(serverList + guild.getId() + "\\-list-.txt");
		File serverPath = new File(serverList + guild.getId());

		Server.Checker.Chk(event, serverPath, serverList, guild); // ���� ���� ���� �˻�

		if (args.length == 1) // �ڻ��� �� �θ� ���
			MClife.Info.Info(event, member, guild);
		
		
		else {
			
			
			if (args[1].equalsIgnoreCase("��ɾ�")) 
				MClife.CommandReader.call(args, event, CommandsList);
			
			else {

				
				if (args[1].equalsIgnoreCase("�����")) {
					if (args.length == 2) 
					{
						event.getChannel().sendMessage("�� �� ����°ž� �� ��. ��. ��. ��." + "\n�ڻ��� ȭ�� �����ϴ�.").queue();
						event.getChannel().sendMessage("���� ����ġ�� ������ [�ڻ��� ����� ���� ���]���� �ؾ��մϴ�.").queue();
					} 
					
					else if (args.length == 3) 
					{
						event.getChannel().sendMessage("����� ���������� �� �� ����ġ�°ų�?").queue();
					} 
					
					else {
						event.getChannel().sendMessage("���� ��������� ��������").queue();

						// CCTXT = Create Command TXT

						String txtPath = serverList + guild.getId() + "\\" + args[2];
						File checkCCTXT = new File(txtPath);
						Path Path = Paths.get(txtPath + ".txt");

						if (checkCCTXT.exists() == false) {

							try {
								BufferedWriter CCTXT = Files.newBufferedWriter(Path, StandardCharsets.UTF_8);
								int argsLength = args.length;

								event.getChannel().sendMessage("�ڻ��� " + args[argsLength - 1] + "��(��) �����!").queue();

								String Commants = new String();
								for (int i = 3; i < argsLength; i++) {
									Commants = Commants + " " + args[i];
								}
								CCTXT.write(Commants);
								CCTXT.flush();

								BufferedReader addList = Files.newBufferedReader(CommandsList);
								String cList[] = new String[100];
								int cListLength = 0;
								for (int i = 0; i < 100;) {
									String CheckCommands = addList.readLine();

									if (CheckCommands != null) {
										cList[i] = CheckCommands;
										i++;
									} else {
										cListLength = i;
										i = 100;

									}
								}
								BufferedWriter listUpdate = Files.newBufferedWriter(CommandsList,
										StandardCharsets.UTF_8);

								for (int i = 0; i < cListLength; i++) {
									listUpdate.write(cList[i]);
									listUpdate.newLine();
								}
								listUpdate.write(args[2]);
								listUpdate.flush();

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							event.getChannel().sendMessage(args[1] + "�� �߰��� ���� ���� �ֽ��ϴ�.").queue();
						}
					}
				} else {
					String ServerID = guild.getId();
					Path checkCommandsList = Paths.get(serverList + ServerID + "\\-list-.txt");
					try {
						BufferedReader Read = Files.newBufferedReader(checkCommandsList);
						for (int i = 0; i < 1;) {
							String DirectCommand = Read.readLine();

							if (DirectCommand.equalsIgnoreCase(args[1])) {
								Path B = Paths.get(serverList + ServerID + "\\" + args[1] + ".txt");
								BufferedReader Commants = Files.newBufferedReader(B);
								String D = Commants.readLine();

								event.getChannel().sendMessage(D).queue();
								i++;
							}

							else if (DirectCommand.equalsIgnoreCase("")) {
								i++;
							}
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}