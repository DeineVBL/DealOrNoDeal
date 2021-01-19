package de.swausb.dealornodeal;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class DealOrNoDeal extends ListenerAdapter {

    private HashMap<Long, GameState> players;

    public DealOrNoDeal() {
        this.players = new HashMap<>();
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        Timer timer = new Timer();
        if (event.getChannel().getName().equalsIgnoreCase("botex")) {
            String message = event.getMessage().getContentRaw();
            if (message.equalsIgnoreCase(".start")) {
                if (players.containsKey(user.getIdLong())) {
                    event.getChannel().sendMessage(new EmbedMessage("Fehler", user.getName(), "Du hast bereits ein aktives Spiel!", null).build()).queue(error -> error.addReaction("❌").queue());
                } else {
                    players.put(user.getIdLong(), new GameState(EState.CHOOSE_LUCK_CHEST));
                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte schreibe zuerst deine Glückszahl in den Chat!", "alle_koffer").build()).queue(luck -> luck.addReaction("☘").queue());
                }
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase(".exit")) {
                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast das Spiel verlassen!", "fraucut").build()).queue(leave -> leave.addReaction("👋🏻").queue());
            }

            try {
                int number = Integer.parseInt(message);

                if (players.containsKey(user.getIdLong())) {
                    GameState gameState = players.get(user.getIdLong());
                    if (gameState.getCurrentState() == EState.CHOOSE_LUCK_CHEST) {
                        if (number > 0 && number < 27) {
                            gameState.setLuckChest(number);
                            gameState.setCurrentState(EState.PICK_CHEST);
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast Koffer " + players.get(user.getIdLong()).getLuckChest() + " als deinen Glückskoffer ausgewählt!", "koffer_" + number).build()).queue(sent -> {
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        sent.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte wähle einen Koffer aus!", "alle_koffer").build()).queue();
                                    }
                                }, 1500);
                            });
                        } else {
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte gib eine Zahl zwischen eins und 26 an!", "DoND").build()).queue();
                        }
                    } else if (gameState.getCurrentState() == EState.PICK_CHEST) {
                        if (number > 0 && number < 27) {
                            if (!gameState.getOpenedChest().contains(number)) {
                                gameState.getOpenedChest().add(number);
                                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast Koffer " + number + " geöffnet!", "fraucut").build()).queue(message2 -> {
                                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Koffer " + number + " beinhaltet `" + new Random().nextInt(1000000) + "`", "DoND").build()).queue();
                                });
                            } else {
                                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Diesen Koffer hast du bereits geöffnet!", "DoND").build()).queue();
                            }
                        } else {
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte gib eine Zahl zwischen eins und 26 an!", "DoND").build()).queue();
                        }
                    }
                }
            } catch (NumberFormatException exception) {

            }
        }
    }
}
