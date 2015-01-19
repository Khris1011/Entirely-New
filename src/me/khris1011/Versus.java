package me.khris1011;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Versus extends JavaPlugin {

	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("Versus") && sender instanceof Player) {
			
			Player player = (Player) sender;
			
			player.sendMessage("Work and Progress");
			
		}
		
		return false;
		
	}
	
}
