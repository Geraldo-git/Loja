package com.gft.fict.Lojaficticia.controllers;

import com.gft.fict.Lojaficticia.entities.Livro;
import com.gft.fict.Lojaficticia.services.LivroService;
import com.gft.fict.Lojaficticia.services.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    LojaService lojaService;


    // ----------------------------------------------------------------
    @RequestMapping(path = "editar")
    public ModelAndView editarLivro(@RequestParam(required = false) Long id) {

        ModelAndView mv = new ModelAndView("livro/form.html");

        Livro livro;
        if (id == null) {
            livro = new Livro();
        } else {
            try {
                livro = livroService.obterLivro(id);
            } catch (Exception e) {
                livro = new Livro();
                mv.addObject("mensagem", e.getMessage());
            }
        }
        mv.addObject("livro", livro);
        mv.addObject("listaLojas", lojaService.listarLoja());
        return mv;
    }

    // ----------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST, path = "editar")
    public ModelAndView salvarLivro(@Valid Livro livro, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("livro/form.html");

        boolean novo = true;
        if (livro.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("livro", livro);
            return mv;
        }

        livroService.salvarLivro(livro);

        if (novo) {
            mv.addObject("livro", new Livro());
        } else {
            mv.addObject("livro", livro);
        }

        mv.addObject("mensagem", "Livro salvo com sucesso");
        mv.addObject("listaLojas", lojaService.listarLoja());

        return mv;
    }

    // ----------------------------------------------------------------

   @RequestMapping
    public ModelAndView listarLivros() {
        ModelAndView mv = new ModelAndView("livro/listar.html");

        mv.addObject("lista", livroService.listarLivro());

        return mv;
    }

    // ----------------------------------------------------------------
   @RequestMapping("/excluir")
    public ModelAndView excluirLivro(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/livro");

        try {
            livroService.excluirLivro(id);
            redirectAttributes.addFlashAttribute("mensagem", "Livro excluido do sistema.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir livro. " + e.getMessage());
            mv.addObject("mensage", e.getMessage());
        }

        return mv;
    }

}