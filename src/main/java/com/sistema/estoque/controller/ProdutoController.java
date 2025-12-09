package com.sistema.estoque.controller;

import com.sistema.estoque.model.Produto;
import com.sistema.estoque.repository.ProdutoRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepo;

    public ProdutoController(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;

    }

    private List<String> categoriasPetshop() {
        return List.of(
                "Alimentos Secos",
                "Alimentos Úmidos",
                "Brinquedos",
                "Remédios",
                "Acessórios para Passeio",
                "Petiscos e Snacks",
                "Higiene",
                "Controle de Pragas",
                "Conforto",
                "Vestuário"
        );
    }

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepo.findAll());
        return "produtos";
    }

    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", categoriasPetshop());
        return "novoproduto";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute("produto") Produto produto) {
        produtoRepo.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", categoriasPetshop());
        return "editarproduto";
    }

    @PostMapping("/atualizar")
    public String atualizarProduto(@ModelAttribute("produto") Produto produto) {
        produtoRepo.save(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable Long id) {
        produtoRepo.deleteById(id);
        return "redirect:/produtos";
    }
}
