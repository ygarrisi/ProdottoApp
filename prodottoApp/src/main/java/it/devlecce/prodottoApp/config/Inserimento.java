package it.devlecce.prodottoApp.config;


import it.devlecce.prodottoApp.model.Prodotto;
import it.devlecce.prodottoApp.persistence.ProdottiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class Inserimento {
    @Bean
    CommandLineRunner inserimentoProdotti(ProdottiRepository repository) {
        return args -> {
            SimpleDateFormat data = new SimpleDateFormat("dd-MM-yyyy");

            Date dataAcquisto = data.parse("02-04-2022");
            Date dataScadenza = data.parse("07-04-2022");
            Prodotto p1 = new Prodotto("Merluzzo",dataAcquisto,dataScadenza,18.90f,50.0f);

            dataAcquisto = data.parse("02-04-2022");
            dataScadenza = data.parse("07-04-2022");
            Prodotto p2 = new Prodotto("Mele",dataAcquisto,dataScadenza,0.50f,50.0f);

            dataAcquisto = data.parse("02-04-2022");
            dataScadenza = data.parse("07-06-2022");
            Prodotto p3 = new Prodotto("Cereali",dataAcquisto,dataScadenza,2.50f,300.0f);

            dataAcquisto = data.parse("02-04-2022");
            dataScadenza = data.parse("07-04-2022");
            Prodotto p4 = new Prodotto("Latte",dataAcquisto,dataScadenza,0.80f,200.0f);

            List<Prodotto> prodotto = new ArrayList<>();
            prodotto.add(p1);
            prodotto.add(p2);
            prodotto.add(p3);
            prodotto.add(p4);
            repository.saveAll(prodotto);
        };
    }
}