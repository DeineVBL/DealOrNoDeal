package de.swausb.dealornodeal.listener;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import de.swausb.dealornodeal.Fenster;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildMessageReceivedListener extends ListenerAdapter {
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel txtChannel = event.getChannel();
		User user = event.getAuthor();
		Message message = event.getMessage();
		
		if (txtChannel.getName().equalsIgnoreCase("privat")) {
			
				if (message.getContentRaw().equalsIgnoreCase("s.news")) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Fenster window = new Fenster();
								window.frmSimonsTollesFenster.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} else if (message.getContentRaw().equalsIgnoreCase("s.test")) {
					txtChannel.sendMessage(new EmbedBuilder().setAuthor("Author").setTitle("Titel").setFooter("Footer").setDescription("Description").setThumbnail(user.getAvatarUrl()).build()).queue();
					
				}
			
		}
	}
}
