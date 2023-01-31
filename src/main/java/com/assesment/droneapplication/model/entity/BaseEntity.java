package com.assesment.droneapplication.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "timestamp with time zone DEFAULT now()")
    private LocalDateTime createdDateTime;

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "timestamp with time zone DEFAULT now()")
    private LocalDateTime updatedDateTime;

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }

        if (createdDateTime == null) {
            createdDateTime = LocalDateTime.now();
            updatedDateTime = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {

        if (updatedDateTime == null) {
            updatedDateTime = LocalDateTime.now();
        }
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }

}
