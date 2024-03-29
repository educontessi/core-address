package io.github.educontessi.core.address.adapters.in.rest.v1.dto;

public class CityV1Dto extends BaseDto {

    private Long id;
    private String name;
    private Long stateId;
    private StateV1Dto state;
    private Integer ibge;

    @Override
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
        this.name = name;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public StateV1Dto getState() {
        return state;
    }

    public void setState(StateV1Dto state) {
        this.state = state;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }
}
