package de.swausb.dealornodeal;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class DealOrNoDeal extends ListenerAdapter {

    private HashMap<Long, GameState> players;

    public DealOrNoDeal() {
        this.players = new HashMap<>();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        if (event.getChannel().getName().equalsIgnoreCase("botex")){
            if (event.getMessage().getContentRaw().equalsIgnoreCase(".start")) {
                if (players.containsKey(user.getIdLong())) {
                    event.getChannel().sendMessage(new EmbedMessage("Fehler", user.getName(), "Du hast bereits ein aktives Spiel!", null).build()).queue();
                } else {
                    players.put(user.getIdLong(), new GameState(EState.CHOOSE_LUCK_CHEST, 0, 0));
                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte schreibe zuerst deine Glückszahl in den Chat!", "DoND").build()).queue();
                }
            }
        }
    }
}
