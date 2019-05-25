package me.fascinated.donation.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Config {
    private JavaPlugin _plugin;
    private String _configName;

    private File _configurationFile;
    private FileConfiguration _configuration;

    public Config(JavaPlugin plugin, String configName, String folderName) {
        _plugin = plugin;
        _configName = configName;
        _configurationFile = new File(plugin.getDataFolder() + (folderName == null ? "" : "/" + folderName), configName);
    }

    public FileConfiguration getConfiguration() {
        if (_configuration == null) {
            reloadConfig();
        }
        return _configuration;
    }

    public File getFile() {
        return _configurationFile;
    }

    public void reloadConfig() {
        _configuration = YamlConfiguration.loadConfiguration(_configurationFile);
        InputStream defConfigStream = _plugin.getResource(_configName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            _configuration.setDefaults(defConfig);
        }
    }

    public void saveConfig() {
        if (_configuration != null && _configurationFile != null) {
            try {
                getConfiguration().save(_configurationFile);
            } catch (IOException ex) {
                _plugin.getLogger().info("Configuration save failed!");
            }
        }
    }

    public void saveDefaultConfig() {
        if (!_configurationFile.exists()) {
            _plugin.saveResource(_configName, false);
        }
    }

    public void deleteConfig() {
        _configurationFile.delete();
    }
}