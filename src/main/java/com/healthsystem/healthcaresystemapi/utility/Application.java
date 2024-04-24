/*!
 * Java REST API
 * Author: Roshan Gade
 * Date: 14/7/18
 */
package com.healthsystem.healthcaresystemapi.utility;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Singleton;

public class Application extends ResourceConfig {

    public Application() {
        System.out.println("REST Application.");

        packages("com.healthsystem.healthcaresystemapi.dao");
        packages("com.healthsystem.healthcaresystemapi.models");
        packages("com.healthsystem.healthcaresystemapi.resources");
        packages("com.healthsystem.healthcaresystemapi.utility");
        packages("com.healthsystem.healthcaresystemapi.services");

        register(new AbstractBinder() {

            @Override
            protected void configure() {
                bind(Async.class).to(Async.class).in(Singleton.class);
                bind(Response.class).to(Response.class).in(Singleton.class);
            }
        });
    }

}
