package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Cliente;
import com.nelioalves.cursomc.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.cursomc.domain.Pedido;
import com.nelioalves.cursomc.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public Pedido find(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(
                        String.format("Não foi encontrada %s para o id '%s'", Pedido.class.getName(), id)));
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstance(new Date());
        obj.getPagamento().setPedido(obj);

        if(obj.getPagamento() instanceof PagamentoComBoleto){
            var pagto = (PagamentoComBoleto) obj.getPagamento();
            pagto.setEstado(EstadoPagamento.PENDENTE);
            pagamentoService.preencherPagamentoComBoleto(pagto, obj.getInstance());
        }
        obj = pedidoRepository.save(obj);

        for (ItemPedido item : obj.getItens()){
            item.setDesconto(0.0);
            var produto = produtoService.find(item.getProduto().getId());
            item.setProduto(produto);
            item.setPreco(produto.getPreco());
            item.setPedido(obj);
        }

        itemPedidoRepository.saveAll(obj.getItens());

        return obj;
    }
}
