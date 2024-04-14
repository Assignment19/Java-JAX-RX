package com.healthsystem.healthcaresystemapi;
import com.healthsystem.healthcaresystemapi.utility.SwaggerConfig;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import java.io.IOException;
import java.net.URI;

public class HealthCareSystemAPI {
    public static void main(String[] args) throws IOException {
        // Initialize Swagger
        SwaggerConfig.init();

   // Define your JAX-RS resource configuration using PackagesResourceConfig
        PackagesResourceConfig rc = new PackagesResourceConfig("com.healthsystem.healthcaresystemapi.resources");

        // Enable POJO mapping for JSON support
        rc.getFeatures().put("com.sun.jersey.api.json.POJOMappingFeature", true);

        // Create and start the Grizzly HTTP server
        HttpServer server = GrizzlyServerFactory.createHttpServer(URI.create("http://localhost:8080"), rc);
        server.start();

        System.out.println("HealthCareSystemAPI started. Hit enter to stop it...");
        System.in.read(); // Keep the server running until the user hits enter

        server.stop();
    }
}
