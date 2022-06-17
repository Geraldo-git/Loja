package com.gft.fict.Lojaficticia.controllers;

import com.gft.fict.Lojaficticia.entities.Loja;
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
@RequestMapping("loja")
public class LojaController {


    @Autowired
    private LojaService lojaService;
    @Autowired
    private LivroService livroService;

    // ----------------------------------------------------------------
    @RequestMapping(path = "novo")
    public ModelAndView novaLoja() {

        ModelAndView mv = new ModelAndView("loja/form.html");
        mv.addObject("loja", new Loja());
        return mv;
    }

    // ----------------------------------------------------------------
    @RequestMapping(method = RequestMethod.POST, path = "novo")
    public ModelAndView salvarLoja(@Valid Loja loja, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("loja/form.html");

        boolean novo = true;

        if(loja.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("loja", loja);
            return mv;
        }

        Loja lojaSalva = lojaService.salvarLoja(loja);
        if (novo) {
            mv.addObject("loja", new Loja());
        } else {
            mv.addObject("loja", lojaSalva);
        }

        mv.addObject("mensagem", "Loja salva com sucesso");

        return mv;
    }

    // ----------------------------------------------------------------
    @RequestMapping
    public ModelAndView listarLojas() {
        ModelAndView mv = new ModelAndView("loja/listar.html");

        mv.addObject("lista", lojaService.listarLoja());

        return mv;
    }

    // ----------------------------------------------------------------
    @RequestMapping("/editar")
    public ModelAndView editarLoja(@RequestParam Long id) {

        ModelAndView mv = new ModelAndView("loja/form.html");

        Loja loja;

        try {
            loja = lojaService.obterLoja(id);
        } catch (Exception e) {
            loja = new Loja();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("loja", loja);
        return mv;
    }

    // ----------------------------------------------------------------
    @RequestMapping("/excluir")
    public ModelAndView excluirLinguagem(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/loja");


        try {
            lojaService.excluirLoja(id);
            redirectAttributes.addFlashAttribute("mensagem", "Loja excluida com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir loja. " + e.getMessage());
            mv.addObject("mensagem", e.getMessage());
        }
        return mv;
    }

    }
