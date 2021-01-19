package de.swausb.dealornodeal;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingPong extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        if (event.getAuthor().isBot()) return;
        // We don't want to respond to other bot accounts, including ourself
        Message message = event.getMessage();
        String content = message.getContentRaw();
        // getContentRaw() is an atomic getter
        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
        if (content.equals("ping"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.equals("pong"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Ping!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.equals("nwausb"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("ist dumm!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
        if (content.equals("Entwickler"))
        {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("ist nh Legende! :joy:  ").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
        }
    }
}