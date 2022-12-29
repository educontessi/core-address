package io.github.educontessi.core.address.adapters.out.persistence.jpa.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class BaseEntity {

    @Column(name = "date_time_creation", insertable = false, updatable = false)
    protected LocalDateTime dateTimeCreation;

    @Column(name = "date_time_change", insertable = false, updatable = false)
    protected LocalDateTime dateTimeChange;

    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0", insertable = false)
    protected boolean deleted;

    @Column(name = "date_time_deletion", insertable = false)
    protected LocalDateTime dateTimeDeletion;

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        this.setDateTimeDeletion(this.deleted ? LocalDateTime.now() : null);
    }

    public LocalDateTime getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(LocalDateTime dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }

    public LocalDateTime getDateTimeChange() {
        return dateTimeChange;
    }

    public void setDateTimeChange(LocalDateTime dateTimeChange) {
        this.dateTimeChange = dateTimeChange;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getDateTimeDeletion() {
        return dateTimeDeletion;
    }

    public void setDateTimeDeletion(LocalDateTime dateTimeDeletion) {
        this.dateTimeDeletion = dateTimeDeletion;
    }
}
