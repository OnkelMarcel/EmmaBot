package emma.core;

import com.github.johnnyjayjay.discord.commandapi.CommandSettings;
import emma.commands.*;
import emma.database.MyDB;
import emma.handler.FileHandler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;

import javax.security.auth.login.LoginException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private JDA jda;
    private FileHandler fh = new FileHandler("PATH/TO/CONFIG");
    private int members = 0;
    private boolean started = false;

    public static void main(String[] args) {

        new Main().init();

    }


    public void init() {

        JDABuilder builder = new JDABuilder(AccountType.BOT);

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
        CommandSettings settings = new CommandSettings(String.valueOf(fh.get("botdata", "prefix", String.class)), jda, true);
        settings.put(new HelpCommand(), "help");
        settings.put(new AddRoleCommand(), "addRole", "addrole", "ar", "AddRole", "ADDROLE");
        settings.put(new StopCommand(), "stop", "STOP", "Stop", "Stopp", "stopp");
        settings.put(new GoogleCommand(), "lmgtfy", "LMGTFY", "lMgTfY", "Lmgtfy", "LmGtFy");
        settings.put(new RemoveRoleCommand(), "removeRole", "removerole", "rr", "RemoveRole", "Removerole", "REMOVEROLE").activate();
    }

    private void registerEvents() {
    }

    private void timer() {
        if (!started) {
            members = 0;
            for (Guild g : jda.getGuilds()) {
                members += g.getMembers().size();
                System.out.println("Debug");
            }
            jda.getPresence().setGame(Game.playing(members + " members"));
            started = true;
            timer();
        } else {
            Executors.newScheduledThreadPool(1).schedule(() -> {
                members = 0;
                for (Guild g : jda.getGuilds()) {
                    members += g.getMembers().size();
                    System.out.println("Debug");
                }
                jda.getPresence().setGame(Game.playing(members + " members"));
            }, 45, TimeUnit.MINUTES);

        }
    }
}
