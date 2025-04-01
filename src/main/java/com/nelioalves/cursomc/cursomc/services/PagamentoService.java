package com.nelioalves.cursomc.cursomc.services;

import com.nelioalves.cursomc.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.cursomc.domain.Produto;
import com.nelioalves.cursomc.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.cursomc.repositories.ProdutoRepository;
import com.nelioalves.cursomc.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
public class PagamentoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public Produto find(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException(
                        String.format("Não foi encontrada %s para o id '%s'", Produto.class.getName(), id)));
    }

    @Transactional
    public Pagamento insert(Pagamento obj){
        obj.setId(null);
        return pagamentoRepository.save(obj);
    }

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        var calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(calendar.getTime());
    }

}
