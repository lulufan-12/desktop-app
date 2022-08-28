package application;

import application.config.App;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Main {
	
	public static void main(String[] args) {
		try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {

			App app = container.select(App.class).get();
			app.run();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
