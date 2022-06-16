package io.github.educontessi.core.address.adapters.in.v1.datamanager;

import io.github.educontessi.core.address.core.filter.CountryFilter;
import io.github.educontessi.core.address.core.model.Country;
import io.github.educontessi.core.address.ports.in.CountryUseCasePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CountryDataManager {

    private final CountryUseCasePort countryUseCasePort;

    public CountryDataManager(CountryUseCasePort countryUseCasePort) {
        this.countryUseCasePort = countryUseCasePort;
    }

    public List<Country> findAll() {
        return countryUseCasePort.findAll();
    }

    public Page<Country> search(CountryFilter filter, Pageable pageable) {
        return (Page<Country>) countryUseCasePort.search(filter, pageable);
    }

    public Country findById(Long id) {
        return countryUseCasePort.findById(id);
    }

    public Country save(Country model) {
        return countryUseCasePort.save(model, Collections.emptyList()); // posso adicionar novas validações fora do core
    }

    public Country update(Long id, Country model) {
        return countryUseCasePort.update(id, model, Collections.emptyList());
    }

    public void delete(Long id) {
        countryUseCasePort.delete(id, Collections.emptyList());
    }
}
