package emma.stuff;

import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class HelpPageEmbed {

    public EmbedBuilder get(){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Help page"); //ON THE VERY TOP LIKE A TITLE OF A TITLE

        eb.setDescription("All commands:"); //"FIELD" UNDER THE TITLE
        eb.addField("Admin", "Commands that only admins/stuff can use:", false);
        eb.addField("stop", "Stops the bot", false); //STOP COMMAND
        eb.addField("Everybody", "Commands that anyone can use:", false);
        eb.addField("help", "Shows this info", false); //HELP COMMAND
        eb.addField("removerole <rolename>", "Removes a role of you", false); //REMOVEROLE COMMAND
        eb.addField("getrole <rolename>", "Gives you a role", false); //GETROLE COMMAND

        eb.setFooter("Coded by OnkelMarcel", null); //CREDITS

        eb.setColor(Color.RED);
        return eb;
    }

}
