package com.github.riking.mcmmotop;

import java.util.HashMap;
import java.util.List;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.gmail.nossr50.datatypes.skills.SkillType;

public class PrefixUpdater {
    private BukkitTask scheduledFetch = null;
    private HashMap<SkillType, List<String>> topPlayers;

    public class LeaderboardFetch extends BukkitRunnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub

        }
    }

    public class ApplyPrefixes extends BukkitRunnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub

        }
    }
}
