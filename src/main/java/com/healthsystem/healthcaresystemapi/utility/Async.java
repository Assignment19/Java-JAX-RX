
package com.healthsystem.healthcaresystemapi.utility;

import javax.inject.Singleton;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.container.AsyncResponse;
import java.util.concurrent.TimeUnit;

@Singleton
public class Async {

    public void execute(AsyncResponse response, Task task) {

        response.register(new RequestCallback(response));

        Thread thread = new Thread(() -> {
            setTimeout(response);
            task.run();
        });

        thread.setUncaughtExceptionHandler((t, e) ->
            response.resume(e)
        );

        thread.start();
    }

    private void setTimeout(AsyncResponse response) {
        response.setTimeoutHandler(res -> res.resume(new ServiceUnavailableException()));
        response.setTimeout(16000, TimeUnit.MILLISECONDS);
    }
}
