package io.github.educontessi.core.address.adapters.in.rest.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(description = "Save resource")
@SwaggerDocumentationGlobal
@ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Resource saved successfully")})
public @interface SwaggerDocumentationPOST {

}
