package Ranking;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Updater {

	
	
	public static void Update()
	{
		String rankingList = "C:\\Users\\ninin\\Desktop\\Resources\\Discord Bot\\Ranking\\";
		Path rankingTXTPath = Paths.get(rankingList+"Ranking.txt");
		int users = 0;
		
		try {
			BufferedReader valueChecker = Files.newBufferedReader(rankingTXTPath);
			String rankData = new String();

			for(int i=0; i<1;) {
				rankData = valueChecker.readLine();
				if(rankData!=null) 
				{
					users++;
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userDetailData[][] = new String[users][users];
		
		try {
			BufferedReader rankChecker = Files.newBufferedReader(rankingTXTPath);
			String rankData = new String();

			for(int i=0; i<users; i++) {
				rankData = rankChecker.readLine();
				if(rankData!=null) 
				{
					userDetailData[i][0] = rankData.split(":")[0];
					userDetailData[i][1] = rankData.split(":")[1];
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void Writer()
	{
		
	}
}
