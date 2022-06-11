package io.github.educontessi.core.address.adapters.in.v1.web.controller;

import io.github.educontessi.core.address.adapters.in.documentation.*;
import io.github.educontessi.core.address.adapters.in.v1.datamanager.CountryDataManager;
import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(
        value = "/v1/countries",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "CountryController", description = "Country Manipulation")
public class CountryController extends BaseController<Country> {

    private final CountryDataManager dataManager;

    public CountryController(CountryDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostMapping
    @SwaggerDocumentationPOST
    public ResponseEntity<Country> save(@Valid @RequestBody Country model) {
        Country entity = dataManager.save(model);
        return created(entity.getId(), entity);
    }

    @GetMapping
    @SwaggerDocumentationGETList
    public ResponseEntity<List<Country>> findAll() {
        List<Country> list = dataManager.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    @SwaggerDocumentationGETList
    public ResponseEntity<Page<Country>> search(CountryFilter filter, Pageable pageable) {
        Page<Country> page = dataManager.search(filter, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @SwaggerDocumentationGET
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        Country model = dataManager.findById(id);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/{id}")
    @SwaggerDocumentationPUT
    public ResponseEntity<Country> update(@PathVariable Long id, @Valid @RequestBody Country model) {
        model = dataManager.update(id, model);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    @SwaggerDocumentationDELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dataManager.delete(id);
    }

}
