package com.extensao.escola.controller;

import com.extensao.escola.dto.MatriculaRecordDto;
import com.extensao.escola.model.MatriculaModel;
import com.extensao.escola.service.MatriculaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/matricula")
public class MatriculaController {

    final MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaModel>> getStudentList(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "subscription", required = false) String subscription) {
        List<MatriculaModel> students;

        if (name != null && subscription != null) {
            students = matriculaService.findStudentsByNameAndSubscription(name, subscription);
        } else if (name != null) {
            students = matriculaService.findStudentsByName(name);
        } else if (subscription != null) {
            students = matriculaService.findStudentsBySubscription(subscription);
        } else {
            students = matriculaService.getStudentsList();
        }

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getEmployeeCount(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "subscription", required = false) String subscription) {
        Long countStudents;

        if (name != null && subscription != null) {
            countStudents = matriculaService.getStudentsCountByNameAndSubscription(name, subscription);
        } else if (name != null) {
            countStudents = matriculaService.getStudentsCountByName(name);
        } else if (subscription != null) {
            countStudents = matriculaService.getStudentsCountBySubscription(subscription);
        } else {
            countStudents = matriculaService.getStudentsCount();
        }
        return ResponseEntity.status(HttpStatus.OK).body(countStudents);
    }


    @PutMapping("/{subscriptionNumber}")
    public ResponseEntity<MatriculaModel> updateEmployee(
            @PathVariable Long subscriptionNumber,
            @RequestBody @Valid MatriculaRecordDto updatedMatriculaDto) {

        var existingStudent = matriculaService.findById(subscriptionNumber);

        if (existingStudent == null) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(updatedMatriculaDto, existingStudent);

        var updatedStudent = matriculaService.save(existingStudent);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{subscriptionNumber}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long subscriptionNumber) {
        if (matriculaService.existsById(subscriptionNumber)) {
            matriculaService.delete(subscriptionNumber);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MatriculaModel> saveSubscription(@RequestBody @Valid MatriculaRecordDto matriculaRecordDto) {
        var matriculaModel = new MatriculaModel();
        BeanUtils.copyProperties(matriculaRecordDto, matriculaModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.save(matriculaModel));
    }

}
