package io.github.educontessi.core.address.adapters.in.event.kafka.v1.payload;

public class CityV1Payload extends BasePayload {

    private Long id;
    private String name;
    private Long stateId;
    private StateV1Payload state;
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

    public StateV1Payload getState() {
        return state;
    }

    public void setState(StateV1Payload state) {
        this.state = state;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }
}
