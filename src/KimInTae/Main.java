package KimInTae;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class Main {
	public static JDA jda;
	

	public static void main(String[] args) throws LoginException {
		
		jda = new JDABuilder(AccountType.BOT).setToken("토큰값").build();	// 디스코드 서버와 연결해주는 다리
		jda.getPresence().setStatus(OnlineStatus.ONLINE);						// 온라인 상태임을 설정
		jda.getPresence().setActivity(Activity.playing("팀해분에게 개조당하는 중"));		// 활동 상태를 나타냄
		
		jda.addEventListener(new Commands());		
		// 이벤트 리스너. 즉 무언가 채팅이 있다면 이 코드가 실행됨.
		
	}
	
	public static void setStatus(String guildName, String dance) {
		jda.getPresence().setActivity(Activity.playing(guildName+" 에서 "+dance));
	}
}
