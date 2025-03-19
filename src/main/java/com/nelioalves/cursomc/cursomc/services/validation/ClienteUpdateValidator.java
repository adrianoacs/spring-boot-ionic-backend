package com.nelioalves.cursomc.cursomc.services.validation;

import com.nelioalves.cursomc.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        var list = new ArrayList<FieldMessage>();

        clienteRepository.findByEmail(clienteDTO.getEmail()).ifPresent(cliente -> {
            var map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            var id = Integer.parseInt(map.get("id"));
            if (!cliente.getId().equals(id)) {
                list.add(new FieldMessage("Email", "email já utilizado por outro cliente!"));
            }
        });

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
