package io.github.educontessi.core.address.adapters.in.rest.v1.dto;

/**
 * @author Eduardo Possamai Contessi
 */
public class ZipCodeSearchV1Dto {

    private String zipCode;
    private StreetV1Dto street;
    private NeighborhoodV1Dto neighborhood;
    private CityV1Dto city;
    private StateV1Dto state;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public StreetV1Dto getStreet() {
        return street;
    }

    public void setStreet(StreetV1Dto street) {
        this.street = street;
    }

    public NeighborhoodV1Dto getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(NeighborhoodV1Dto neighborhood) {
        this.neighborhood = neighborhood;
    }

    public CityV1Dto getCity() {
        return city;
    }

    public void setCity(CityV1Dto city) {
        this.city = city;
    }

    public StateV1Dto getState() {
        return state;
    }

    public void setState(StateV1Dto state) {
        this.state = state;
    }
}
