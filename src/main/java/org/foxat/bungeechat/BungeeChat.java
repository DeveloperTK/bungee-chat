package org.foxat.bungeechat;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeChat extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, this);
    }

    @Override
    public void onDisable() {
        getProxy().broadcast(new TextComponent("Goodbye"));
    }

    @EventHandler
    public void onChat(ChatEvent e) {
        e.setCancelled(true);

        TextComponent component = new TextComponent();
        String message = e.getMessage();

        if(e.getMessage().startsWith("∆")) {
            char color = e.getMessage().toCharArray()[1];
            message = ChatColor.getByChar(color) + message.substring(2);
        }

        if(e.getSender() instanceof ProxiedPlayer) {
            String name = ((ProxiedPlayer) e.getSender()).getDisplayName();
            component.setText("<" + name + "> " + message);
        } else {
            component.setText("[Server] " + message);
        }

        ProxyServer.getInstance().broadcast(component);
    }

}
