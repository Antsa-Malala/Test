package org.project.clouds5_backend.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return (role == null) ? null : role.getValue();
    }

    @Override
    public Role convertToEntityAttribute(Integer value) {
        if (value == null) {
            return null;
        }

        return (value == 0) ? Role.USER : Role.ADMIN;
    }
}
