package com.notaRapida.services;

import com.notaRapida.dtos.FaturaRequestDTO;
import com.notaRapida.dtos.FaturaResponseDTO;
import com.notaRapida.models.Cliente;
import com.notaRapida.models.Fatura;
import com.notaRapida.models.ItemFatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public FaturaResponseDTO createFatura(FaturaRequestDTO requestDTO) {
        // 1. Verifica se o cliente já existe pelo nome + email
        Cliente cliente = clienteRepository.findByNomeAndEmail(
                requestDTO.getNomeCliente(),
                requestDTO.getEmailCliente()
        ).orElseGet(() -> {
            // 2. Se não existir, cria um novo
            Cliente novo = new Cliente();
            novo.setNome(requestDTO.getNomeCliente());
            novo.setEmail(requestDTO.getEmailCliente());
            novo.setEndereco(requestDTO.getEnderecoCliente());
            novo.setCidade(requestDTO.getCidadeCliente());
            novo.setUf(requestDTO.getUfCliente());
            novo.setCep(requestDTO.getCepCliente());
            return clienteRepository.save(novo); //?
        });

        // 3. Cria a fatura
        Fatura fatura = new Fatura();
        fatura.setNomeFatura(requestDTO.getNomeFatura());
        fatura.setVencimento(requestDTO.getVencimento());
        fatura.setObservacoes(requestDTO.getObservacoes());
        fatura.setCliente(cliente);

        // 4. Iterar os itens
        List<ItemFatura> itens = new ArrayList<>();
        for (ItemFaturaRequestDTO dto : requestDTO.getItens()) {
            ItemFatura item = new ItemFatura();
            item.setDescricao(dto.getDescricao());
            item.setQuantidade(dto.getQuantidade());
            item.setValorUnitario(dto.getValorUnitario());
            item.setValorTotal(dto.getValorUnitario() * dto.getQuantidade());
            item.setFatura(fatura);
            itens.add(item);
        }

        fatura.setItens(itens);


    }
}
