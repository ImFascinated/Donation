package me.fascinated.donation;

import me.fascinated.donation.commands.DonationCommand;
import me.fascinated.donation.util.Config;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.getConsoleSender;

/**
 * Project: Donation
 * Date: Sat 25, May at 13:43
 * Created By: ImFascinated
 */
public class Donation extends JavaPlugin {
    public static Config config;
    public static Donation instance;

    public void onEnable() {
        instance = this;
        config = new Config(this, "config.yml", null);
        config.saveDefaultConfig();
        getCommand("donation").setExecutor(new DonationCommand());
        getConsoleSender().sendMessage("§8§m-----------------------------------------");
        getConsoleSender().sendMessage("§aThis plugin was created by _Fascinated");
        getConsoleSender().sendMessage("");
        getConsoleSender().sendMessage("§cPlugin: §f" + getDescription().getName());
        getConsoleSender().sendMessage("§cVersion: §f" + getDescription().getVersion());
        getConsoleSender().sendMessage("");
        getConsoleSender().sendMessage("§aDiscord: §fImFascinated#4735");
        getConsoleSender().sendMessage("§aGitHub: §fgithub.com/RealFascinated");
        getConsoleSender().sendMessage("§aTwitter: §ftwitter.com/RealFascinated");
        getConsoleSender().sendMessage("§8§m-----------------------------------------");
    }
}
