package User;

import java.io.BufferedReader;
import java.io.IOException;
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

public class InfoLoader {
	public static void Report(String[] args, Path Info, GuildMessageReceivedEvent event, Member member) {
		double PlayerCash = 0;
		double PlayerGold = 0;
		double PlayerUSD = 0;
		double PlayerCNY = 0;
		double PlayerEUR = 0;
		double PlayerJPY = 0;
		double PlayerAllCash = 0;
	
		boolean pData[] = new boolean[5];

		
		
		try {
			BufferedReader userInfoReader = Files.newBufferedReader(Info);
			String DirectReader = userInfoReader.readLine();
			String[] chartChecker = new String [5];
			for(int i=0; i<1;) 
			
			{
				
				if(DirectReader!=null) 
				{
					chartChecker = DirectReader.split(":");
					
					
					if(chartChecker[0].equalsIgnoreCase("원")) 
					{
						PlayerCash = Double.valueOf(chartChecker[1]);
					} 
					else if(chartChecker[0].equalsIgnoreCase("금")) 
					{
						PlayerGold = Double.valueOf(chartChecker[1]);
						pData[0] = true;
					}
					else if(chartChecker[0].equalsIgnoreCase("달러")) 
					{
						PlayerUSD = Double.valueOf(chartChecker[1]);
						pData[1] = true;
					}
					else if(chartChecker[0].equalsIgnoreCase("엔")) 
					{
						PlayerJPY = Double.valueOf(chartChecker[1]);
						pData[2] = true;
					}
					else if(chartChecker[0].equalsIgnoreCase("유로")) 
					{
						PlayerEUR = Double.valueOf(chartChecker[1]);
						pData[3] = true;
					}
					else if(chartChecker[0].equalsIgnoreCase("위안")) 
					{
						PlayerCNY = Double.valueOf(chartChecker[1]);
						pData[4] = true;
					}
					
					DirectReader = userInfoReader.readLine();
				} 
				
				else 
				{
					i++;
				}

			}
			
			PlayerAllCash = PlayerCash;
			
			
			String PlayerInfo = new String();
			if (PlayerCash!=0) 
			{
				PlayerInfo = PlayerInfo+"\n"+Integer.toString((int) PlayerCash)+" 원";
			}
			
			if (PlayerGold!=0)
			{
				PlayerInfo = PlayerInfo+"\n"+Double.toString(PlayerGold)+" g";
			}

			if (PlayerUSD!=0)
			{
				PlayerInfo = PlayerInfo+"\n"+Double.toString(PlayerUSD)+" $(달러)";
			}
			
			if (PlayerJPY!=0)
			{
				PlayerInfo = PlayerInfo+"\n"+Double.toString(PlayerJPY)+" ¥(엔)";
			}
			 
			if (PlayerEUR!=0)
			{
				PlayerInfo = PlayerInfo+"\n"+Double.toString(PlayerEUR)+" €(유로)";
			}
			
			if (PlayerCNY!=0)
			{
				PlayerInfo = PlayerInfo+"\n"+Double.toString(PlayerCNY)+" ¥(위안)";
			}
			
			String billType[] = {"Gold","USD","JPY","EUR","CNY"};
			for(int j=0; j<5; j++) 
			{
				
				if(pData[j]==true)
				{
					String url = new String();
					if(j==0) { //금
						url = "https://finance.naver.com/marketindex/goldDetail.nhn";
					}
					else 
					{	//통화
						url = "https://finance.naver.com/marketindex/exchangeDetail.nhn?marketindexCd=FX_"+billType[j]+"KRW_SHB";
					}
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

					if(j==0) {
						bill.next();
					}
					else {
						bill.next();
						bill.next();
						bill.next();
					}
					
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
						if(j==0) 
						{
							String[] cig = billB.split("원");
							billB = cig[0];
						}
					}
					//환율값
					Double billSellPrice = Double.valueOf(billB);
					//환율값
					
					if(j==2) {
						billSellPrice = billSellPrice/100;
					}
					
					
					
					if(billType[j].equalsIgnoreCase("Gold")) {
						PlayerAllCash = PlayerAllCash+(billSellPrice*PlayerGold);
					}
					else if(billType[j].equalsIgnoreCase("USD")) {
						PlayerAllCash = PlayerAllCash+(billSellPrice*PlayerUSD);
					}
					else if(billType[j].equalsIgnoreCase("JPY")) {
						PlayerAllCash = PlayerAllCash+(billSellPrice*PlayerJPY);
					}
					else if(billType[j].equalsIgnoreCase("EUR")) {
						PlayerAllCash = PlayerAllCash+(billSellPrice*PlayerEUR);
					}
					else if(billType[j].equalsIgnoreCase("CNY")) {
						PlayerAllCash = PlayerAllCash+(billSellPrice*PlayerCNY);
					}
					
				}
			}
			EmbedBuilder Infoload = new EmbedBuilder();
			Infoload.setTitle(member.getEffectiveName()+"님의 총 자산 : "+(int)PlayerAllCash+" 원");
			Infoload.setDescription(PlayerInfo);
			Infoload.addField("초기화 방법","개발중입니다.", false);
			Infoload.setColor(0x00FFBB);
			event.getChannel().sendMessage(Infoload.build()).queue();
			Infoload.clear();
			} 
		
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
