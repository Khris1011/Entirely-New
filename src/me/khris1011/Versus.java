package me.khris1011;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.block.Action;

public class Versus extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public void teleportInWord(Player player, int x, int y, int z) {
		player.teleport(new Location(player.getWorld(), x, y, z));
	}
	
	private void openGUI(Player player) {
		Inventory inv = Bukkit.createInventory(null,  9, ChatColor.BLUE + "2v2 Games");
		
		//Item Stack List?
		ItemStack Versus = new ItemStack(Material.IRON_SWORD);
		ItemMeta VersusMeta = Versus.getItemMeta();
		ItemStack Hub = new ItemStack(Material.DIAMOND);
		ItemMeta HubMeta = Hub.getItemMeta();
		//Item Stack List?
		
		//Start Of Item Stacks
		VersusMeta.setDisplayName(ChatColor.BLUE + "2v2 PVP");
		Versus.setItemMeta(VersusMeta);
		
		HubMeta.setDisplayName(ChatColor.GREEN + "Hub!");
		Hub.setItemMeta(HubMeta);
		//End Of Item Stacks
		
		//Inventory Menu 0-8 
		inv.setItem(3, Versus);
		inv.setItem(8, Hub);
		//Inventory Menu 0-8
		
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(ChatColor.stripColor(event.getInventory().getName())
				.equalsIgnoreCase("2v2 Games"))
			return;
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if(event.getCurrentItem()==null || event.getCurrentItem().getType()==Material.AIR||!event.getCurrentItem().hasItemMeta()){
			player.closeInventory();
			return;
		}
		
		switch(event.getCurrentItem().getType()) {
		case IRON_SWORD:
			//Erum Halp?
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported To %s2v2's%s!",
					ChatColor.GOLD, ChatColor.BLUE, ChatColor.GOLD));
			break;
		case DIAMOND:
			//Erum Halp?
			player.closeInventory();
			player.sendMessage(String.format("%sTeleported To The %sHub's%s!",
					ChatColor.GOLD, ChatColor.BLUE, ChatColor.GOLD));
			break;
		default:
			player.closeInventory();
			player.sendMessage(String.format("%sInvaled Item!",
					ChatColor.RED));
			break;
		}
	}
	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().getInventory()
				.addItem(new ItemStack(Material.COMPASS));
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Action a = event.getAction();
		ItemStack is = event.getItem();
		
		if(a == Action.PHYSICAL || is == null || is.getType()==Material.AIR)
			return;
		
		if(is.getType() == Material.COMPASS)
			openGUI(event.getPlayer());
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("GUI") && sender instanceof Player) {
			
			Player player = (Player) sender;
			
			player.sendMessage("Work and Progress");
			
		}
		
		return false;
		
	}
	
}
