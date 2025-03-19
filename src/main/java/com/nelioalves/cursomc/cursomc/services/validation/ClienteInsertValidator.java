package com.nelioalves.cursomc.cursomc.services.validation;

import com.nelioalves.cursomc.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.cursomc.resources.exception.FieldMessage;
import com.nelioalves.cursomc.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext constraintValidatorContext) {
        var list = new ArrayList<FieldMessage>();

        if (clienteNewDTO.getTipoCliente() == null) {
            list.add(new FieldMessage("tipoCliente", "TipoCliente não pode ser nulo"));
        }

        if (clienteNewDTO.getTipoCliente() == TipoCliente.PESSOAFISICA &&
                !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj",
                    String.format("CPF '%s' informado não é válido", clienteNewDTO.getCpfOuCnpj())));
        }

        if (clienteNewDTO.getTipoCliente() == TipoCliente.PESSOAJURIDICA &&
                !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("CpfOuCnpj",
                    String.format("CNPJ '%s' informado não é válido", clienteNewDTO.getCpfOuCnpj())));
        }

        if(clienteRepository.findByEmail(clienteNewDTO.getEmail()).isPresent()){
            list.add(new FieldMessage("Email", "email já cadastrado!"));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
