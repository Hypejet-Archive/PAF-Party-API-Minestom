package de.simonsator.partyandfriends.minestom.party.mysql;

import de.simonsator.partyandfriends.minestom.api.pafplayers.PAFPlayer;
import de.simonsator.partyandfriends.minestom.communication.sql.MySQLData;
import de.simonsator.partyandfriends.minestom.pafplayers.mysql.PAFPlayerMySQL;
import de.simonsator.partyandfriends.minestom.api.party.PartyManager;
import de.simonsator.partyandfriends.minestom.api.party.PlayerParty;

public class MySQLPartyManager extends PartyManager {
	private final MySQLConnection CONNECTION;

	public MySQLPartyManager(MySQLData mySQLData) {
		CONNECTION = new MySQLConnection(mySQLData);
	}

	@Override
	public PlayerParty getParty(PAFPlayer pPlayer) {
		return CONNECTION.getParty(((PAFPlayerMySQL) pPlayer).getPlayerID());
	}

	@Override
	public void onDisable() {
		CONNECTION.closeConnection();
	}
}
