package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Não foi encontrada " + Categoria.class.getName() + " para o id " + id));
    }
}
