package Town;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Info extends ListenerAdapter {
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		event.getMessage().addReaction("1️⃣").queue();
	}
}
