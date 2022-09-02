package de.icrayix.svc_port_changer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public class SvcPortChanger extends JavaPlugin {

    @Override
    public void onLoad() {
        final var configFile = Bukkit.getPluginsFolder().toPath().resolve(Path.of("voicechat/voicechat-server.properties")).toFile();

        if(!configFile.exists()) {
            Bukkit.getLogger().info("[SVCPortChanger] SimpleVoiceChat-Configuration not found!");
            Bukkit.getLogger().info("[SVCPortChanger] Please install the plugin or start your server once so that the configuration can be generated!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        final var config = new Properties();

        try {
            config.load(new FileInputStream(configFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        config.setProperty("port", String.valueOf(Bukkit.getServer().getPort()));
        try {
            config.store(new FileWriter(configFile), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Bukkit.getLogger().info("[SVCPortChanger] Successfully changed the SimpleVoiceChat-Port!");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
