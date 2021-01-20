package de.swausb.dealornodeal;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
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
                if (players.containsKey(user.getIdLong())) {
                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast das Spiel verlassen!", "fraucut").build()).queue(leave -> leave.addReaction("👋🏻").queue());
                } else {
                    event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Du hast noch kein Spiel gestartet!", "DoND").build()).queue(exitMessage -> {
                        exitMessage.addReaction("❌").queue();
                    });
                }
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
                                }, 2000);
                            });
                        } else {
                            event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Bitte gib eine Zahl zwischen eins und 26 an!", "DoND").build()).queue();
                        }
                    } else if (game.getCurrentState() == EState.PICK_CHEST) {
                        if (number > 0 && number < 27) {
                            if (!game.getOpenedChest().contains(number)) {
                                int money = game.getRandomMoney();
                                game.getOpenedChest().add(number);
                                game.getChestLoot().put(number, money);
                                game.getAvailableMoney().remove(game.getAvailableMoney().indexOf(money));

                                event.getChannel().sendMessage(new EmbedMessage("DealOrNoDeal", user.getName(), "Ich öffne Koffer " + number, "koffer_" + number).build()).queue(edit -> {
                                    timer.schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            EmbedBuilder builder = new EmbedMessage("**$" + new DecimalFormat("###,###,###").format(money) + " hast du gezogen!**", user.getName(),"", "frau_mit_koffer_" + money).raw();
                                            for (int i = 1; i < 27; i++) {
                                                if (game.getChestLoot().containsKey(i)) {
                                                    int storedmoney = game.getChestLoot().get(i);
                                                    if (storedmoney == money){
                                                        builder.getFields().add(new MessageEmbed.Field("Koffer " + i, "» __$" + new DecimalFormat("###,###,###").format(game.getChestLoot().get(i)) + "__", true));
                                                    }
                                                    else {
                                                        builder.getFields().add(new MessageEmbed.Field("Koffer " + i, "» `$" + new DecimalFormat("###,###,###").format(game.getChestLoot().get(i)) + "`", true));
                                                    }
                                                } else {
                                                    builder.getFields().add(new MessageEmbed.Field("Koffer " + i, "» `x`", true));
                                                }
                                            }
                                            edit.editMessage(builder.build()).queue();
                                        }
                                    }, TimeUnit.SECONDS.toMillis(2));
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