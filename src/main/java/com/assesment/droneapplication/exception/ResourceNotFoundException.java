/*
 * Copyright (c) 2022. Envispalm and/or its affiliates. All Rights Reserved. (https://www.envispalm.com)
 */

package com.assesment.droneapplication.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Nyasha Chirinda - 30/01/2023
 */

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private final String operation;
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException(String operation, String resourceName, String fieldName, Object fieldValue) {
        super("Could not find %s with %s: [%s]".formatted(resourceName, fieldName, fieldValue));
        this.operation = operation;
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
