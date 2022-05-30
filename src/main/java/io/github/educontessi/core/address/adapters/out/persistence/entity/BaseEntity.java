package io.github.educontessi.core.address.adapters.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@JsonInclude(Include.NON_NULL)
public abstract class BaseEntity {

    @Column(name = "create_date", insertable = false, updatable = false)
    protected LocalDateTime created;

    @Column(name = "changed", insertable = false, updatable = false)
    protected LocalDateTime changed;

    @Column(name = "deleted", columnDefinition = "tinyint(1) default 0", insertable = false)
    protected boolean deleted;

    @Column(name = "delete_date", insertable = false)
    protected LocalDateTime deletedDate;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getChanged() {
        return changed;
    }

    public void setChanged(LocalDateTime changed) {
        this.changed = changed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        this.setDeletedDate(this.deleted ? LocalDateTime.now() : null);
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(LocalDateTime deletedDate) {
        this.deletedDate = deletedDate;
    }

}
