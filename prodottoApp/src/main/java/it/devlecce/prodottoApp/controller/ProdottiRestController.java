package it.devlecce.prodottoApp.controller;

import it.devlecce.prodottoApp.avviso.ProdottoNonTrovato;
import it.devlecce.prodottoApp.model.Prodotto;
import it.devlecce.prodottoApp.persistence.ProdottiRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ProdottiRestController {
    private ProdottiRepository repository;

    ProdottiRestController(ProdottiRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/prodotti")
    private List<Prodotto> tuttiProdotti(){
        return repository.findAll();
    }

    @GetMapping("/prodotto/{id}")
    public Prodotto trovaProdottoConID(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProdottoNonTrovato(id));
    }

    @GetMapping("/prodottofindnome")
    public List<Prodotto> trovaProdottoConNome(@RequestParam(name = "nome") String nome) {
        return repository.findByNome(nome);
    }

    @PostMapping("/prodotto")
    public Prodotto inserisciNuovoProdotto(@RequestBody Prodotto nuovoProdotto) {
        return repository.save(nuovoProdotto);
    }

    @PutMapping("/prodotto/{id}")
    public Prodotto aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto prodotto) {
        return repository.save(prodotto);
    }

    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id){
        repository.deleteById(id);
    }

    @GetMapping("/prodotti/ricercaprezzo")
    public List<Prodotto> ricercaPerPrezzo(@RequestParam(name="min") float min,
                                           @RequestParam(name="max") float max){
        return repository.findByprezzoBetween(min,max);
    }

    @GetMapping("/prodotti/ricercadataregistrazione")
    public List<Prodotto> ricercaPerDataDiRegistrazione(@RequestParam(name="datada") @DateTimeFormat(pattern= "dd-MM-yyyy")
                                                                Date datada,
                                                        @RequestParam(name="dataa") @DateTimeFormat(pattern= "dd-MM-yyyy")
                                                                Date dataa){
        return repository.findBydataacquistoBetween(datada,dataa);
    }

    @GetMapping("/prodotti/quantitainferiore")
    public List<Prodotto> ricercaPerQuantitaInferirore(@RequestParam(name="max") float max){
        return repository.findByquantitaLessThan(max);
    }

}