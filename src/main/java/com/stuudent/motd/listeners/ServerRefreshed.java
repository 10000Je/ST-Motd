package com.stuudent.motd.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import com.stuudent.motd.MotdCore;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServerRefreshed implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPing(ServerListPingEvent e) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String motd : MotdCore.cf.getStringList("ServerMotd")) {
            stringBuilder.append(ChatColor.translateAlternateColorCodes('&', motd)).append("\n");
        }
        e.setMotd(stringBuilder.toString());
        e.setMaxPlayers(MotdCore.cf.getInt("ServerMaxPlayers"));
    }

    public static void initListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(MotdCore.instance, ListenerPriority.LOWEST, PacketType.Status.Server.SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                WrappedServerPing ping = event.getPacket().getServerPings().read(0);
                List<WrappedGameProfile> hoverList = new ArrayList<>();
                for (String hover : MotdCore.cf.getStringList("ServerHoverList"))
                    hoverList.add(new WrappedGameProfile(UUID.randomUUID(), ChatColor.translateAlternateColorCodes('&', hover)));
                ping.setPlayers(hoverList);
            }
        });
    }

}
