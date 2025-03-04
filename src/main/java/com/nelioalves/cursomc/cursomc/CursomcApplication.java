package com.nelioalves.cursomc.cursomc;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.domain.Cidade;
import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.domain.Endereco;
import com.nelioalves.cursomc.cursomc.domain.Estado;
import com.nelioalves.cursomc.cursomc.domain.Produto;
import com.nelioalves.cursomc.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public void run(String... args) throws Exception {

        var categoria1 = new Categoria(null, "Informática");
        var categoria2 = new Categoria(null, "Escritório");

        var produto1 = new Produto(null, "Computador", 2000.00);
        var produto2 = new Produto(null, "Impressora", 800.00);
        var produto3 = new Produto(null, "Mouse", 80.00);

        var est2 = new Estado(null, "São Paulo");
        var est1 = new Estado(null, "Minas Gerais");
        var c2 = new Cidade(null, "São Paulo", est2);
        var c3 = new Cidade(null, "Campinas", est2);
        var c1 = new Cidade(null, "Uberlandia", est1);
        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        var cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377",
                TipoCliente.PESSOAFISICA);
        cli1.getTelefones().add("27363323");
        cli1.getTelefones().add("93838393");

        var e1 = new Endereco(null, "Rua Flores", "300", "Apartamento 203",
                "Jardim", "38220834", cli1, c1);
        var e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
                "Centro", "38777012", cli1, c2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));


        produto1.getCategorias().addAll(Arrays.asList(categoria1));
        produto2.getCategorias().addAll(Arrays.asList(categoria1, categoria2));
        produto3.getCategorias().addAll(Arrays.asList(categoria1));

        categoria1.getProdutos().addAll(Arrays.asList(produto1, produto2, produto3));
        categoria2.getProdutos().addAll(Arrays.asList(produto2));

        categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2));
        produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
        estadoRepository.saveAll(Arrays.asList(est2, est1));
        cidadeRepository.saveAll(Arrays.asList(c2, c3, c1));
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
    }
}
