package io.github.educontessi.core.address.adapters.in.web.v1.dataconverter;

import io.github.educontessi.core.address.adapters.in.web.exception.InvalidDtoException;
import io.github.educontessi.core.address.adapters.in.web.v1.dto.BaseDto;
import io.github.educontessi.core.address.core.model.BaseModel;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
