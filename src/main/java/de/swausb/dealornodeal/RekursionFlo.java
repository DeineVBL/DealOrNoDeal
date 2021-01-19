package de.swausb.dealornodeal;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RekursionFlo extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if (event.getChannel().getName().equalsIgnoreCase("privat")) {
			if(event.getMessage().getContentRaw().equalsIgnoreCase("florek")) {
				Member Tim = Start.jda.getGuilds().get(0).getMemberById("290584719647571978");
				while (true) {
					event.getChannel().sendMessage(new EmbedBuilder().setDescription("<@290584719647571978>").build()).queue();
				}
			}
		}
	}
	
}