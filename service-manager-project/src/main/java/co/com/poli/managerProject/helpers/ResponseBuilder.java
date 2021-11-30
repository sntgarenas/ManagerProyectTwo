package co.com.poli.managerProject.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    public Response success(String uri){
        return Response.builder()
                .data(HttpStatus.OK)
                .Uri(uri)
                .build();
    }

    public Response success(Object data, String uri){
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }

    public Response successCreated(Object data, String uri) {
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }

    public Response failed(Object data, String uri){
        return Response.builder()
                .data(data)
                .Uri(uri)
                .build();
    }
}
