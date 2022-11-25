package de.simonsator.partyandfriends.minestom.party.redis;

import com.moandjiezana.toml.Toml;
import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.api.party.PartyManager;
import de.simonsator.partyandfriends.minestom.api.party.PlayerParty;
import redis.clients.jedis.Jedis;

public class RedisPartyManager extends PartyManager {
	private final Toml CONFIG;

	public RedisPartyManager(Toml pConfig) {
		CONFIG = pConfig.getTable("redis");
	}

	Jedis getConnection() {
		Jedis jedis = new Jedis(CONFIG.getString("Host"), Math.toIntExact(CONFIG.getLong("Port")));
		if (!CONFIG.getString("Password").equals(""))
			jedis.auth(CONFIG.getString("Password"));
		return jedis;
	}

	@Override
	public PlayerParty getParty(PAFPlayer pPlayer) {
		try (Jedis jedis = getConnection()) {
			String id = jedis.hget("paf:parties:players:id", pPlayer.getUniqueId().toString());
			if (id == null)
				return null;
			return new RedisPlayerParty(Integer.parseInt(id), this);
		}
	}

	@Override
	public void onDisable() {

	}
}
