package com.sistema.estoque;

import com.sistema.estoque.model.Produto;
import com.sistema.estoque.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProdutoRepository produtoRepository;

    public DataLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (produtoRepository.count() > 0) return;

        List<Produto> produtosExemplo = Arrays.asList(
                new Produto("Ração Golden Dog Adulto", "Alimentos Secos", BigDecimal.valueOf(120.0), 50, "Ração completa para cães adultos."),
                new Produto("Ração Úmida Wysks Sabor Frango", "Alimentos Úmidos", BigDecimal.valueOf(30.0), 40, "Ração úmida sabor frango para gatos."),
                new Produto("Bola de Borracha", "Brinquedos", BigDecimal.valueOf(25.0), 20, "Bola resistente para cães brincarem."),
                new Produto("Antipulgas Frontline", "Remédios", BigDecimal.valueOf(80.0), 15, "Tratamento antipulgas e carrapatos para cães e gatos."),
                new Produto("Coleira para cães ajustável", "Acessórios", BigDecimal.valueOf(35.0), 25, "Coleira confortável para passear com cachorros de todos os portes."),
                new Produto("Petisco FriDog", "Petiscos e Snacks", BigDecimal.valueOf(15.0), 60, "Biscoitos sabor carne para cães."),
                new Produto("Shampoo CatSuav", "Higiene", BigDecimal.valueOf(25.0), 30, "Shampoo suave para gatos com pele sensível."),
                new Produto("Cama Pet Confort", "Conforto", BigDecimal.valueOf(120.0), 10, "Cama macia para cães e gatos."),
                new Produto("Suéter Pet Fashion", "Vestuário", BigDecimal.valueOf(50.0), 15, "Suéter estiloso para animais de pequeno porte.")
        );

        produtoRepository.saveAll(produtosExemplo);
    }
}
