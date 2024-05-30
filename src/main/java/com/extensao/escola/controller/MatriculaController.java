package com.extensao.escola.controller;

import com.extensao.escola.dto.MatriculaRecordDto;
import com.extensao.escola.model.MatriculaModel;
import com.extensao.escola.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/matricula")
public class MatriculaController {

    final MatriculaService matriculaService;

    @GetMapping("/form")
    public String showMatriculaForm(Model model) {
        model.addAttribute("matriculaModel", new MatriculaModel());
        return "matricula";
    }

    @PostMapping("/save")
    public String saveSubscription(@ModelAttribute("matriculaModel") @Valid MatriculaRecordDto matriculaRecordDto) {
        var matriculaModel = new MatriculaModel();
        BeanUtils.copyProperties(matriculaRecordDto, matriculaModel);
        matriculaService.save(matriculaModel);
        return "redirect:/matricula/list";
    }

    @GetMapping("/list")
    public String showMatriculaList(Model model) {
        List<MatriculaModel> matriculas = matriculaService.getStudentsList();
        model.addAttribute("matriculas", matriculas);
        return "matricula-list";
    }

    @GetMapping("/edit/{subscriptionNumber}")
    public String showEditForm(@PathVariable Long subscriptionNumber, Model model) {
        MatriculaModel matriculaModel = matriculaService.findById(subscriptionNumber);
        if (matriculaModel != null) {
            model.addAttribute("matriculaModel", matriculaModel);
            return "matricula-edit";
        } else {
            return "redirect:/matricula/list";
        }
    }

    @PostMapping("/update/{subscriptionNumber}")
    public String updateSubscription(@PathVariable Long subscriptionNumber, @ModelAttribute("matriculaModel") @Valid MatriculaRecordDto updatedMatriculaDto) {
        var existingStudent = matriculaService.findById(subscriptionNumber);
        if (existingStudent != null) {
            BeanUtils.copyProperties(updatedMatriculaDto, existingStudent);
            matriculaService.save(existingStudent);
        }
        return "redirect:/matricula/list";
    }

    @DeleteMapping("/delete/{subscriptionNumber}")
    public String deleteSubscription(@PathVariable Long subscriptionNumber) {
        matriculaService.delete(subscriptionNumber);
        return "redirect:/matricula/list";
    }
}
