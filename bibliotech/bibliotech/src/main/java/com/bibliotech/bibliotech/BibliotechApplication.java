package com.bibliotech.bibliotech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BibliotechApplication extends Application {

	private ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		System.out.println("Bibliotech Application Started");
		launch(args);
	}

	@Override
	public void init() {
		springContext = new SpringApplicationBuilder(BibliotechApplication.class).run();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Bibliotech.fxml"));
		loader.setControllerFactory(springContext::getBean);

		Parent root = loader.load();
		String title = springContext.getBean("title", String.class);

		stage.setTitle(title);
		stage.setScene(new Scene(root));
		stage.show();
	}

	@Override
	public void stop() {
		springContext.close();
	}
}
