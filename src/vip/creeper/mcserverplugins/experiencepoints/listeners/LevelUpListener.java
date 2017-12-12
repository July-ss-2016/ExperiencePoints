package vip.creeper.mcserverplugins.experiencepoints.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import vip.creeper.mcserverplugins.experiencepoints.ExperiencePoints;
import vip.creeper.mcserverplugins.experiencepoints.Settings;
import vip.creeper.mcserverplugins.experiencepoints.managers.ExperiencePointsManager;

public class LevelUpListener implements Listener {
    private ExperiencePointsManager experiencePointsManager;
    private Settings settings;

    public LevelUpListener(ExperiencePoints plugin) {
        this.experiencePointsManager = plugin.getExperiencePointsManager();
        this.settings = plugin.getSettings();
    }

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        int temp = experiencePointsManager.getPoints(playerName) + (event.getNewLevel() - event.getOldLevel()) * settings.getLevelUpIncreaseExperiencePoints();

    }
}
