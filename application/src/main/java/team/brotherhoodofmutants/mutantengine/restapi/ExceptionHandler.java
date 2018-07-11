package team.brotherhoodofmutants.mutantengine.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.InternalServerErrorException;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.MatrixException;
import team.brotherhoodofmutants.mutantengine.usecase.exceptions.NucleotideException;

import static java.util.Collections.singletonMap;
import static spark.Spark.exception;
import static team.brotherhoodofmutants.mutantengine.utils.JsonUtils.toJson;

@Component
public class ExceptionHandler {

    private static ObjectMapper mapper = new ObjectMapper();

    public void setupExceptionHandlers(){
        exception(MatrixException.class, (exception, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
        exception(NucleotideException.class, (exception, request, response) -> {
            response.status(400);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
        exception(InternalServerErrorException.class, (exception, request, response) -> {
            response.status(500);
            response.type("application/json");
            response.body(toJson(singletonMap("error", exception.getMessage())));
        });
    }

}