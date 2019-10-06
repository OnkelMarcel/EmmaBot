package emma.util;

import net.dv8tion.jda.core.entities.TextChannel;

public class LMGTFY {

    private String[] searchArguments;
    private TextChannel channel;

    public LMGTFY(String[] searchArguments, TextChannel channel){
        this.searchArguments = searchArguments;
        this.channel = channel;
    }

    public void search(){
        if(searchArguments.length == 1){
            switch (searchArguments[0]){
                case "help":
                    channel.sendMessage("Help for the LMGTFY command:\n>help - shows this help\n>credits - shows credits and who it's inspired by").queue();
                    break;
                case "credits":
                    channel.sendMessage("Programmed in JDA by Marcel#6612\nInspired by Vale#5252").queue();
                    break;
            }
        } else {
            if (searchArguments.length == 0) {
                //SEND ERROR MESSAGE - NEED MORE ARGUMENTS
            } else {
                String result = "";
                for (String args : searchArguments) {
                    result += args + "%20";
                }
                channel.sendMessage("Someone who doesn't know how to google, huh? Let me show you how to do it, mate.").queue();
                channel.sendMessage("http://lmgtfy.com/?q=" + result).queue();
            }
        }
    }

}
