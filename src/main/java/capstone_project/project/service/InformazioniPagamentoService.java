package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.InformazioniPagamento;
import capstone_project.project.repository.InformazioniPagamentoRepository;
import capstone_project.project.request.InformazioniPagamentoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class InformazioniPagamentoService {

    // Iniezione della dipendenza del repository per le informazioni di pagamento
    @Autowired
    private InformazioniPagamentoRepository informazioniPagamentoRepository;

    // Metodo per ottenere tutte le informazioni di pagamento paginate
    public Page<InformazioniPagamento> getAllInformazioniPagamenti(Pageable pageable){
        return informazioniPagamentoRepository.findAll(pageable);
    }

    // Metodo per ottenere le informazioni di pagamento tramite il loro ID
    public InformazioniPagamento getInformazioniPagamentoById(int id) throws NotFoundException {
        return informazioniPagamentoRepository.findById(id).orElseThrow(()->new NotFoundException("L'informazione di pagamento con ID " + id + " non è stata trovata"));
    }

    // Metodo per creare nuove informazioni di pagamento
    public InformazioniPagamento createInformazioniPagamento(InformazioniPagamentoRequest ipr){
        // Creare una nuova istanza di InformazioniPagamento
        InformazioniPagamento informazioniPagamento = new InformazioniPagamento();

        // Impostare i dettagli delle informazioni di pagamento con quelli forniti nella richiesta
        informazioniPagamento.setCvv(ipr.getCvv());
        informazioniPagamento.setNumeroCarta(ipr.getNumeroCarta());
        informazioniPagamento.setDataDiScadenza(ipr.getDataDiScadenza());
        informazioniPagamento.setNomeDelTitolare(ipr.getNomeDelTitolare());

        // Salvare le informazioni di pagamento nel repository e restituirle
        return informazioniPagamentoRepository.save(informazioniPagamento);
    }

    // Metodo per aggiornare le informazioni di pagamento esistenti
    public InformazioniPagamento updateInformazioniPagamento(int id, InformazioniPagamentoRequest ipr){
        // Ottenere le informazioni di pagamento esistenti tramite il loro ID
        InformazioniPagamento informazioniPagamento = getInformazioniPagamentoById(id);

        // Aggiornare i dettagli delle informazioni di pagamento con quelli forniti nella richiesta
        informazioniPagamento.setCvv(ipr.getCvv());
        informazioniPagamento.setNumeroCarta(ipr.getNumeroCarta());
        informazioniPagamento.setDataDiScadenza(ipr.getDataDiScadenza());
        informazioniPagamento.setNomeDelTitolare(ipr.getNomeDelTitolare());

        // Salvare le informazioni di pagamento aggiornate nel repository e restituirle
        return informazioniPagamentoRepository.save(informazioniPagamento);
    }

    // Metodo per eliminare le informazioni di pagamento tramite il loro ID
    public void deleteInformazioniPagamento(int id) throws NotFoundException {
        // Ottenere le informazioni di pagamento tramite il loro ID
        InformazioniPagamento informazioniPagamento = getInformazioniPagamentoById(id);
        // Eliminare le informazioni di pagamento dal repository
        informazioniPagamentoRepository.delete(informazioniPagamento);
    }
}
