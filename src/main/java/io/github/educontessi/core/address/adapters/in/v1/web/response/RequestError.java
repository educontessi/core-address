package io.github.educontessi.core.address.adapters.in.v1.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestError implements Serializable {

    @Serial
    private static final long serialVersionUID = -7936551501603456464L;
    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String urlError;
    private String userMessage;
    private String exception;
    private String exceptionMessage;
    private List<ErrorDetail> datails;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlError() {
        return urlError;
    }

    public void setUrlError(String urlError) {
        this.urlError = urlError;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public List<ErrorDetail> getDatails() {
        return datails;
    }

    public void setDatails(List<ErrorDetail> datails) {
        this.datails = datails;
    }
}
