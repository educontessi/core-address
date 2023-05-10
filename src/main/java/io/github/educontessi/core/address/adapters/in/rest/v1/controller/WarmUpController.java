package io.github.educontessi.core.address.adapters.in.rest.v1.controller;

import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.CityV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.datamanager.CountryV1DataManager;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.WarmUpRequestDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.UUID;

/**
 * @author Eduardo Possamai Contessi
 */
@RestController
@RequestMapping(
        value = "/v1/warmup",
        produces = {"application/json"},
        consumes = {"application/json"})
@Tag(name = "WarmUpController", description = "Warm Me Up ")
public class WarmUpController {

    private final CountryV1DataManager countryV1DataManager;
    private final CityV1DataManager cityV1DataManager;

    public WarmUpController(CountryV1DataManager countryV1DataManager, CityV1DataManager cityV1DataManager) {
        this.countryV1DataManager = countryV1DataManager;
        this.cityV1DataManager = cityV1DataManager;
    }

    @PostMapping
    public ResponseEntity<String> warmup(@RequestBody @Valid WarmUpRequestDto warmUpRequestDto) {
        countryV1DataManager.findById(1L);
        cityV1DataManager.findAllByStateId(24L, "all");
        return ResponseEntity.ok(UUID.randomUUID().toString() + warmUpRequestDto);
    }
}
