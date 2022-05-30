package io.github.educontessi.core.address.adapters.in.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(description = "Search resource list")
@SwaggerDocumentationGlobal
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully retrieved all resources")})
public @interface SwaggerDocumentationGETList {

}
