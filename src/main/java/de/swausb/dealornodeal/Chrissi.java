package de.swausb.dealornodeal;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Chrissi extends ListenerAdapter{

	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel txtChannel = event.getChannel();
		User user = event.getAuthor();
		Message message = event.getMessage();
	}
}



