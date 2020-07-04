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
		if(args.length==2) //'금' 만 정보 받은경우
		{
			String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //크롤링할 주소지정
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
			String[] cig1 = splitig1.split("원");
			double goldPrice1 = Double.valueOf(cig1[0]);

			
			String[] ig2 = price.next().text().split(",");
			String splitig2 = ig2[0]+ig2[1];
			String[] cig2 = splitig2.split("원");
			double goldPrice2 = Double.valueOf(cig2[0]);
			
			String S = no_exday.next().text();
			
			EmbedBuilder liveStockPrice = new EmbedBuilder();
			
			liveStockPrice.setTitle("국내 금 시세(매매기준율 ￦/g): "+(goldPrice1+goldPrice2)/2+"\n"+S);
			liveStockPrice.setDescription("사용자: "+event.getMember().getUser().getAsMention()+"님\n"
					+ "금 구매 시): "+goldPrice1+"원"+"\n"
					+ "금 판매 시): "+goldPrice2+"원");
			liveStockPrice.setImage("https://ssl.pstatic.net/imgfinance/chart/marketindex/area/month/CMDT_GD.png");
			liveStockPrice.addField("거래 방법", "앰생투자 금 구매(or 판매) 수량(g)", false);
			
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
						if(chartChecker[0].equalsIgnoreCase("원")) 
						{
							PlayerCash = Double.valueOf(chartChecker[1]);
						} 
						
						else if(chartChecker[0].equalsIgnoreCase("금")) 
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
			
			event.getChannel().sendMessageFormat(event.getMember().getAsMention()+"님은 현재 '금'을\n"
					+ "%.1fg("
					+ "약 %.1f돈"
					+ ")[약 %.0f원]만큼 구매,\n"
					+ PlayerGold+"g(약 %.1f돈)[약 %.0f원]만큼 판매할 수 있습니다.",
					(PlayerCash/goldPrice1),
					(PlayerCash/goldPrice1)/3.75,
					(PlayerCash/goldPrice1)*goldPrice1,
					PlayerGold/3.75,
					(PlayerGold*goldPrice2)
					).queue();
		} 
		
		else 
		{
			if(args[2].equalsIgnoreCase("구매")) 
			{
				if(args.length==3) //구매만 적은 경우 
				{
					event.getChannel().sendMessage("구매할 g수를 입력해주셔야 합니다.").queue();
				}
				else //실행
				{
					int PlayerCash = 0;
					double PlayerGold = 0;
					double PlayerUSD = 0;
					double PlayerCNY = 0;
					double PlayerEUR = 0;
					double PlayerJPY = 0;
					
					String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //크롤링할 url지정
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
					String[] cig1 = splitig1.split("원");
					double goldPrice1 = Double.valueOf(cig1[0]);
					
					try {
						BufferedReader userInfoReader = Files.newBufferedReader(Info);
						String DirectReader = userInfoReader.readLine();
						String[] chartChecker = new String [5];
						for(int i=0; i<1;) {
							if(DirectReader!=null) {
								chartChecker = DirectReader.split(":");
								if(chartChecker[0].equalsIgnoreCase("원")) 
								{
									PlayerCash = Integer.valueOf(chartChecker[1]);
								} 
								
								else if(chartChecker[0].equalsIgnoreCase("금")) 
								{
									PlayerGold = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("달러")) 
								{
									PlayerUSD = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("엔")) 
								{
									PlayerJPY = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("유로")) 
								{
									PlayerEUR = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("위안")) 
								{
									PlayerCNY = Double.valueOf(chartChecker[1]);
								}
								
								DirectReader = userInfoReader.readLine();
							} else {
								i++;
							}
						}
						
						if((Double.valueOf(args[3])*goldPrice1)>PlayerCash){
							event.getChannel().sendMessage("잔액이 부족합니다.").queue();
						}
						else
						{
							BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
							
							userInfoWriter.write("닉네임:"+event.getMember().getEffectiveName());
							userInfoWriter.newLine();
							userInfoWriter.write("등록 서버:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
							userInfoWriter.newLine();
							userInfoWriter.write("원:"+Integer.toString((int) (PlayerCash-(Double.valueOf(args[3])*goldPrice1))));
							userInfoWriter.newLine();
							userInfoWriter.write("금:"+Double.toString(PlayerGold+Double.valueOf(args[3])));
							userInfoWriter.newLine();
							userInfoWriter.write("달러:"+PlayerUSD);
							userInfoWriter.newLine();
							userInfoWriter.write("엔:"+PlayerJPY);
							userInfoWriter.newLine();
							userInfoWriter.write("유로:"+PlayerEUR);
							userInfoWriter.newLine();
							userInfoWriter.write("위안:"+PlayerCNY);
							
							userInfoWriter.flush();
							
							event.getChannel().sendMessage(event.getMember().getAsMention()+"님의 거래가 성사되었습니다").queue();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			else if(args[2].equalsIgnoreCase("판매"))
			{
				if(args.length==3) //판매만 적은 경우 
				{
					event.getChannel().sendMessage("판매할 g수를 입력해주셔야 합니다.").queue();
				}
				else 
				{
					int PlayerCash = 0;
					double PlayerGold = 0;
					double PlayerUSD = 0;
					double PlayerCNY = 0;
					double PlayerEUR = 0;
					double PlayerJPY = 0;
					
					String url = "https://finance.naver.com/marketindex/goldDetail.nhn"; //크롤링할 url지정
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
					String[] cig2 = splitig2.split("원");
					double goldPrice2 = Double.valueOf(cig2[0]);
					
					try {
						BufferedReader userInfoReader = Files.newBufferedReader(Info);
						String DirectReader = userInfoReader.readLine();
						String[] chartChecker = new String [2];
						for(int i=0; i<1;) {
							if(DirectReader!=null) {
								chartChecker = DirectReader.split(":");
								if(chartChecker[0].equalsIgnoreCase("원")) 
								{
									PlayerCash = Integer.valueOf(chartChecker[1]);
								} 
								
								else if(chartChecker[0].equalsIgnoreCase("금")) 
								{
									PlayerGold = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("달러")) 
								{
									PlayerUSD = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("엔")) 
								{
									PlayerJPY = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("유로")) 
								{
									PlayerEUR = Double.valueOf(chartChecker[1]);
								}
								else if(chartChecker[0].equalsIgnoreCase("위안")) 
								{
									PlayerCNY = Double.valueOf(chartChecker[1]);
								}
								DirectReader = userInfoReader.readLine();
							} else {
								i++;
							}
						}
						
						if(Double.valueOf(args[3])>PlayerGold){
							event.getChannel().sendMessage("판매할 금의 양을 확인하세요.").queue();
						}
						else
						{
							BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
							
							userInfoWriter.write("닉네임:"+event.getMember().getEffectiveName());
							userInfoWriter.newLine();
							userInfoWriter.write("등록 서버:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
							userInfoWriter.newLine();
							userInfoWriter.write("원:"+Integer.toString((int) (PlayerCash+(Double.valueOf(args[3]))*goldPrice2)));
							userInfoWriter.newLine();
							userInfoWriter.write("금:"+Double.toString(PlayerGold-Double.valueOf(args[3])));
							userInfoWriter.newLine();
							userInfoWriter.write("달러:"+PlayerUSD);
							userInfoWriter.newLine();
							userInfoWriter.write("엔:"+PlayerJPY);
							userInfoWriter.newLine();
							userInfoWriter.write("유로:"+PlayerEUR);
							userInfoWriter.newLine();
							userInfoWriter.write("위안:"+PlayerCNY);
							
							userInfoWriter.flush();
							
							event.getChannel().sendMessage(event.getMember().getAsMention()+"님의 거래가 성사되었습니다").queue();
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
