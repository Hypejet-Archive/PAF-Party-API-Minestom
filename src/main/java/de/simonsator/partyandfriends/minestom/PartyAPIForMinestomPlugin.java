package de.simonsator.partyandfriends.minestom;

import com.moandjiezana.toml.Toml;
import de.simonsator.partyandfriends.minestom.error.ErrorReporter;
import de.simonsator.partyandfriends.minestom.main.Main;
import de.simonsator.partyandfriends.minestom.party.mysql.MySQLPartyManager;
import de.simonsator.partyandfriends.minestom.party.redis.RedisPartyManager;
import org.hypejet.hypestom.Extension;

public class PartyAPIForMinestomPlugin extends Extension {
	@Override
	public void initialize() {
		switch (getConfig().getString("PartyDataTransferMethod").toLowerCase()) {
			case "redis" -> new RedisPartyManager(getConfig());
			case "mysql" -> new MySQLPartyManager(Main.getInstance().getMySQLData());
			case "none" ->
					new ErrorReporter("§cThe plugin has not been setup yet. Please visit https://github.com/Simonsator/Party-API-For-Spigot/wiki/Installation to learn how to setup the plugin.");
			default ->
					new ErrorReporter("§cPartyDataTransferMethod must be either \"redis\" or \"mysql\". Please refer to the installation guide (https://github.com/Simonsator/Party-API-For-Spigot/wiki/Installation) for more information.");
		}
	}

	@Override
	public void terminate() {}
}
