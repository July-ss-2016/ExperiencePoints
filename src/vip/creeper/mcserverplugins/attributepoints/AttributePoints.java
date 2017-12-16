package vip.creeper.mcserverplugins.attributepoints;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.attributepoints.listeners.LevelUpListener;
import vip.creeper.mcserverplugins.attributepoints.managers.AttributePointsManager;

public class AttributePoints extends JavaPlugin {
    private static AttributePoints instance;
    private Settings settings;
    private AttributePointsManager attributePointsManager;

    public void onEnable() {
        instance = this;
        this.attributePointsManager = new AttributePointsManager(this);
        this.settings = new Settings();

        loadConfig();
        Bukkit.getPluginManager().registerEvents(new LevelUpListener(this), this);
    }

    private void loadConfig() {
        saveDefaultConfig();
        reloadConfig();

        settings.setLevelUpIncreaseAttributePoints(getConfig().getInt("level_up_increase_experience_points", 0));
    }

    public static AttributePoints getInstance() {
        return instance;
    }

    public AttributePointsManager getAttributePointsManager() {
        return attributePointsManager;
    }

    public Settings getSettings() {
        return settings;
    }
}
