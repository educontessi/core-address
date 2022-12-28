package io.github.educontessi.core.address.adapters.in.event.kafka.v1.payload;

public class StreetV1Payload extends BasePayload {

    private Long id;
    private String name;
    private Long cityId;
    private CityV1Payload city;

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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public CityV1Payload getCity() {
        return city;
    }

    public void setCity(CityV1Payload city) {
        this.city = city;
    }
}
