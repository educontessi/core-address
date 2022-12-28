package io.github.educontessi.core.address.adapters.in.rest.v1.dto;

import io.github.educontessi.core.address.core.enums.PropertyType;

public class AddressV1Dto extends BaseDto {

    private Long id;
    private String integrationId;
    private Long countryId;
    private CountryV1Dto country;
    private Long stateId;
    private StateV1Dto state;
    private Long cityId;
    private CityV1Dto city;
    private Long neighborhoodId;
    private NeighborhoodV1Dto neighborhood;
    private Long streetId;
    private StreetV1Dto street;
    private String number;
    private String recipientName;
    private String recipientPhone;
    private PropertyType propertyType;
    private String complement;
    private String proximity;
    private String deliveryInstructions;
    private String zipCode;
    private boolean defaultAddress;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public CountryV1Dto getCountry() {
        return country;
    }

    public void setCountry(CountryV1Dto country) {
        this.country = country;
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

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public CityV1Dto getCity() {
        return city;
    }

    public void setCity(CityV1Dto city) {
        this.city = city;
    }

    public Long getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(Long neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public NeighborhoodV1Dto getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(NeighborhoodV1Dto neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public StreetV1Dto getStreet() {
        return street;
    }

    public void setStreet(StreetV1Dto street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getProximity() {
        return proximity;
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
