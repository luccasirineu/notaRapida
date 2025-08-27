package com.notaRapida.services;

import com.notaRapida.dtos.ClienteDTO;
import com.notaRapida.dtos.FaturaRequestDTO;
import com.notaRapida.dtos.FaturaResponseDTO;
import com.notaRapida.dtos.ItemFaturaRequestDTO;
import com.notaRapida.exceptions.DadosInvalidosException;
import com.notaRapida.models.Cliente;
import com.notaRapida.models.Fatura;
import com.notaRapida.models.ItemFatura;
import com.notaRapida.repositories.ClienteRepository;
import com.notaRapida.repositories.FaturaRepository;
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

        if (requestDTO.getClienteDTO().getDocumento() == null || requestDTO.getClienteDTO().getDocumento().isBlank()){
            throw new DadosInvalidosException("DADOS INVALIDOS OU INCOMPLETOS");
        }

        Cliente cliente = clienteRepository.findByDocumento(requestDTO.getClienteDTO().getDocumento())
        .orElseGet(() -> {

            Cliente novo = new Cliente();
            novo.setNome(requestDTO.getClienteDTO().getNome());
            novo.setEmail(requestDTO.getClienteDTO().getEmail());
            novo.setDocumento(requestDTO.getClienteDTO().getDocumento());
            novo.setEndereco(requestDTO.getClienteDTO().getEndereco());
            novo.setCidade(requestDTO.getClienteDTO().getCidade());
            novo.setUf(requestDTO.getClienteDTO().getUf());
            novo.setCep(requestDTO.getClienteDTO().getCep());
            return clienteRepository.save(novo);
        });

        Fatura fatura = new Fatura();
        fatura.setNomeFatura(requestDTO.getNomeFatura());
        fatura.setVencimento(requestDTO.getVencimento());
        fatura.setObservacoes(requestDTO.getObservacoes());
        fatura.setValorTotal(requestDTO.getValorTotal());
        fatura.setCliente(cliente);


        List<ItemFatura> itens = new ArrayList<>();

        for (ItemFaturaRequestDTO dto : requestDTO.getItens()) {
            ItemFatura item = new ItemFatura();
            item.setDescricao(dto.getDescricao());
            item.setQuantidade(dto.getQuantidade());
            item.setValorUnitario(dto.getValorUnitario());
            item.setValorTotal(dto.getValorUnitario() * dto.getQuantidade());
            itens.add(item);
        }

        fatura.setItens(itens);

        Fatura faturaSaved = faturaRepository.save(fatura);



        ClienteDTO clienteDTO = new ClienteDTO(
                faturaSaved.getCliente().getNome(),
                faturaSaved.getCliente().getEmail(),
                faturaSaved.getCliente().getEndereco(),
                faturaSaved.getCliente().getCidade(),
                faturaSaved.getCliente().getUf(),
                faturaSaved.getCliente().getCep(),
                faturaSaved.getCliente().getDocumento()
        );

        List<ItemFaturaRequestDTO> itensDTO = new ArrayList<>();

        for (ItemFatura item : faturaSaved.getItens()) {
            itensDTO.add(new ItemFaturaRequestDTO(
                    item.getDescricao(),
                    item.getQuantidade(),
                    item.getValorUnitario()
            ));
        }

        return new FaturaResponseDTO(faturaSaved.getId(), faturaSaved.getNomeFatura(), faturaSaved.getVencimento(),
                faturaSaved.getObservacoes(), faturaSaved.getValorTotal(), clienteDTO , itensDTO);



    }
}
