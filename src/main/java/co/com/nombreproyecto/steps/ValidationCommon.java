package co.com.nombreproyecto.steps;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ValidationCommon  {

    @Step("se valida el esquema {0} ")
    public void validateSchema(String path) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
            .setValidationConfiguration(ValidationConfiguration
                .newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();

        SerenityRest.then().assertThat()
            .body(matchesJsonSchemaInClasspath(path).using(jsonSchemaFactory));
            }

}
