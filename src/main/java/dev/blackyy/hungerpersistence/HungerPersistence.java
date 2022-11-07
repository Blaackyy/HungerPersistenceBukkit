package dev.blackyy.hungerpersistence;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class HungerPersistence extends JavaPlugin implements Listener {
	private final Map<UUID, PlayerModel> playerFoodLevel = new HashMap<>();

	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHungerpersistence has been enabled!"));
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		int foodLevel = playerFoodLevel.get(event.getPlayer().getUniqueId()).getFoodLevel();
		float saturationLevel = playerFoodLevel.get(event.getPlayer().getUniqueId()).getSaturation();
		Player player = event.getPlayer();

		getServer().getScheduler().runTaskLater(this, () -> {
			Player playerAux = event.getPlayer();

			if (foodLevel == 0) {
				playerAux.setFoodLevel(4);
				playerAux.setSaturation(saturationLevel);
			} else {
				playerAux.setFoodLevel(foodLevel);
				playerAux.setSaturation(saturationLevel);
			}
		}, 10L);

		playerFoodLevel.remove(player.getUniqueId());
	}

	@EventHandler
	public void onPlayerDie(PlayerDeathEvent event) {
		playerFoodLevel.put(event.getEntity().getUniqueId(), new PlayerModel(event.getEntity().getFoodLevel(), event.getEntity().getSaturation()));
	}
}
