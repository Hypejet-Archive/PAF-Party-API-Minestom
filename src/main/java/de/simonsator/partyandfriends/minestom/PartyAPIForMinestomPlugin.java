package de.simonsator.partyandfriends.minestom;

import com.moandjiezana.toml.Toml;
import de.simonsator.partyandfriends.minestom.error.ErrorReporter;
import de.simonsator.partyandfriends.minestom.main.Main;
import de.simonsator.partyandfriends.minestom.party.mysql.MySQLPartyManager;
import de.simonsator.partyandfriends.minestom.party.redis.RedisPartyManager;
import me.heroostech.citystom.Extension;

import java.io.*;
import java.nio.file.Path;

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

	private Toml getConfig() {
		Toml toml = new Toml();
		File file = Path.of(String.valueOf(getDataDirectory()), "config.toml").toFile();

		if(!file.exists()) {
			try {
				InputStream configResource = getClass().getClassLoader().getResourceAsStream("config.toml");
				if (configResource == null) return null;
				byte[] bytes = configResource.readAllBytes();
				if(!getDataDirectory().toFile().exists())
					getDataDirectory().toFile().mkdir();
				File f = new File(getDataDirectory().toFile(), "config.toml");
				f.createNewFile();
				OutputStream stream = new FileOutputStream(f);
				stream.write(bytes);
				configResource.close();
				stream.close();
				file = f;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return toml.read(file);
	}
}
