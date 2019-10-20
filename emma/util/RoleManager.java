package emma.util;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.managers.GuildController;

import java.util.List;

public class RoleManager {

    private Guild guild;
    private User user;
    private Member member;
    private String[] args;
    private TextChannel channel;

    public RoleManager(Guild guild, User user, Member member, String[] args, TextChannel channel){
        this.guild = guild;
        this.user = user;
        this.member = member;
        this.args = args;
        this.channel = channel;
    }

    public void getRoles(){

    }

    public void removeRole(){

        GuildController controller = guild.getController();

        if(member.getRoles().size() != 0) {
            for (Role role : member.getRoles()) {
                if (role.getName().equalsIgnoreCase(args[0])) {
                    if(guild.getRoleById(role.getId()) != null) {
                        controller.removeRolesFromMember(member, guild.getRolesByName(args[0], true)).queue();
                        channel.sendMessage("Die Rolle *" + args[0] + "* wurde dem User " + user.getAsMention() + " entfernt.").queue();
                        break;
                    }
                }
            }
        } else {
            channel.sendMessage("Du besitzt keine Rollen " + user.getAsMention()).queue();
        }

    }

    public void addRole(){
        GuildController controller = guild.getController();

        if (user.isBot())
            return;

        if (args.length > 0 && !guild.getRolesByName(args[0], true).isEmpty()) {
            List<Role> role = guild.getRolesByName(args[0], true);
            List<Role> userRoles = member.getRoles();

            if (userRoles.size() >= 0 && !userRoles.contains(role.get(0))) {

                controller.addRolesToMember(member, role.get(0)).queue();
                channel
                        .sendMessage(user.getAsMention() + " wurde die Rolle " + args[0] + " hinzugefügt!")
                        .queue();
            } else {
                channel.sendMessage("Du hast diese Rolle bereits!").queue();
            }
        } else if(args.length <= 0){
            channel.sendMessage("Du musst eine Rolle angeben!").queue();
        } else if(guild.getRolesByName(args[0], true).isEmpty()) {
            channel.sendMessage("Diese Rolle existiert nicht!").queue();
        }

    }


}
