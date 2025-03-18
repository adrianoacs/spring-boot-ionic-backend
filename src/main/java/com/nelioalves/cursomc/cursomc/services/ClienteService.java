package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(
                        String.format("Não foi encontrada %s para o id '%s'", Cliente.class.getName(), id)));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        var pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.fromString(direction), orderBy);
        return repository.findAll(pageRequest);
    }

//    public Cliente insert(Cliente categoria){
//        categoria.setId(null);
//        return repository.save(categoria);
//    }

    public Cliente update (Cliente obj){
        var newObj = this.find(obj.getId());
        updateData(newObj,  obj);
        return repository.save(newObj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível deletar pois há entidades relacionadas!");
        }
    }

    public Cliente fromDto(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
