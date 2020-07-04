package Stock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class GoldStock {
	public static void Gold(GuildMessageReceivedEvent event, String[] args,Path Info) {
		if(args.length==2) //'��' �� ���� �������
		{
			String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //ũ�Ѹ��� �ּ�����
			Document doc = null;
			
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements element = doc.select("table.tbl_exchange.market");
			Elements exday = doc.select("p.no_exday");
			
			Iterator<Element> price = element.select("td").iterator();
			Iterator<Element> no_exday = exday.select("p.no_exday").iterator();	
			
			String[] ig1 = price.next().text().split(",");
			String splitig1 = ig1[0]+ig1[1];
			String[] cig1 = splitig1.split("��");
			double goldPrice1 = Double.valueOf(cig1[0]);

			
			String[] ig2 = price.next().text().split(",");
			String splitig2 = ig2[0]+ig2[1];
			String[] cig2 = splitig2.split("��");
			double goldPrice2 = Double.valueOf(cig2[0]);
			
			String S = no_exday.next().text();
			
			EmbedBuilder liveStockPrice = new EmbedBuilder();
			
			liveStockPrice.setTitle("���� �� �ü�(�Ÿű����� ��/g): "+(goldPrice1+goldPrice2)/2+"\n"+S);
			liveStockPrice.setDescription("�����: "+event.getMember().getUser().getAsMention()+"��\n"
					+ "�� ���� ��): "+goldPrice1+"��"+"\n"
					+ "�� �Ǹ� ��): "+goldPrice2+"��");
			liveStockPrice.setImage("https://ssl.pstatic.net/imgfinance/chart/marketindex/area/month/CMDT_GD.png");
			liveStockPrice.addField("�ŷ� ���", "�ڻ����� �� ����(or �Ǹ�) ����(g)", false);
			
			liveStockPrice.setColor(0xffff00);
			event.getChannel().sendMessage(liveStockPrice.build()).queue();
			
			double PlayerCash = 0;
			double PlayerGold = 0;
			
			try {
				BufferedReader userInfoReader = Files.newBufferedReader(Info);
				String DirectReader = userInfoReader.readLine();
				String[] chartChecker = new String [2];
				for(int i=0; i<1;) {
					if(DirectReader!=null) {
						chartChecker = DirectReader.split(":");
						if(chartChecker[0].equalsIgnoreCase("��")) 
						{
							PlayerCash = Double.valueOf(chartChecker[1]);
						} 
						
						else if(chartChecker[0].equalsIgnoreCase("��")) 
						{
							PlayerGold = Double.valueOf(chartChecker[1]);
						}
						DirectReader = userInfoReader.readLine();
					} else {
						i++;
					}
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			event.getChannel().sendMessageFormat(event.getMember().getAsMention()+"���� ���� '��'��\n"
					+ "%.1fg("
					+ "�� %.1f��"
					+ ")[�� %.0f��]��ŭ ����,\n"
					+ PlayerGold+"g(�� %.1f��)[�� %.0f��]��ŭ �Ǹ��� �� �ֽ��ϴ�.",
					(PlayerCash/goldPrice1),
					(PlayerCash/goldPrice1)/3.75,
					(PlayerCash/goldPrice1)*goldPrice1,
					PlayerGold/3.75,
					(PlayerGold*goldPrice2)
					).queue();
		} 
		
		else 
		{
			if(args[2].equalsIgnoreCase("����")) 
			{
				if(args.length==3) //���Ÿ� ���� ��� 
				{
					event.getChannel().sendMessage("������ g���� �Է����ּž� �մϴ�.").queue();
				}
				else //����
				{
					int PlayerCash = 0;
					double PlayerGold = 0;
					double PlayerUSD = 0;
					double PlayerCNY = 0;
					double PlayerEUR = 0;
					double PlayerJPY = 0;
					
					String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //ũ�Ѹ��� url����
					Document doc = null;
					
					try {
						doc = Jsoup.connect(url).get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Elements element = doc.select("table.tbl_exchange.market");
					
					Iterator<Element> price = element.select("td").iterator();
					
					String[] ig1 = price.next().text().split(",");
					String splitig1 = ig1[0]+ig1[1];
					String[] cig1 = splitig1.split("��");
					double goldPrice1 = Double.valueOf(cig1[0]);
					
					try {
						BufferedReader userInfoReader = Files.newBufferedReader(Info);
						String DirectReader = userInfoReader.readLine();
						String[] chartChecker = new String [5];
						for(int i=0; i<1;) {
							if(DirectReader!=null) {
								chartChecker = DirectReader.split(":");
								if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerCash = Integer.valueOf(chartChecker[1]);
								} 
								
								else if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerGold = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("�޷�")) 
								{
									PlayerUSD = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerJPY = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("����")) 
								{
									PlayerEUR = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("����")) 
								{
									PlayerCNY = Double.valueOf(chartChecker[1]);
								}
								
								DirectReader = userInfoReader.readLine();
							} else {
								i++;
							}
						}
						
						if((Double.valueOf(args[3])*goldPrice1)>PlayerCash){
							event.getChannel().sendMessage("�ܾ��� �����մϴ�.").queue();
						}
						else
						{
							BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
							
							userInfoWriter.write("�г���:"+event.getMember().getEffectiveName());
							userInfoWriter.newLine();
							userInfoWriter.write("��� ����:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+Integer.toString((int) (PlayerCash-(Double.valueOf(args[3])*goldPrice1))));
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+Double.toString(PlayerGold+Double.valueOf(args[3])));
							userInfoWriter.newLine();
							userInfoWriter.write("�޷�:"+PlayerUSD);
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+PlayerJPY);
							userInfoWriter.newLine();
							userInfoWriter.write("����:"+PlayerEUR);
							userInfoWriter.newLine();
							userInfoWriter.write("����:"+PlayerCNY);
							
							userInfoWriter.flush();
							
							event.getChannel().sendMessage(event.getMember().getAsMention()+"���� �ŷ��� ����Ǿ����ϴ�").queue();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			else if(args[2].equalsIgnoreCase("�Ǹ�"))
			{
				if(args.length==3) //�ǸŸ� ���� ��� 
				{
					event.getChannel().sendMessage("�Ǹ��� g���� �Է����ּž� �մϴ�.").queue();
				}
				else 
				{
					int PlayerCash = 0;
					double PlayerGold = 0;
					double PlayerUSD = 0;
					double PlayerCNY = 0;
					double PlayerEUR = 0;
					double PlayerJPY = 0;
					
					String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //ũ�Ѹ��� url����
					Document doc = null;
					
					try {
						doc = Jsoup.connect(url).get();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Elements element = doc.select("table.tbl_exchange.market");
					
					Iterator<Element> price = element.select("td").iterator();
					
					String[] ig2 = price.next().text().split(",");
					String splitig2 = ig2[0]+ig2[1];
					String[] cig2 = splitig2.split("��");
					double goldPrice2 = Double.valueOf(cig2[0]);
					
					try {
						BufferedReader userInfoReader = Files.newBufferedReader(Info);
						String DirectReader = userInfoReader.readLine();
						String[] chartChecker = new String [2];
						for(int i=0; i<1;) {
							if(DirectReader!=null) {
								chartChecker = DirectReader.split(":");
								if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerCash = Integer.valueOf(chartChecker[1]);
								} 
								
								else if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerGold = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("�޷�")) 
								{
									PlayerUSD = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("��")) 
								{
									PlayerJPY = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("����")) 
								{
									PlayerEUR = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("����")) 
								{
									PlayerCNY = Double.valueOf(chartChecker[1]);
								}
								DirectReader = userInfoReader.readLine();
							} else {
								i++;
							}
						}
						
						if(Double.valueOf(args[3])>PlayerGold){
							event.getChannel().sendMessage("�Ǹ��� ���� ���� Ȯ���ϼ���.").queue();
						}
						else
						{
							BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
							
							userInfoWriter.write("�г���:"+event.getMember().getEffectiveName());
							userInfoWriter.newLine();
							userInfoWriter.write("��� ����:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+Integer.toString((int) (PlayerCash+(Double.valueOf(args[3]))*goldPrice2)));
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+Double.toString(PlayerGold-Double.valueOf(args[3])));
							userInfoWriter.newLine();
							userInfoWriter.write("�޷�:"+PlayerUSD);
							userInfoWriter.newLine();
							userInfoWriter.write("��:"+PlayerJPY);
							userInfoWriter.newLine();
							userInfoWriter.write("����:"+PlayerEUR);
							userInfoWriter.newLine();
							userInfoWriter.write("����:"+PlayerCNY);
							
							userInfoWriter.flush();
							
							event.getChannel().sendMessage(event.getMember().getAsMention()+"���� �ŷ��� ����Ǿ����ϴ�").queue();
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
