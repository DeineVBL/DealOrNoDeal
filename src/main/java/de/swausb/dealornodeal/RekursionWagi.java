package de.swausb.dealornodeal;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RekursionWagi extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		
		if (event.getChannel().getName().equalsIgnoreCase("quatschen")) {
			if(event.getMessage().getContentRaw().equalsIgnoreCase("nwausbrek")) {
				Member Tim = Start.jda.getGuilds().get(0).getMemberById("404301583027798032");
				while (true) {
					event.getChannel().sendMessage(new EmbedBuilder().setDescription("<@404301583027798032>").build()).queue();
				}
			}
		}
	}
	
}