package de.swausb.dealornodeal;

import de.swausb.dealornodeal.listener.GuildMessageReceivedListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class DealOrNoDeal extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getChannel().getName().equalsIgnoreCase("botex")){
            if (event.getMessage().getContentRaw().equalsIgnoreCase(".start")) {
                event.getChannel().sendMessage(new EmbedBuilder().setAuthor("DealOrNoDeal").setTitle("DealOrNoDeal").setColor(Color.orange).setFooter("Bot written by @swausb").setDescription("Bitte wähle zuerst deine Glückszahl aus!").setImage("https://github.com/swausb/DealOrNoDeal/blob/master/images/fraucut.png?raw=true").build()).queue();
            }
        }
    }
}
