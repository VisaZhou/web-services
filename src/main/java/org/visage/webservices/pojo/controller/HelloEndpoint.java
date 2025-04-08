package org.visage.webservices.pojo.controller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.visage.webservices.pojo.req.HelloRequest;
import org.visage.webservices.pojo.res.HelloResponse;

@Endpoint
public class HelloEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/soap";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HelloRequest")
    @ResponsePayload
    public HelloResponse sayHello(@RequestPayload HelloRequest request) {
        HelloResponse response = new HelloResponse();
        response.setMessage("你好, " + request.getName() + "!");
        return response;
    }
}
