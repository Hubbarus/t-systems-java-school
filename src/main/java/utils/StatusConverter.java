package utils;

import entity.StatusEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String> {
    @Override
    public String convertToDatabaseColumn(StatusEnum status) {
        return status.name();
    }

    @Override
    public StatusEnum convertToEntityAttribute(String code) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.name().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code " + code);
    }
}