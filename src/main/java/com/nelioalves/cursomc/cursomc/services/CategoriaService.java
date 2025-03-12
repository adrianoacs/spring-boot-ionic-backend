package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Não foi encontrada " + Categoria.class.getName() + " para o id " + id));
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update (Categoria categoria){
        this.find(categoria.getId());
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id){
        find(id);
        try{
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível deletar uma categoria com produtos vinculados!");
        }
    }


}
