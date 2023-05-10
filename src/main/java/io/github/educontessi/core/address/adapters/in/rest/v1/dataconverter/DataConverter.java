package io.github.educontessi.core.address.adapters.in.rest.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.rest.exception.InvalidDtoException;
import io.github.educontessi.core.address.adapters.in.rest.v1.dto.BaseDto;
import io.github.educontessi.core.address.core.model.BaseModel;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public abstract class DataConverter<M extends BaseModel, D extends BaseDto> {

    public abstract void copyToModel(M model, D dto);

    public abstract D convertToDto(D dto, M model);

    protected void isValid(M model) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<M>> violations = validator.validate(model);
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<M> violation : violations) {
                builder.append("|");
                builder.append(violation.getMessage().replace("{0}", violation.getPropertyPath().toString()));
            }
            throw new InvalidDtoException(builder);
        }
    }

    protected Long getIdOrNull(BaseDto dto, Long idPriority) {
        Long retorno = null;
        if (idPriority != null) {
            retorno = idPriority;
        } else if (dto != null) {
            retorno = dto.getId();
        }
        return retorno;
    }
}
