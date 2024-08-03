package escooter.micronaut;

import io.micronaut.runtime.Micronaut;
import io.micronaut.serde.annotation.SerdeImport;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "escooter-service",
                version = "1.0"
        ))

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}