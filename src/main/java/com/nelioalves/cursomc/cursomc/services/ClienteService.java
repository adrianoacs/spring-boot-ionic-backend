package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(
                        String.format("Não foi encontrada %s para o id '%s'", Cliente.class.getName(), id)));
    }
}
