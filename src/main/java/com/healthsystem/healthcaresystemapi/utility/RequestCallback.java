/*!
 * Java REST API
 * Author: Roshan Gade
 * Date: 21/7/18
 */
package com.healthsystem.healthcaresystemapi.utility;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;

public class RequestCallback implements CompletionCallback {

    public RequestCallback(AsyncResponse response) {
        // TODO: add delayed jobs
    }

    @Override
    public void onComplete(Throwable err) {
    }
}
