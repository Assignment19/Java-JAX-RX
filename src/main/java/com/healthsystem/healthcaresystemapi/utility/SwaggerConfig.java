
package com.healthsystem.healthcaresystemapi.utility;
import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfig {
    public static void init() {
        BeanConfig config = new BeanConfig();
        config.setBasePath("/api");
        config.setResourcePackage("com.healthsystem.healthcaresystemapi.resources");
        config.setScan(true);
    }
}

