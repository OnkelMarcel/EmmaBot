package emma.core;

import com.github.johnnyjayjay.discord.commandapi.CommandSettings;
import emma.commands.*;
import emma.database.MyDB;
import emma.handler.FileHandler;
import net.dv8tion.jda.client.entities.Application;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;

import javax.security.auth.login.LoginException;
import java.util.*;

public class Core {

    private JDABuilder builder;
    private JDA jda;
    private CommandSettings settings;
    private FileHandler fh = new FileHandler("PATH/TO/CONFIG");
    private int members = 0;
    private Timer timer = new Timer();
    private TimerTask task = new MyTask();
    private boolean started = false;

    public static void main(String[] args) {

        new Core().init();

    }


    public void init() {

        builder = new JDABuilder(AccountType.BOT);

        builder.setToken(String.valueOf(fh.get("botdata", "token", String.class)));

        try {
            jda = builder.buildBlocking();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        builder.setStatus(OnlineStatus.ONLINE);
        registerCommands();
        registerEvents();

        timer();

    }

    private void registerCommands() {
        settings = new CommandSettings(String.valueOf(fh.get("botdata", "prefix", String.class)), jda, true);
        settings.put(new HelpCMD(), "help");
        settings.put(new AddRoleCMD(), "addRole", "addrole", "ar", "AddRole", "ADDROLE");
        settings.put(new StopCMD(), "stop", "STOP", "Stop", "Stopp", "stopp");
        settings.put(new GoogleCMD(), "lmgtfy", "LMGTFY", "lMgTfY", "Lmgtfy", "LmGtFy");
        settings.put(new RemoveRoleCMD(), "removeRole", "removerole", "rr", "RemoveRole", "Removerole", "REMOVEROLE").activate();
    }

    private void registerEvents() {}
    
    private void timer() {
        if(!started) {
            members = 0;
            for(Guild g : jda.getGuilds()){
                members += g.getMembers().size();
                System.out.println("Debug");
            }
            jda.getPresence().setGame(Game.playing(members + " members"));
            started = true;
            timer();
        } else {
            timer.scheduleAtFixedRate(task, (1000 * 60) * 45, Integer.MAX_VALUE);//EVERY 45 MINUTES
        }
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            members = 0;
            for(Guild g : jda.getGuilds()){
                members += g.getMembers().size();
                System.out.println("Debug");
            }
            jda.getPresence().setGame(Game.playing(members + " members"));
        }
    }

}
