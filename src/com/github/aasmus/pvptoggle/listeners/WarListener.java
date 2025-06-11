package com.github.aasmus.pvptoggle.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.aasmus.pvptoggle.PvPToggle;
import com.github.aasmus.pvptoggle.utils.Chat;
import com.github.TownyAdvanced.SiegeWar.SiegeWarAPI;
import com.github.TownyAdvanced.SiegeWar.events.SiegeStartEvent;
import com.github.TownyAdvanced.SiegeWar.events.SiegeEndEvent;
import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.Town;

public class WarListener implements Listener {
    
    @EventHandler
    public void onSiegeStart(SiegeStartEvent event) {
        // Get all players from both towns involved in the siege
        Town attackerTown = event.getAttackerTown();
        Town defenderTown = event.getDefenderTown();
        
        // Add all players from both towns to warPlayers map and force PvP on
        for (Player player : TownyAPI.getInstance().getOnlinePlayers(attackerTown)) {
            PvPToggle.instance.warPlayers.put(player.getUniqueId(), true);
            PvPToggle.instance.players.put(player.getUniqueId(), false);
            Chat.send(player, "PVP_WAR_ENABLED");
        }
        
        for (Player player : TownyAPI.getInstance().getOnlinePlayers(defenderTown)) {
            PvPToggle.instance.warPlayers.put(player.getUniqueId(), true);
            PvPToggle.instance.players.put(player.getUniqueId(), false);
            Chat.send(player, "PVP_WAR_ENABLED");
        }
    }
    
    @EventHandler
    public void onSiegeEnd(SiegeEndEvent event) {
        // Get all players from both towns involved in the siege
        Town attackerTown = event.getAttackerTown();
        Town defenderTown = event.getDefenderTown();
        
        // Remove players from warPlayers map and restore their previous PvP state
        for (Player player : TownyAPI.getInstance().getOnlinePlayers(attackerTown)) {
            PvPToggle.instance.warPlayers.remove(player.getUniqueId());
            // Restore default PvP state
            PvPToggle.instance.players.put(player.getUniqueId(), 
                PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF"));
            Chat.send(player, "PVP_WAR_DISABLED");
        }
        
        for (Player player : TownyAPI.getInstance().getOnlinePlayers(defenderTown)) {
            PvPToggle.instance.warPlayers.remove(player.getUniqueId());
            // Restore default PvP state
            PvPToggle.instance.players.put(player.getUniqueId(), 
                PvPToggle.instance.getConfig().getBoolean("SETTINGS.DEFAULT_PVP_OFF"));
            Chat.send(player, "PVP_WAR_DISABLED");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Town playerTown = TownyAPI.getInstance().getTown(player);
        
        if (playerTown != null) {
            // Check if player's town is involved in any active sieges
            if (SiegeWarAPI.isTownInSiege(playerTown)) {
                PvPToggle.instance.warPlayers.put(player.getUniqueId(), true);
                PvPToggle.instance.players.put(player.getUniqueId(), false);
                Chat.send(player, "PVP_WAR_ENABLED");
            }
        }
    }
} 