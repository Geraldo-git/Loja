package com.gft.fict.Lojaficticia.services;

import com.gft.fict.Lojaficticia.entities.Loja;
import com.gft.fict.Lojaficticia.repositories.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Service
public class LojaService {

    @Autowired
    LojaRepository lojaRepository;
    @Autowired
    LivroService livroService;

    // ----------------------------------------------------------------
    public Loja salvarLoja(Loja loja) {

        return lojaRepository.save(loja);
    }

    // ----------------------------------------------------------------
    public List<Loja> listarLoja(){

        return lojaRepository.findAll();
    }


    //----------------------------------------------------------------
    public Loja obterLoja(Long id) throws Exception {

        Optional<Loja> loja = lojaRepository.findById(id);
        if(loja.isEmpty()) {
            throw new Exception("Loja não encontrada");
        }

        return  loja.get();
    }

    // ----------------------------------------------------------------
    public Loja excluirLoja(Long id) {
        lojaRepository.deleteById(id);
        return null;
    }

    public void ajustaPatrimonio(){

        double prt =livroService.calculaValor();


    }
}
