package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.TFM_RollbackManager;
import me.StevenLawson.TotalFreedomMod.TFM_Util;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import me.StevenLawson.TotalFreedomMod.Bridge.TFM_WorldEditBridge;
import net.minecraft.util.org.apache.commons.lang3.ArrayUtils;
import net.minecraft.util.org.apache.commons.lang3.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//THE ONLY USAGE OF THIS COMMAND IS TO UNDO WORLD EDIT OR MINOR GRIEF DONT USE IT LIKE SMITE- looperXD 
@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Rekt a noob", usage = "/<command> <playername>")
public class Command_rekt extends TFM_Command{

	@Override
	public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole) {
		  if (args.length == 0)
	        {
	            return false;
	        }

	        final Player player = getPlayer(args[0]);

	        if (player == null)
	        {
	            playerMsg(TotalFreedomMod.PLAYER_NOT_FOUND, ChatColor.RED);
	            return true;
	        }
	        String reason = null;
	        if (args.length >= 2)
	        {
	            reason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
	        }
            TFM_Util.adminAction(sender.getName(), "Rekting " + player.getName(), true);
	        TFM_Util.bcastMsg(player.getName() + " has been rekted.", ChatColor.RED);
            TFM_Util.adminAction(sender.getName(), "Adding " + player.getName() + " to the noob list.", true);

	        // Undo WorldEdits:
	        try
	        {
	            TFM_WorldEditBridge.undo(player, 15);
	        }
	        catch (NoClassDefFoundError ex)
	        {
	        }

	        // rollback
	        TFM_RollbackManager.rollback(player.getName());

	        // deop
	        player.setOp(false);

	        // set gamemode to survival:
	        player.setGameMode(GameMode.SURVIVAL);

	        // clear inventory:
	        player.getInventory().clear();

	        // strike with lightning effect:
	        final Location targetPos = player.getLocation();
	        for (int x = -1; x <= 1; x++)
	        {
	            for (int z = -1; z <= 1; z++)
	            {
	                final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x, targetPos.getBlockY(), targetPos.getBlockZ() + z);
	                targetPos.getWorld().strikeLightning(strike_pos);
	            }
	        }
	        //kill them.
	        player.setHealth(0.0);
               //welcome xD
               	player.sendMessage(ChatColor.RED + "Welcome to noob zone mother fucker");
               //insult them
	        player.sendMessage(ChatColor.RED + "Go learn now to minecraft you noob");
		return true;
	}

}
