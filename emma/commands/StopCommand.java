package emma.commands;

import com.github.johnnyjayjay.discord.commandapi.CommandEvent;
import com.github.johnnyjayjay.discord.commandapi.ICommand;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;

import java.util.concurrent.TimeUnit;

public class StopCommand implements ICommand  {

    @Override
    public void onCommand(CommandEvent event, Member member, TextChannel channel, String[] args) {
        if(event.getAuthor().isBot())return;

        for(Permission perms : event.getMember().getPermissions()){
            if(perms == Permission.ADMINISTRATOR || perms == Permission.KICK_MEMBERS || event.getAuthor().getId().equalsIgnoreCase("209413133020823552")){
                event.getChannel().sendMessage("Ich...Ich will noch nicht gehen, aber nichts hält mich noch hier....").queue();
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }
    }
}
