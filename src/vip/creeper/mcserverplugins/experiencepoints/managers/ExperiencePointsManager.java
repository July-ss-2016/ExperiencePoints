package vip.creeper.mcserverplugins.experiencepoints.managers;

import org.bukkit.configuration.file.YamlConfiguration;
import vip.creeper.mcserverplugins.experiencepoints.ExperiencePoints;

import java.io.File;
import java.io.IOException;

public class ExperiencePointsManager {
    private File playerExperiencePointsFile;
    private YamlConfiguration playerExperiencePointsYml;

    public ExperiencePointsManager(ExperiencePoints plugin) {
        this.playerExperiencePointsFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "players.yml");
        this.playerExperiencePointsYml = YamlConfiguration.loadConfiguration(playerExperiencePointsFile);
    }

    public boolean takePoints(String playerName, int points) {
        return givePoints(playerName, - points);
    }

    public boolean givePoints(String playerName, int points) {
        int current = getPoints(playerName);

        if (current + points < 0) {
            return false;
        }

        playerExperiencePointsYml.set(playerName, points);

        try {
            playerExperiencePointsYml.save(playerExperiencePointsFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public int getPoints(String playerName) {
        return playerExperiencePointsYml.getInt(playerName, 0);
    }
}
