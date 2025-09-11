package com.notaRapida.services;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Fatura findById(Long id) {
        Fatura fatura = faturaRepository.findById(id)
                .orElseThrow(() -> new DadosInvalidosException("Fatura não encontrada"));

        return fatura;
    }

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

        byte[] pdfBytes = gerarPdfFatura(fatura);
        fatura.setArquivoPdf(pdfBytes);


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

    public List<FaturaResponseDTO> listarHistorico() {
        List<Fatura> faturas = faturaRepository.findAll();

        return faturas.stream().map(this::converterFaturaParaDTO) .toList();
    }

    private FaturaResponseDTO converterFaturaParaDTO(Fatura fatura) {
        return new FaturaResponseDTO(fatura.getId(),
                fatura.getNomeFatura(),
                fatura.getVencimento(),
                fatura.getObservacoes(),
                fatura.getValorTotal(),
                converterClienteParaDTO(fatura.getCliente()),
                converterItensParaDTO(fatura.getItens()));
    }

    private ClienteDTO converterClienteParaDTO(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteDTO(
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getEndereco(),
                cliente.getCidade(),
                cliente.getUf(),
                cliente.getCep(),
                cliente.getDocumento()
        );
    }

    private ItemFaturaRequestDTO converterItemParaDTO(ItemFatura item) {
        if (item == null) return null;
        return new ItemFaturaRequestDTO(
                item.getDescricao(),
                item.getQuantidade(),
                item.getValorUnitario()

        );
    }

    private List<ItemFaturaRequestDTO> converterItensParaDTO(List<ItemFatura> itens) {
        if (itens == null) return Collections.emptyList();
        return itens.stream()
                .map(this::converterItemParaDTO)
                .toList();
    }

    public byte[] gerarPdfFatura(Fatura fatura) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // --- Logo + Título ---
            //Image logo = new Image(ImageDataFactory.create("src/main/resources/static/assets/logoNotaRapida.png"));
            //logo.setWidth(100);
            //document.add(logo);

            Paragraph titulo = new Paragraph("FATURA")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18)
                    .setBold();
            document.add(titulo);
            document.add(new Paragraph("\n"));

            // --- Dados do Cliente ---
            Table tabelaCliente = new Table(2);
            tabelaCliente.addCell("Cliente:");
            tabelaCliente.addCell(fatura.getCliente().getNome());
            tabelaCliente.addCell("Documento:");
            tabelaCliente.addCell(fatura.getCliente().getDocumento());
            tabelaCliente.addCell("E-mail:");
            tabelaCliente.addCell(fatura.getCliente().getEmail());
            tabelaCliente.addCell("Endereço:");
            tabelaCliente.addCell(fatura.getCliente().getEndereco() + " - "
                    + fatura.getCliente().getCidade() + "/"
                    + fatura.getCliente().getUf());
            document.add(tabelaCliente);
            document.add(new Paragraph("\n"));

            // --- Dados da Fatura ---
            Table tabelaFatura = new Table(2);
            tabelaFatura.addCell("Número da Fatura:");
            tabelaFatura.addCell(fatura.getNomeFatura());
            tabelaFatura.addCell("Data de Vencimento:");
            tabelaFatura.addCell(fatura.getVencimento().toString());
            tabelaFatura.addCell("Observações:");
            tabelaFatura.addCell(fatura.getObservacoes() != null ? fatura.getObservacoes() : "-");
            document.add(tabelaFatura);
            document.add(new Paragraph("\n"));

            // --- Itens ---
            Table tabelaItens = new Table(new float[]{4, 1, 2, 2});
            tabelaItens.setWidth(UnitValue.createPercentValue(100));
            tabelaItens.addHeaderCell("Descrição");
            tabelaItens.addHeaderCell("Qtd");
            tabelaItens.addHeaderCell("Valor Unit.");
            tabelaItens.addHeaderCell("Subtotal");

            fatura.getItens().forEach(item -> {
                tabelaItens.addCell(item.getDescricao());
                tabelaItens.addCell(String.valueOf(item.getQuantidade()));
                tabelaItens.addCell("R$ " + item.getValorUnitario());
                tabelaItens.addCell("R$ " + item.getValorTotal());
            });

            document.add(tabelaItens);
            document.add(new Paragraph("\n"));

            // --- Total ---
            Paragraph total = new Paragraph("TOTAL: R$ " + fatura.getValorTotal())
                    .setBold()
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(14);
            document.add(total);

            // --- Rodapé ---
            document.add(new Paragraph("\n\nObrigado por escolher o NotaRápida!")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(10)
                    .setItalic());

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF da fatura", e);
        }
    }
}


