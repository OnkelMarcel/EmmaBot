package emma.commands;

import com.github.johnnyjayjay.discord.commandapi.CommandEvent;
import com.github.johnnyjayjay.discord.commandapi.ICommand;
import emma.stuff.RoleManager;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.List;

public class AddRoleCMD implements ICommand  {

    @Override
    public void onCommand(CommandEvent e, Member member, TextChannel channel, String[] args) {
        RoleManager manager = new RoleManager(e.getGuild(), e.getAuthor(), e.getMember(), args, e.getChannel());
        manager.addRole();

    }

}
