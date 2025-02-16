package com.productmangement.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public abstract class DateAudit implements Serializable {
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @LastModifiedBy
    private Long updatedBy;
}