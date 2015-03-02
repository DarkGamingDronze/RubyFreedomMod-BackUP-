package me.StevenLawson.TotalFreedomMod.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//use it twice, this is for when your mom looks at your mc -looperXD
@CommandPermissions(level = AdminLevel.OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Clear your own chat", usage = "/<command>", aliases = "pcc")
public class Command_clearpersonchat extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (senderIsConsole)
        {
            playerMsg("You must be in-game to use this command.");
        }

        Player player = sender_p;
        for(int i = 0; i <= 20; i++)
        {
            playerMsg("");
        }
        playerMsg("Chat cleared!", ChatColor.RED);
        
        return true;
    }
}
