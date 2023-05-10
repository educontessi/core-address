package io.github.educontessi.core.address.core.model;

import io.github.educontessi.core.address.core.util.StringUtils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Model {@link City} to manipulate city table
 *
 * @author Eduardo Possamai Contessi
 */
public class City extends BaseModel {

    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private Long stateId;

    private State state;

    @NotNull
    private Integer ibge;

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

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }
}
