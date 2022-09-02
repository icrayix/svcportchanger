package de.icrayix.svc_port_changer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

public class SvcPortChanger extends JavaPlugin {

    @Override
    public void onLoad() {
        final File configFile = new File(this.getFile().getParent() + "/voicechat/voicechat-server.properties");

        if(!configFile.exists()) {
            Bukkit.getLogger().info("[SVCPortChanger] SimpleVoiceChat-Configuration not found!");
            Bukkit.getLogger().info("[SVCPortChanger] Please install the plugin or start your server once so that the configuration can be generated!");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        final Properties config = new Properties();

        try {
            config.load(Files.newInputStream(configFile.toPath()));
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
