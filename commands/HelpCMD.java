package emma.commands;

import com.github.johnnyjayjay.discord.commandapi.CommandEvent;
import com.github.johnnyjayjay.discord.commandapi.ICommand;
import emma.stuff.HelpPageEmbed;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

public class HelpCMD implements ICommand {


    @Override
    public void onCommand(CommandEvent e, Member member, TextChannel channel, String[] args) {

        if(e.getAuthor().isBot()) return;
        e.getChannel().sendMessage(new HelpPageEmbed().get().build()).queue();

    }



}
