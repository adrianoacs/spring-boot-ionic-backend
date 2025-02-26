package com.nelioalves.cursomc.cursomc.repositories;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
