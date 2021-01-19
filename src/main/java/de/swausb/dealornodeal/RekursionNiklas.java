package de.swausb.dealornodeal;

import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RekursionNiklas extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if (event.getChannel().getName().equalsIgnoreCase("privat")) {
			if(event.getMessage().getContentRaw().equalsIgnoreCase("nwausbrek")) {
				Member nwausb = Start.jda.getGuilds().get(0).getMemberById("404301583027798032");
				while (true) {
					event.getChannel().sendMessage(new MessageBuilder("<@404301583027798032>").build()).queue();
				}
			}
		}
	}
	
}
