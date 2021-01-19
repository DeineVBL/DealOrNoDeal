package de.swausb.dealornodeal;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class EmbedMessage {
    private String title;
    private String description;
    private String imagename;
    private MessageEmbed.Field[] fields;

    public EmbedMessage(String title, String description, String imagename, MessageEmbed.Field... fields) {
        this.title = title;
        this.description = description;
        this.imagename = imagename;
        this.fields = fields;
    }

    public MessageEmbed build(){
        EmbedBuilder builder = new EmbedBuilder().setAuthor("DealOrNoDeal").setDescription(description).setTitle(title).setColor(Color.orange).setFooter("DoND Bot written by @swausb");

        if (imagename != null)
            builder.setImage("https://github.com/swausb/DealOrNoDeal/blob/master/images/" + imagename + ".png?raw=true&size=1");

        if (fields != null && fields.length > 0) {
            for (MessageEmbed.Field field : fields) {
                builder.addField(field);
            }
        }

        return builder.build();
    }
}
