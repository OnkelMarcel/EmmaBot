package emma.stuff;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;

public class UserInfo {

    private User target;
    private EmbedBuilder eb;

    public UserInfo(User target){
        this.target = target;
        eb = new EmbedBuilder();
    }

    public void show(){

    }

}
