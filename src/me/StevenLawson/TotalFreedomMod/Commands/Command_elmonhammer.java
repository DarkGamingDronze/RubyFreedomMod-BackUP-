
package me.StevenLawson.TotalFreedomMod.Commands;




import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_Ban;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
//fixed by looperXD
//remove AdminLevel.ELMON that dont exist !
@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "For the bad Superadmins", usage = "/<command> <playername>")
public class Command_elmonhammer extends TFM_Command
{

@Override

public boolean run(final CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)

    {
    //locked the command so it only opens to Elomon
           if (!sender.getName().equalsIgnoreCase("Elmon11"))
        {
            playerMsg("Who the heck do you think you are?");//What it will say if others try this command
            return true;
        }
        if (args.length != 1) {
      return false;
    }
    final Player player = getPlayer(args[0]);
    if (player == null)
    {
      sender.sendMessage(TotalFreedomMod.PLAYER_NOT_FOUND);
      return true;
    }




        TFM_Util.bcastMsg(sender.getName() + " - You Have Broken The Rules" + player.getName() + "!!", ChatColor.RED);
        TFM_Util.bcastMsg(player.getName() + " will be completely Elmonicly Smashed!", ChatColor.RED);
        TFM_Util.bcastMsg(sender.getName() + " - You Will be Elmonicly Elmoned!", ChatColor.RED);
        TFM_Util.bcastMsg(player.getName() + " - I am a Noob Admin/OP! What did I do?", ChatColor.RED);
        TFM_Util.bcastMsg(sender.getName() + " - Haha! You are so getting a ban!", ChatColor.RED);
        TFM_Util.bcastMsg(sender.getName() + " - dont be like " + player.getName() + "!", ChatColor.DARK_RED);      
        TFM_Util.bcastMsg(sender.getName() + " Or You Will Get Hit by the ElmonHammer!", ChatColor.RED);




        final String ip = player.getAddress().getAddress().getHostAddress().trim();




        // remove from superadmin


        if (TFM_AdminList.isSuperAdmin(player))


        {


            TFM_Util.adminAction(sender.getName(), "Removing " + player.getName() + " from the superadmin list.", true);


            TFM_AdminList.removeSuperadmin(player);


        }




        // remove from whitelist


        player.setWhitelisted(false);




        // deop


        player.setOp(false);




        // ban IPs


        for (String playerIp : TFM_PlayerList.getEntry(player).getIps())


        {


            TFM_BanManager.addIpBan(new TFM_Ban(playerIp, player.getName()));


        }




        // ban uuid


        TFM_BanManager.addUuidBan(player);




        // set gamemode to survival


        player.setGameMode(GameMode.SURVIVAL);




        // clear inventory


        player.closeInventory();


        player.getInventory().clear();




        // ignite player


        player.setFireTicks(10000);




        // generate explosion


        player.getWorld().createExplosion(player.getLocation(), 4F);




        // Shoot the player in the sky


        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 20, 0)));




        new BukkitRunnable()


        {


            @Override


            public void run()


            {


                // strike lightning


                player.getWorld().strikeLightning(player.getLocation());




                // kill (if not done already)


                player.setHealth(0.0);


            }


        }.runTaskLater(plugin, 2L * 20L);




        new BukkitRunnable()


        {


            @Override


            public void run()


            {


                // message


                TFM_Util.adminAction(sender.getName(), "Elmonicely Banned " + player.getName() + ", IP: " + ip, true);




                // generate explosion


                player.getWorld().createExplosion(player.getLocation(), 4F);




                // kick player


                player.kickPlayer(ChatColor.RED + "YOU GOT ELMONICELY BANNED! NEXT TIME, Don't be Bad!!");


            }


        }.runTaskLater(plugin, 3L * 20L);




        return true;


    }


}

