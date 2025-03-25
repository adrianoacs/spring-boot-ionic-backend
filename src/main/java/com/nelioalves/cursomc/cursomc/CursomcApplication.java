package com.nelioalves.cursomc.cursomc;

import com.nelioalves.cursomc.cursomc.domain.Categoria;
import com.nelioalves.cursomc.cursomc.domain.Cidade;
import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.domain.Endereco;
import com.nelioalves.cursomc.cursomc.domain.Estado;
import com.nelioalves.cursomc.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.cursomc.domain.Pedido;
import com.nelioalves.cursomc.cursomc.domain.Produto;
import com.nelioalves.cursomc.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Override
    public void run(String... args) throws Exception {

        var cat1 = new Categoria(null, "Informática");
        var cat2 = new Categoria(null, "Escritório");
        var cat3 = new Categoria(null, "Cama mesa e banho");
        var cat4 = new Categoria(null, "Eletrônicos");
        var cat5 = new Categoria(null, "Jardinagem");
        var cat6 = new Categoria(null, "Decoração");
        var cat7 = new Categoria(null, "Perfumaria");

        var p1 = new Produto(null, "Computador", 2000.00);
        var p2 = new Produto(null, "Impressora", 800.00);
        var p3 = new Produto(null, "Mouse", 80.00);
        var p4 = new Produto(null, "Mesa de escritório", 300.00);
        var p5 = new Produto(null, "Toalha", 50.00);
        var p6 = new Produto(null, "Colcha", 200.00);
        var p7 = new Produto(null, "TV true color", 1200.00);
        var p8 = new Produto(null, "Roçadeira", 800.00);
        var p9 = new Produto(null, "Abajour", 100.00);
        var p10 = new Produto(null, "Pendente", 80.00);
        var p11 = new Produto(null, "Shampoo", 80.00);

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

        p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        p4.getCategorias().addAll(Arrays.asList(cat2));
        p5.getCategorias().addAll(Arrays.asList(cat3));
        p6.getCategorias().addAll(Arrays.asList(cat3));
        p7.getCategorias().addAll(Arrays.asList(cat4));
        p8.getCategorias().addAll(Arrays.asList(cat5));
        p9.getCategorias().addAll(Arrays.asList(cat6));
        p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));

        cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProdutos().addAll(Arrays.asList(p2, p4));
        cat3.getProdutos().addAll(Arrays.asList(p5, p6));
        cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9, p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        var sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        var ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        var ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
        var pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);
        var pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
                sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        var ip1 = new ItemPedido(ped1, p1, 0.00, 2000.00, 1);
        var ip2 = new ItemPedido(ped1, p3, 0.00, 80.00, 2);
        var ip3 = new ItemPedido(ped2, p2, 100.00, 800.00, 1);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
        estadoRepository.saveAll(Arrays.asList(est2, est1));
        cidadeRepository.saveAll(Arrays.asList(c2, c3, c1));
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
