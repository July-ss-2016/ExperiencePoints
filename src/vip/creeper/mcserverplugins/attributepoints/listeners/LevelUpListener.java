package vip.creeper.mcserverplugins.attributepoints.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import vip.creeper.mcserverplugins.attributepoints.AttributePoints;
import vip.creeper.mcserverplugins.attributepoints.Settings;
import vip.creeper.mcserverplugins.attributepoints.managers.AttributePointsManager;

public class LevelUpListener implements Listener {
    private AttributePointsManager attributePointsManager;
    private Settings settings;

    public LevelUpListener(AttributePoints plugin) {
        this.attributePointsManager = plugin.getExperiencePointsManager();
        this.settings = plugin.getSettings();
    }

    @EventHandler
    public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();

        int temp = (event.getNewLevel() - event.getOldLevel()) * settings.getLevelUpIncreaseAttributePoints();
        attributePointsManager.givePoints(playerName, temp);
    }
}
