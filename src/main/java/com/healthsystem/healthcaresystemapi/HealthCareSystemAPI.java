package com.healthsystem.healthcaresystemapi;

import com.healthsystem.healthcaresystemapi.utility.Application;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.jersey.server.ContainerFactory;

import java.io.IOException;

public class HealthCareSystemAPI {

    private static final String PACKAGE = "com.healthsystem.healthcaresystemapi";

    private static HttpServer configure() {
        final Application app = new Application();

        app.setApplicationName("Health API");

        System.out.println("Loading packages...");
        app.packages(PACKAGE);

        int maxThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("Max Threads: " + maxThreads);

        HttpServer httpServer = new HttpServer();

        // Thread Pool
        ThreadPoolConfig threadPoolConfig = ThreadPoolConfig.defaultConfig()
                .setCorePoolSize(maxThreads)
                .setMaxPoolSize(maxThreads)
                .setQueueLimit(-1);

        // Listener
        NetworkListener listener = new NetworkListener("test_management", "localhost", 8080);
        listener.setSendFileEnabled(false);
        listener.setSecure(false);
        listener.setMaxPendingBytes(2 * 1024 * 1024);
        listener.getTransport().setWorkerThreadPoolConfig(threadPoolConfig);
        httpServer.addListener(listener);

        // Handler
        HttpHandler handler = ContainerFactory.createContainer(HttpHandler.class, app);
        httpServer.getServerConfiguration().addHttpHandler(handler, "/healthcaresystemapi");

        return httpServer;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Starting server..");
        HttpServer server = configure();
        server.start();
        System.out.println("Server started successfully.");
        System.out.println("Press 'Q' to quite.");
        int i = 0;
        while (i != 81 && i != 113) {
            i = System.in.read();
        }
        server.shutdownNow();
    }
}
