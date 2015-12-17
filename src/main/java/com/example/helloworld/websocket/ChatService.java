package com.example.helloworld.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.cache.UUIDBroadcasterCache;
import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;
import org.eclipse.jetty.server.Authentication;

import javax.ws.rs.Path;
import java.io.IOException;

@Path("/")
@AtmosphereHandlerService(path = "/chat",
        broadcasterCache = UUIDBroadcasterCache.class,
        interceptors = {AtmosphereResourceLifecycleInterceptor.class,
                BroadcastOnPostAtmosphereInterceptor.class,
                TrackMessageSizeInterceptor.class,
                HeartbeatInterceptor.class
        })
public class ChatService extends OnMessage<String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse response, String message) throws IOException {
        response.write(mapper.writeValueAsString(mapper.readValue(message, Authentication.User.class)));
    }
}
