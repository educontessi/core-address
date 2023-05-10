package io.github.educontessi.core.address.core.model;

import io.github.educontessi.core.address.core.util.StringUtils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model {@link Neighborhood} to manipulate neighborhood table
 *
 * @author Eduardo Possamai Contessi
 */
public class Neighborhood extends BaseModel {

    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private Long cityId;

    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.formatName(name);
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
