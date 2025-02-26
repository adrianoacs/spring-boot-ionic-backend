package com.nelioalves.cursomc.cursomc;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.domain.Produto;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {

        var categoria1 = new Categoria(null, "Informática");
        var categoria2 = new Categoria(null, "Escritório");

        var produto1 = new Produto(null, "Computador", 2000.00);
        var produto2 = new Produto(null, "Impressora", 800.00);
        var produto3 = new Produto(null, "Mouse", 80.00);

        produto1.getCategorias().addAll(Arrays.asList(categoria1));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().addAll(Arrays.asList(categoria1));

        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Arrays.asList(produto2));

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));


    }
}
