package de.swausb.dealornodeal;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class DealOrNoDeal extends ListenerAdapter {

    private HashMap<Long, Game> players;

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
                    players.put(user.getIdLong(), new Game(EState.CHOOSE_LUCK_CHEST));
                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte schreibe zuerst deine Glückszahl in den Chat!", "alle_koffer").build()).queue(luck -> luck.addReaction("☘").queue());
                }
            }
            if (event.getMessage().getContentRaw().equalsIgnoreCase(".exit")) {
                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast das Spiel verlassen!", "fraucut").build()).queue(leave -> leave.addReaction("👋🏻").queue());
            }

            try {
                int number = Integer.parseInt(message);

                if (players.containsKey(user.getIdLong())) {
                    Game game = players.get(user.getIdLong());
                    if (game.getCurrentState() == EState.CHOOSE_LUCK_CHEST) {
                        if (number > 0 && number < 27) {
                            game.setLuckChest(number);
                            game.setCurrentState(EState.PICK_CHEST);
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast Koffer " + players.get(user.getIdLong()).getLuckChest() + " als deinen Glückskoffer ausgewählt!", "koffer_" + number).build()).queue(sent -> {
                                timer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        sent.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte wähle einen Koffer aus!", "alle_koffer").build()).queue();
                                    }
                                }, 3500);
                            });
                        } else {
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte gib eine Zahl zwischen eins und 26 an!", "DoND").build()).queue();
                        }
                    } else if (game.getCurrentState() == EState.PICK_CHEST) {
                        if (number > 0 && number < 27) {
                            if (!game.getOpenedChest().contains(number)) {
                                int money = game.getRandomMoney();
                                game.getOpenedChest().add(number);
                                game.getAvailableMoney().remove(game.getAvailableMoney().indexOf(money));

                                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Ich öffne Koffer " + number, "koffer_" + number).build()).queue(edit -> {
                                    timer.schedule(new TimerTask() {
                                        int call = 1;
                                        @Override
                                        public void run() {

                                            switch (call) {
                                                case 1:
                                                    edit.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Ich öffne Koffer " + number + ".", "koffer_" + number).build()).queue();
                                                    break;
                                                case 2:
                                                    edit.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Ich öffne Koffer " + number + "..", "koffer_" + number).build()).queue();
                                                    break;
                                                case 3:
                                                    edit.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Ich öffne Koffer " + number + "...", "koffer_" + number).build()).queue();
                                                    break;
                                            }
                                            call++;
                                        }
                                    }, 1000, 3);
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            edit.editMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Der Koffer " + number + " beinhaltet *$" + new DecimalFormat("###,###,###").format(money) + "*", "frau_mit_koffer_" + money).build()).queue();
                                        }
                                    }, TimeUnit.SECONDS.toMillis(4));
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
