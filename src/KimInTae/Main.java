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
		
		jda = new JDABuilder(AccountType.BOT).setToken("��ū��").build();	// ���ڵ� ������ �������ִ� �ٸ�
		jda.getPresence().setStatus(OnlineStatus.ONLINE);						// �¶��� �������� ����
		jda.getPresence().setActivity(Activity.playing("���غп��� �������ϴ� ��"));		// Ȱ�� ���¸� ��Ÿ��
		
		jda.addEventListener(new Commands());		
		// �̺�Ʈ ������. �� ���� ä���� �ִٸ� �� �ڵ尡 �����.
		
	}
	
	public static void setStatus(String guildName, String dance) {
		jda.getPresence().setActivity(Activity.playing(guildName+" ���� "+dance));
	}
}
