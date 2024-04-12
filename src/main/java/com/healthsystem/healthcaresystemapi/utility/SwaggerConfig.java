
package com.healthsystem.healthcaresystemapi.utility;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfig {
    public static void init() {
        BeanConfig config = new BeanConfig();
        config.setBasePath("/api");
        config.setResourcePackage("com.healthsystem.healthcaresystemapi.resources");
        config.setScan(true);
    }
}

