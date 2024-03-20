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

@Service
public class DettagliPagamentoService {

    @Autowired
    private DettagliPagamentoRepository dettagliPagamentoRepository;

    public Page<DettagliPagamento> getAllDettagliPagamenti(Pageable pageable){
        return dettagliPagamentoRepository.findAll(pageable);
    }

    public DettagliPagamento getDettagliPagamentoById(int id) throws NotFoundException {
        return dettagliPagamentoRepository.findById(id).orElseThrow(()->new NotFoundException("Il dettaglio con il pagamento con il numero = " + id + " non è stata trovato"));
    }

    public DettagliPagamento createDettagliPagamento(DettagliPagamentoRequest dpr){

        DettagliPagamento dettagliPagamento = new DettagliPagamento();

        dettagliPagamento.setMetodoPagamento(dpr.getMetodoPagamento());
        dettagliPagamento.setStato(dpr.getStato());

        return dettagliPagamentoRepository.save(dettagliPagamento);
    }

    public DettagliPagamento updateDettagliPagamento(int id, DettagliPagamentoRequest dpr){
        DettagliPagamento dettagliPagamento = getDettagliPagamentoById(id);

        dettagliPagamento.setMetodoPagamento(dpr.getMetodoPagamento());
        dettagliPagamento.setStato(dpr.getStato());

        return dettagliPagamentoRepository.save(dettagliPagamento);
    }

    public void deleteDettagliPagamento(int id) throws NotFoundException {
        DettagliPagamento dettagliPagamento = getDettagliPagamentoById(id);
        dettagliPagamentoRepository.delete(dettagliPagamento);
    }
}
