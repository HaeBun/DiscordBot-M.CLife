package Art;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Main {
	public static String MstagramList = "C:\\Users\\ninin\\Desktop\\Resources\\Discord Bot\\Mstagram\\";
	public static void main(String[] args, Guild guild, GuildMessageReceivedEvent event, Member member) {
//		Path CommandsList = Paths.get(serverList + guild.getId() + "\\-list-.txt");
//		File serverPath = new File(serverList + guild.getId());

		Path CommandsList = Paths.get(MstagramList + "-list-.txt");
		File serverPath = new File(MstagramList);

		Art.Checker.Chk(event, serverPath, MstagramList, guild); // ���� ���� ���� �˻�

		if (args.length == 1) Art.Info.Info(event, member, guild);// �ڽ�Ÿ�� �θ� ���
		
		else {
			
			
			if (args[1].equalsIgnoreCase("��ǰ")) 
				if(args.length == 2) 
				{
					Art.CommandReader.call(args, event, CommandsList);
				}
			
			
				else {
					
					if (args[2].equalsIgnoreCase("�����") || args[2].equalsIgnoreCase("���")) {
						if (args.length == 3) 
						{
							event.getChannel().sendMessage("�� �� ����Ѵٴ°ž� �� ��. ��. ��. ��." + "\n�ڽ�Ÿ ������ ȭ�� �����ϴ�.").queue();
							event.getChannel().sendMessage("��ǰ�� ����ϰ� ������ [�ڽ�Ÿ ��ǰ ��� URL]���� �ؾ��մϴ�.").queue();
						} 
						
						else if (args.length == 4) 
						{
							event.getChannel().sendMessage("URL�̹����� �Է��ؾ� ����� ���� ��.��").queue();
						} 
						
						else {
							event.getChannel().sendMessage("���� ��������� ��������").queue();

							// CCTXT = Create Command TXT

							String txtPath = MstagramList + args[3];
							File checkCCTXT = new File(txtPath);
							Path Path = Paths.get(txtPath + ".txt");

							if (checkCCTXT.exists() == false) {

								try {
									BufferedWriter CCTXT = Files.newBufferedWriter(Path, StandardCharsets.UTF_8);
									int argsLength = args.length;

									event.getChannel().sendMessage("��ǰ " + args[3] + "��(��) ����߽��ϴ�!").queue();

									String Commants = new String();
									for (int i = 4; i < argsLength; i++) {
										Commants = Commants + args[i];
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
									listUpdate.write(args[3]);
									listUpdate.flush();

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								event.getChannel().sendMessage(args[3] + "�� �߰��� ���� ���� �ֽ��ϴ�.").queue();
							}
						}
					} 
					
					
					else {
						
						Path checkCommandsList = Paths.get(MstagramList + "-list-.txt");
						try {
							BufferedReader Read = Files.newBufferedReader(checkCommandsList);
							for (int i = 0; i < 1;) {
								String DirectCommand = Read.readLine();
								if (DirectCommand.equalsIgnoreCase(args[2])) {
									Path B = Paths.get(MstagramList + args[2] + ".txt");
									BufferedReader Commants = Files.newBufferedReader(B);
									EmbedBuilder Image = new EmbedBuilder();
									
									Image.setImage(Commants.readLine());
									
									event.getChannel().sendMessage(Image.build()).queue();
									
									Image.clear();
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
			
			
			

			
			else {
				


			}
		}
	}
}