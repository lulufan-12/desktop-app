package application.utils;

import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvUtils {
	public static final String DB_URL = "DB_URL";
	public static final String DB_USER = "DB_USER";
	public static final String DB_PASSWORD = "DB_PASSWORD";
	public static final String PERSISTENCE_UNIT = "PERSISTENCE_UNIT";

	public String getEnv(@Nonnull String name) {
		return System.getenv(name);
	}
}
