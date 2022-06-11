package io.github.educontessi.core.address.adapters.in.documentation;

import io.github.educontessi.core.address.adapters.in.v1.web.response.ResponseError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Operation(description = "Update resource")
@SwaggerDocumentationGlobal
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Resource successfully changed"),
        @ApiResponse(responseCode = "404", description = "The resource you were trying to change was not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseError.class)))})
public @interface SwaggerDocumentationPUT {

}
