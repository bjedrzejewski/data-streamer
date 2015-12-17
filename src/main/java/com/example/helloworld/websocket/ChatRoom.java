package com.example.helloworld.websocket;

import com.example.helloworld.websocket.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;

import javax.ws.rs.Path;
import java.io.IOException;

@Path("/")
@AtmosphereHandlerService(path="/chat",
        interceptors = { AtmosphereResourceLifecycleInterceptor.class,
                BroadcastOnPostAtmosphereInterceptor.class
        })
public class ChatRoom extends OnMessage<String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void onMessage(AtmosphereResponse response, String message) throws IOException {
        response.write(mapper.writeValueAsString(mapper.readValue(message, Data.class)));
    }
}