package io.github.educontessi.core.address.adapters.in.v1.web.response;

public enum ErrorType {

    INVALID_REQUEST("/error-invalid-request", "Invalid request"),
    INCOMPREHENSIVE_MESSAGE("/error-incomprehensive-message", "Incomprehensible message"),
    INVALID_DATA("/error-invalid-data", "Invalid data"),
    BUSINESS_ERROR("/error-business-error", "Business rule violation"),
    ENTITY_IN_USE("/error-entity-in-use", "Entity in use"),
    RESOURCE_NOT_FOUND("/error-resource-not-found", "Resource not found"),
    INTERNAL_SERVER_ERROR("/error-internal-server-error", "Internal server error");

    private final String title;
    private final String path;

    ErrorType(String path, String title) {
        this.path = path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }
}