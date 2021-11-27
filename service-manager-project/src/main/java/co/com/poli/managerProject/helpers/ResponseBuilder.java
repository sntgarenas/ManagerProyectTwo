package co.com.poli.managerProject.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    public Response success(){
        return Response.builder()
                .data(HttpStatus.OK)
                .status(HttpStatus.OK.value())
                .build();
    }

    public Response success(Object data){
        return Response.builder()
                .data(data)
                .status(HttpStatus.OK.value())
                .build();
    }

    public Response failed(Object data){
        return Response.builder()
                .data(data)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
    }
}
