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
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Exchange_Rate 
{
	public static void USDStock(GuildMessageReceivedEvent event, String[] args, Member member, Path Info) 
	{
		if(args.length==2) 
		{
			Report("USD", event, member, "달러", "$", "us", 1, Info);
		}
		else
		{
			if(args[2].equalsIgnoreCase("구매"))
			{
				Buy("USD", event, member, args, Info);
			}
			else if(args[2].equalsIgnoreCase("판매"))
			{
				Sell("USD", event, member, args, Info);
			}
		}		
	}
	
	public static void JPYStock(GuildMessageReceivedEvent event, String[] args, Member member, Path Info) 
	{
		if(args.length==2) 
		{
			Report("JPY", event, member, "엔", "¥", "jp", 100, Info);
		}
		else
		{
			if(args[2].equalsIgnoreCase("구매"))
			{
				Buy("JPY", event, member, args, Info);
			}
			else if(args[2].equalsIgnoreCase("판매"))
			{
				Sell("JPY", event, member, args, Info);
			}
		}		
	}
	
	public static void EURStock(GuildMessageReceivedEvent event, String[] args, Member member, Path Info) 
	{
		if(args.length==2) 
		{
			Report("EUR", event, member, "유로", "€", "eu", 1, Info);
		}
		else
		{
			if(args[2].equalsIgnoreCase("구매"))
			{
				Buy("EUR", event, member, args, Info);
			}
			else if(args[2].equalsIgnoreCase("판매"))
			{
				Sell("EUR", event, member, args, Info);
			}
		}		
	}
	
	public static void CNYStock(GuildMessageReceivedEvent event, String[] args, Member member, Path Info) 
	{
		if(args.length==2) 
		{
			Report("CNY", event, member, "위안", "¥", "cn", 1, Info);
		}
		else
		{
			if(args[2].equalsIgnoreCase("구매"))
			{
				Buy("CNY", event, member, args, Info);
			}
			else if(args[2].equalsIgnoreCase("판매"))
			{
				Sell("CNY", event, member, args, Info);
			}
		}		
	}
	
	public static void Report(String Bill, GuildMessageReceivedEvent event, Member member,String lang, String billSymbol, String flag, int unit,Path Info) {
		String url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_"+Bill+"KRW_SHB"; //크롤링할 url지정
		Document doc = null;
		
		try 
		{
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		Elements element = doc.select("table.tbl_exchange");
		Iterator<Element> bill = element.select("td").iterator();

		bill.next();
		bill.next();
		
		String billA = bill.next().text();
		String billB = bill.next().text();
		int billALength = billA.length();
		int billBLength = billA.length();
		boolean ac = false;
		boolean bc = false;
		
		String[] checkerA = billA.split("");
		String[] checkerB = billB.split("");
		
		for(int i=0; i<billALength; i++) 
		{
			if(checkerA[i].equalsIgnoreCase(",")) 
			{
				ac = true;
			}
		}
		
		for(int i=0; i<billBLength; i++) 
		{
			if(checkerB[i].equalsIgnoreCase(",")) 
			{
				bc = true;
			}
		}
		
		if(ac==true) 
		{
			String[] bills = billA.split(",");
			billA = bills[0]+bills[1];
		}
		
		if(bc==true) 
		{
			String[] billt = billB.split(",");
			billB = billt[0]+billt[1];
		}
	
		Double billBuyPrice = Double.valueOf(billA);
		Double billSellPrice = Double.valueOf(billB);
		
		EmbedBuilder billBuilder = new EmbedBuilder();
		billBuilder.setTitle(":flag_"+flag+": "+Bill+"("+billSymbol+") : "+ (billBuyPrice+billSellPrice)/2+"");
		billBuilder.setDescription("사용자:"+ member.getAsMention()+"님\n"
				+ unit+lang+" 구매 시): "+billBuyPrice+"원\n"
				+ unit+lang+" 판매 시): "+billSellPrice+"원\n");
		billBuilder.addField("거래 방법", "앰생투자 "+lang+" 구매(or 판매) 수량("+billSymbol+")", false);
		billBuilder.setImage("https://ssl.pstatic.net/imgfinance/chart/marketindex/area/month/FX_"+Bill+"KRW.png");
		
		event.getChannel().sendMessage(billBuilder.build()).queue();
		billBuilder.clear();
		
		userDetails(event, Bill, Info, billSymbol);
		
	}
	
	public static void Buy (String Bill, GuildMessageReceivedEvent event, Member member, String[] args, Path Info)
	{
		if(args.length==3) //구매만 적은 경우 
		{
			event.getChannel().sendMessage("구매할 수량을 입력해주셔야 합니다.").queue();
		}
		
		else 
		{
			double PlayerCash = 0;
			double PlayerGold = 0;
			double PlayerUSD = 0;
			double PlayerCNY = 0;
			double PlayerEUR = 0;
			double PlayerJPY = 0;
			
			String url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_"+Bill+"KRW_SHB"; //크롤링할 url지정
			Document doc = null;
			
			try 
			{
				doc = Jsoup.connect(url).get();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements element = doc.select("table.tbl_exchange");
			Iterator<Element> bill = element.select("td").iterator();

			bill.next();
			bill.next();
			
			String billA = bill.next().text();
			int billALength = billA.length();
			boolean ac = false;
			
			String[] checkerA = billA.split("");
			
			for(int i=0; i<billALength; i++) 
			{
				if(checkerA[i].equalsIgnoreCase(",")) 
				{
					ac = true;
				}
			}
			
			if(ac==true) 
			{
				String[] bills = billA.split(",");
				billA = bills[0]+bills[1];
			}

			//환율값
			Double billBuyPrice = Double.valueOf(billA);
			//환율값
			
			try {
				BufferedReader userInfoReader = Files.newBufferedReader(Info);
				String DirectReader = userInfoReader.readLine();
				String[] chartChecker = new String [5];
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
				
				if(Bill.equalsIgnoreCase("JPY")) 
				{
					billBuyPrice = billBuyPrice/100;
				}
				
				if((Double.valueOf(args[3])*billBuyPrice)>PlayerCash)
				{
					event.getChannel().sendMessage("잔액이 부족합니다.").queue();
				}
				
				else
				{
					if(Bill.equalsIgnoreCase("USD")) 
					{
						PlayerUSD = PlayerUSD+Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("JPY")) 
					{
						PlayerJPY = PlayerJPY+Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("EUR")) 
					{
						PlayerEUR = PlayerEUR+Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("CNY")) 
					{
						PlayerCNY = PlayerCNY+Double.valueOf(args[3]);
					}
					
					BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
					
					userInfoWriter.write("닉네임:"+event.getMember().getEffectiveName());
					userInfoWriter.newLine();
					userInfoWriter.write("등록 서버:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
					userInfoWriter.newLine();
					userInfoWriter.write("원:"+(int)(PlayerCash-(billBuyPrice*Double.valueOf(args[3]))));
					userInfoWriter.newLine();
					userInfoWriter.write("금:"+PlayerGold);
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
					
					Ranking.Updater.Update();
					
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public static void Sell (String Bill, GuildMessageReceivedEvent event, Member member, String[] args, Path Info)
	{
		if(args.length==3) //판매만 적은 경우 
		{
			event.getChannel().sendMessage("판매할 수량을 입력해주셔야 합니다.").queue();
		}
		
		else 
		{
			int PlayerCash = 0;
			double PlayerGold = 0;
			double PlayerUSD = 0;
			double PlayerCNY = 0;
			double PlayerEUR = 0;
			double PlayerJPY = 0;
			
			String url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_"+Bill+"KRW_SHB"; //크롤링할 url지정
			Document doc = null;
			
			try 
			{
				doc = Jsoup.connect(url).get();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements element = doc.select("table.tbl_exchange");
			Iterator<Element> bill = element.select("td").iterator();

			bill.next();
			bill.next();
			bill.next();
			
			String billB = bill.next().text();
			int billBLength = billB.length();
			boolean bc = false;
			
			String[] checkerB = billB.split("");
			
			for(int i=0; i<billBLength; i++) 
			{
				if(checkerB[i].equalsIgnoreCase(",")) 
				{
					bc = true;
				}
			}
			
			if(bc==true) 
			{
				String[] billt = billB.split(",");
				billB = billt[0]+billt[1];
			}

			//환율값
			Double billSellPrice = Double.valueOf(billB);
			//환율값
			
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
				if(Bill.equalsIgnoreCase("JPY")) 
				{
					billSellPrice = billSellPrice/100;
				}
				
				
				if(Bill.equalsIgnoreCase("USD")&&(Double.valueOf(args[3])>PlayerUSD)) 
				{
					event.getChannel().sendMessage("판매할 수량을 확인해주세요.").queue();
				}
				else if(Bill.equalsIgnoreCase("JPY")&&(Double.valueOf(args[3])>PlayerJPY)) 
				{
					event.getChannel().sendMessage("판매할 수량을 확인해주세요.").queue();
				}
				else if(Bill.equalsIgnoreCase("EUR")&&(Double.valueOf(args[3])>PlayerEUR)) 
				{
					event.getChannel().sendMessage("판매할 수량을 확인해주세요.").queue();
				}
				else if(Bill.equalsIgnoreCase("CNY")&&(Double.valueOf(args[3])>PlayerCNY)) 
				{
					event.getChannel().sendMessage("판매할 수량을 확인해주세요.").queue();
				}
				
				else
				{
					if(Bill.equalsIgnoreCase("USD")) 
					{
						PlayerUSD = PlayerUSD-Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("JPY")) 
					{
						PlayerJPY = PlayerJPY-Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("EUR")) 
					{
						PlayerEUR = PlayerEUR-Double.valueOf(args[3]);
					}
					else if(Bill.equalsIgnoreCase("CNY")) 
					{
						PlayerCNY = PlayerCNY-Double.valueOf(args[3]);
					}
					
					BufferedWriter userInfoWriter = Files.newBufferedWriter(Info, StandardCharsets.UTF_8);
					
					userInfoWriter.write("닉네임:"+event.getMember().getEffectiveName());
					userInfoWriter.newLine();
					userInfoWriter.write("등록 서버:"+event.getChannel().getGuild().getId()+" ("+event.getGuild().getName()+")");
					userInfoWriter.newLine();
					userInfoWriter.write("원:"+(int)(PlayerCash+(int)(billSellPrice*Double.valueOf(args[3]))));
					userInfoWriter.newLine();
					userInfoWriter.write("금:"+PlayerGold);
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
					Ranking.Updater.Update();
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void userDetails(GuildMessageReceivedEvent event,String Bill,Path Info, String billSymbol) 
	{
		double PlayerCash = 0;
		double PlayerGold = 0;
		double PlayerUSD = 0;
		double PlayerCNY = 0;
		double PlayerEUR = 0;
		double PlayerJPY = 0;
		
		String url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_"+Bill+"KRW_SHB"; //크롤링할 url지정
		Document doc = null;
		
		try 
		{
			doc = Jsoup.connect(url).get();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("table.tbl_exchange");
		Iterator<Element> bill = element.select("td").iterator();

		bill.next();
		bill.next();
		
		String billA = bill.next().text();
		String billB = bill.next().text();
		int billALength = billA.length();
		int billBLength = billB.length();
		boolean ac = false;
		boolean bc = false;
		
		String[] checkerA = billA.split("");
		String[] checkerB = billB.split("");
		
		for(int i=0; i<billALength; i++) 
		{
			if(checkerA[i].equalsIgnoreCase(",")) 
			{
				ac = true;
			}
		}
		for(int i=0; i<billBLength; i++) 
		{
			if(checkerB[i].equalsIgnoreCase(",")) 
			{
				bc = true;
			}
		}
		
		if(ac==true) 
		{
			String[] bills = billA.split(",");
			billA = bills[0]+bills[1];
		}
		if(bc==true) 
		{
			String[] billt = billB.split(",");
			billB = billt[0]+billt[1];
		}

		//환율값
		Double billBuyPrice = Double.valueOf(billA);
		Double billSellPrice = Double.valueOf(billB);
		//환율값 환율 구매, 판매 가격 
		
		BufferedReader userInfoReader;
		try {
			userInfoReader = Files.newBufferedReader(Info);
			String DirectReader = userInfoReader.readLine();
			String[] chartChecker = new String [5];
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Double sellValue = (double) 0;
		if(Bill.equalsIgnoreCase("USD")) 
		{
			sellValue = PlayerUSD;
		}
		else if(Bill.equalsIgnoreCase("JPY")) 
		{
			sellValue = PlayerJPY;
		}
		else if(Bill.equalsIgnoreCase("EUR")) 
		{
			sellValue = PlayerEUR;
		}
		else if(Bill.equalsIgnoreCase("CNY")) 
		{
			sellValue = PlayerCNY;
		}
		
		event.getChannel().sendMessageFormat(event.getMember().getAsMention()+"님은 현재 '금'을\n"
				+ "%.1f"+billSymbol
				+ " [약 %.0f원]만큼 구매,\n"
				+ sellValue+billSymbol+"[약 %.0f원]만큼 판매할 수 있습니다.",
				(PlayerCash/billBuyPrice),
				((PlayerCash/billBuyPrice)*billBuyPrice),
				(sellValue*billSellPrice)
				).queue();
		
	}
}
