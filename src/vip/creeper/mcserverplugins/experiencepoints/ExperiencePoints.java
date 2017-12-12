package vip.creeper.mcserverplugins.experiencepoints;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import vip.creeper.mcserverplugins.experiencepoints.listeners.LevelUpListener;
import vip.creeper.mcserverplugins.experiencepoints.managers.ExperiencePointsManager;

public class ExperiencePoints extends JavaPlugin {
    private static ExperiencePoints instance;
    private Settings settings;
    private ExperiencePointsManager experiencePointsManager;

    public void onEnable() {
        instance = this;
        this.experiencePointsManager = new ExperiencePointsManager(this);
        this.settings = new Settings();

        loadConfig();
        Bukkit.getPluginManager().registerEvents(new LevelUpListener(this), this);
    }

    private void loadConfig() {
        saveDefaultConfig();
        reloadConfig();

        settings.setLevelUpIncreaseExperiencePoints(getConfig().getInt("level_up_increase_experience_points", 0));
    }

    public static ExperiencePoints getInstance() {
        return instance;
    }

    public ExperiencePointsManager getExperiencePointsManager() {
        return experiencePointsManager;
    }

    public Settings getSettings() {
        return settings;
    }
}
