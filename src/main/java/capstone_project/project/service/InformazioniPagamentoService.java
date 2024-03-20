package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.InformazioniPagamento;
import capstone_project.project.repository.InformazioniPagamentoRepository;
import capstone_project.project.request.InformazioniPagamentoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InformazioniPagamentoService {

    @Autowired
    private InformazioniPagamentoRepository informazioniPagamentoRepository;

    public Page<InformazioniPagamento> getAllInformazioniPagamenti(Pageable pageable){
        return informazioniPagamentoRepository.findAll(pageable);
    }

    public InformazioniPagamento getInformazioniPagamentoById(int id) throws NotFoundException {
        return informazioniPagamentoRepository.findById(id).orElseThrow(()->new NotFoundException("L'informazione con il pagamento con il numero = " + id + " non è stata trovato"));
    }

    public InformazioniPagamento createInformazioniPagamento(InformazioniPagamentoRequest ipr){
        InformazioniPagamento informazioniPagamento = new InformazioniPagamento();

       informazioniPagamento.setCvv(ipr.getCvv());
       informazioniPagamento.setNumeroCarta(ipr.getNumeroCarta());
       informazioniPagamento.setDataDiScadenza(ipr.getDataDiScadenza());
       informazioniPagamento.setNomeDelTitolare(ipr.getNomeDelTitolare());

       return informazioniPagamentoRepository.save(informazioniPagamento);
    }

    public InformazioniPagamento updateInformazioniPagamento(int id, InformazioniPagamentoRequest ipr){
        InformazioniPagamento informazioniPagamento = getInformazioniPagamentoById(id);

        informazioniPagamento.setCvv(ipr.getCvv());
        informazioniPagamento.setNumeroCarta(ipr.getNumeroCarta());
        informazioniPagamento.setDataDiScadenza(ipr.getDataDiScadenza());
        informazioniPagamento.setNomeDelTitolare(ipr.getNomeDelTitolare());

        return informazioniPagamentoRepository.save(informazioniPagamento);
    }

    public void deleteInformazioniPagamento(int id) throws NotFoundException {
        InformazioniPagamento informazioniPagamento = getInformazioniPagamentoById(id);
        informazioniPagamentoRepository.delete(informazioniPagamento);
    }
}
