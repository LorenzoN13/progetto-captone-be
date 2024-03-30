package capstone_project.project.service;

import capstone_project.project.exception.NotFoundException;
import capstone_project.project.model.DettagliPagamento;
import capstone_project.project.model.Indirizzo;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.DettagliPagamentoRepository;
import capstone_project.project.request.DettagliPagamentoRequest;
import capstone_project.project.request.IndirizzoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

// Annotazione per indicare che questa classe è un servizio Spring
@Service
public class DettagliPagamentoService {

    // Iniezione della dipendenza del repository per i dettagli di pagamento
    @Autowired
    private DettagliPagamentoRepository dettagliPagamentoRepository;

    // Metodo per ottenere tutti i dettagli di pagamento paginati
    public Page<DettagliPagamento> getAllDettagliPagamenti(Pageable pageable){
        return dettagliPagamentoRepository.findAll(pageable);
    }

    // Metodo per ottenere un dettaglio di pagamento tramite il suo ID
    public DettagliPagamento getDettagliPagamentoById(int id) throws NotFoundException {
        return dettagliPagamentoRepository.findById(id).orElseThrow(()->new NotFoundException("Il dettaglio di pagamento con ID " + id + " non è stato trovato"));
    }

    // Metodo per creare un nuovo dettaglio di pagamento
    public DettagliPagamento createDettagliPagamento(DettagliPagamentoRequest dpr){

        // Creare una nuova istanza di DettagliPagamento
        DettagliPagamento dettagliPagamento = new DettagliPagamento();

        // Impostare i dettagli del pagamento
        dettagliPagamento.setMetodoPagamento(dpr.getMetodoPagamento());
        dettagliPagamento.setStato(dpr.getStato());

        // Salvare il dettaglio di pagamento nel repository e restituirlo
        return dettagliPagamentoRepository.save(dettagliPagamento);
    }

    // Metodo per aggiornare un dettaglio di pagamento esistente
    public DettagliPagamento updateDettagliPagamento(int id, DettagliPagamentoRequest dpr){
        // Ottenere il dettaglio di pagamento esistente tramite il suo ID
        DettagliPagamento dettagliPagamento = getDettagliPagamentoById(id);

        // Aggiornare i dettagli del pagamento con quelli forniti nella richiesta
        dettagliPagamento.setMetodoPagamento(dpr.getMetodoPagamento());
        dettagliPagamento.setStato(dpr.getStato());

        // Salvare il dettaglio di pagamento aggiornato nel repository e restituirlo
        return dettagliPagamentoRepository.save(dettagliPagamento);
    }

    // Metodo per eliminare un dettaglio di pagamento tramite il suo ID
    public void deleteDettagliPagamento(int id) throws NotFoundException {
        // Ottenere il dettaglio di pagamento tramite il suo ID
        DettagliPagamento dettagliPagamento = getDettagliPagamentoById(id);
        // Eliminare il dettaglio di pagamento dal repository
        dettagliPagamentoRepository.delete(dettagliPagamento);
    }
}
