package io.github.educontessi.core.address.adapters.in.web.v1.controller;

import io.github.educontessi.core.address.adapters.in.documentation.*;
import io.github.educontessi.core.address.adapters.in.web.v1.datamanager.AddressV1DataManager;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.AddressV1Dto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/v1/integration/{integrationId}/adresses",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "AddressV1Controller", description = "Address Manipulation")
public class AddressV1Controller extends BaseController<AddressV1Dto> {

    private final AddressV1DataManager dataManager;

    public AddressV1Controller(AddressV1DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<AddressV1Dto> save(@PathVariable String integrationId, @Valid @RequestBody AddressV1Dto dto) {
        AddressV1Dto saved = dataManager.save(integrationId, dto);
        return created(saved.getId(), saved);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<AddressV1Dto>> findAll(@PathVariable String integrationId) {
        List<AddressV1Dto> list = dataManager.findAllByIntegrationId(integrationId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<AddressV1Dto> findById(@PathVariable String integrationId, @PathVariable Long id) {
        AddressV1Dto dto = dataManager.findById(integrationId, id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/default-address")
    @SwaggerDocumentationGET
    public ResponseEntity<AddressV1Dto> findDefaultAddress(@PathVariable String integrationId) {
        AddressV1Dto dto = dataManager.findDefaultAddress(integrationId);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<AddressV1Dto> update(@PathVariable String integrationId, @PathVariable Long id, @Valid @RequestBody AddressV1Dto dto) {
        dto = dataManager.update(integrationId, id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @SwaggerDocumentationDELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String integrationId, @PathVariable Long id) {
        dataManager.delete(integrationId, id);
    }

}
