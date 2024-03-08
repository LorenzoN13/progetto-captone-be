package capstone_project.project.service;

import capstone_project.project.model.Prodotto;
import capstone_project.project.model.Utente;
import capstone_project.project.repository.CategoriaRepository;
import capstone_project.project.repository.ProdottoRepository;
import capstone_project.project.request.CreaProdottoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Prodotto createProdotto(CreaProdottoRequest cpr){

    }


}
