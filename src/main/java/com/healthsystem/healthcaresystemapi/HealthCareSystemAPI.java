import com.healthsystem.healthcaresystemapi.resources.PatientResource;
import com.healthsystem.healthcaresystemapi.utility.SwaggerConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import org.glassfish.grizzly.http.server.HttpServer;
import java.io.IOException;
import java.net.URI;

public class HealthCareSystemAPI {
    public static void main(String[] args) throws IOException {
        // Initialize Swagger
        SwaggerConfig.init();

        // Define your JAX-RS resource configuration
        ResourceConfig rc = new ResourceConfig()
                .packages("com.healthsystem.healthcaresystemapi.resources")
                .property(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        // Create and start the Grizzly HTTP server
        HttpServer server = GrizzlyServerFactory.createHttpServer(URI.create("http://localhost:8080"), rc);
        server.start();

        System.out.println("HealthCareSystemAPI started. Hit enter to stop it...");
        System.in.read(); // Keep the server running until the user hits enter

        server.shutdownNow();
    }
}
