package vip.creeper.mcserverplugins.attributepoints.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import vip.creeper.mcserverplugins.attributepoints.AttributePoints;

import java.io.File;
import java.io.IOException;

public class AttributePointsManager {
    private File playerAttributePointsFile;
    private YamlConfiguration playerAttributePointsYml;

    public AttributePointsManager(AttributePoints plugin) {
        this.playerAttributePointsFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "players.yml");
        this.playerAttributePointsYml = YamlConfiguration.loadConfiguration(playerAttributePointsFile);
    }

    public boolean takePoints(String playerName, int points) {
        return givePoints(playerName, - points);
    }

    public boolean givePoints(String playerName, int points) {
        int current = getPoints(playerName);

        if (current + points < 0) {
            return false;
        }

        playerAttributePointsYml.set(playerName, points);

        try {
            playerAttributePointsYml.save(playerAttributePointsFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int getPoints(String playerName) {
        return playerAttributePointsYml.getInt(playerName, 0);
    }
}
