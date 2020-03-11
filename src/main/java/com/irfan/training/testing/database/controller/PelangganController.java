package com.irfan.training.testing.database.controller;

import com.irfan.training.testing.database.dao.PelangganDao;
import com.irfan.training.testing.database.entity.Pelanggan;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller @RequestMapping("/")
public class PelangganController {
    
    @Autowired private PelangganDao pelangganDao;
    
    @GetMapping("/list")
    public ModelMap dataPelanggan(Pageable page){
        return new ModelMap("daftarPelanggan", pelangganDao.findAll(page));
    }
    
    @GetMapping("/form")
    public ModelMap tampilkanForm(@RequestParam(name = "id", required = false) Pelanggan p){
        if(p == null){
            p = new Pelanggan();
        }
        
        return new ModelMap(p);
    }
    
    @PostMapping("/form")
    public String prosesForm(@ModelAttribute @Valid Pelanggan p, BindingResult err, SessionStatus status, RedirectAttributes redirectAttrs){
        if(err.hasErrors()) {
            return "pelanggan/form";
        }
        pelangganDao.save(p);
        status.setComplete();
        redirectAttrs.addFlashAttribute("message", "Data pelanggan berhasil disimpan");
        return "redirect:list";
    }
}
