package me.fascinated.donation.commands;

import me.fascinated.donation.Donation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Project: Donation
 * Date: Sat 25, May at 13:45
 * Created By: ImFascinated
 */
public class DonationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        Player player = (Player) s;

        if (player.hasPermission("donation.command.admin")) {
            if (args.length < 1) {
                help(player);
            } else {
                if (args[0].equalsIgnoreCase("reload")) {
                    player.sendMessage("§e§lNotice §8▪ §fThe configuration is reloading...");
                    try {
                        player.sendMessage("§a§lSuccess §8▪ §fThe configuration has reloaded!");
                        Donation.config.reloadConfig();
                    } catch (Exception ex) {
                        player.sendMessage("§c§lError §8▪ §fThe configuration has failed to reload!");
                        ex.printStackTrace();
                    }
                } else {
                    player.playSound(player.getLocation(), Sound.valueOf(Donation.config.getConfiguration().getString("SOUND")), 1L, 1L);
                    for (String msg : Donation.config.getConfiguration().getStringList("DONATION-MESSAGE")) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg.replace("$player", args[0])));
                    }
                }
            }
        }
        return true;
    }

    public void help(CommandSender sender) {
        sender.sendMessage("§8§m---------------------------------------");
        sender.sendMessage("§a§lDonation §8┃ §fv" + Donation.instance.getDescription().getVersion());
        sender.sendMessage("");
        sender.sendMessage("§8* §a/donation reload §8- §fReloads the config!");
        sender.sendMessage("§8* §a/donation <name> §8- §fSends the donation msg!");
        sender.sendMessage("§8§m---------------------------------------");
    }
}
