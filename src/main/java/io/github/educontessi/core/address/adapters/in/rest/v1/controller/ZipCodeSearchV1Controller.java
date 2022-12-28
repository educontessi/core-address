package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.documentation.SwaggerDocumentationGET;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.ZipCodeSearchV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.ZipCodeSearchV1Dto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.github.educontessi.core.address.core.config.TagLogs.*;

@RestController
@RequestMapping(
        value = "/v1/zip-code-search",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "ZipCodeSearchV1Controller", description = "Zip Code Search ")
public class ZipCodeSearchV1Controller extends BaseController<ZipCodeSearchV1Dto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipCodeSearchV1Controller.class);

    private final ZipCodeSearchV1DataManager dataManager;

    public ZipCodeSearchV1Controller(ZipCodeSearchV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @GetMapping("/{zipCode}")
    @SwaggerDocumentationGET
    public ResponseEntity<ZipCodeSearchV1Dto> search(@PathVariable String zipCode) {
        LOGGER.info("{}", ZIP_CODE_SEARCH + COUNT + REQUEST);
        ZipCodeSearchV1Dto dto = dataManager.search(zipCode);
        LOGGER.info("{}", ZIP_CODE_SEARCH + COUNT + REQUEST + SUCCESS);
        return ResponseEntity.ok(dto);
    }

}
