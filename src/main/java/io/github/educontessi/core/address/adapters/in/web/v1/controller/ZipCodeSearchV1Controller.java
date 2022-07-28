package io.github.educontessi.core.address.adapters.in.web.v1.controller;

import io.github.educontessi.core.address.adapters.in.documentation.SwaggerDocumentationGET;
import io.github.educontessi.core.address.adapters.in.web.v1.datamanager.ZipCodeSearchV1DataManager;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.ZipCodeSearchV1Dto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/v1/zip-code-search",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "ZipCodeSearchV1Controller", description = "Zip Code Search ")
public class ZipCodeSearchV1Controller extends BaseController<ZipCodeSearchV1Dto> {

    private final ZipCodeSearchV1DataManager dataManager;

    public ZipCodeSearchV1Controller(ZipCodeSearchV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @GetMapping("/{zipCode}")
    @SwaggerDocumentationGET
    public ResponseEntity<ZipCodeSearchV1Dto> search(@PathVariable String zipCode) {
        ZipCodeSearchV1Dto dto = dataManager.search(zipCode);
        return ResponseEntity.ok(dto);
    }

}
