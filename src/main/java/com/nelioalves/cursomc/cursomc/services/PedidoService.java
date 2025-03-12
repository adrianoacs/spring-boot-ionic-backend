package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.domain.Pedido;
import com.nelioalves.cursomc.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(
                        String.format("Não foi encontrada %s para o id '%s'", Cliente.class.getName(), id)));
    }
}
