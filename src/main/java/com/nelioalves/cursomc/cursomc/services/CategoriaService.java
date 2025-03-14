package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Não foi encontrada " + Categoria.class.getName() + " para o id " + id));
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
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

    public Categoria fromDto(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }


}
