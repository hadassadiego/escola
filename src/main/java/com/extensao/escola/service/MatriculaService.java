package com.extensao.escola.service;

import com.extensao.escola.model.MatriculaModel;
import com.extensao.escola.repository.MatriculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatriculaService {

    final MatriculaRepository matriculaRepository; //instead of using @Autowired

    public MatriculaModel save(MatriculaModel matriculaModel) {
        return matriculaRepository.save(matriculaModel);
    }
    
    public List<MatriculaModel> getStudentsList() {
        return matriculaRepository.findAll();
    }

    public List<MatriculaModel> findStudentsByName(String name) {
        return matriculaRepository.findByName(name);
    }

    public List<MatriculaModel> findStudentsBySubscription(String subscription) {
        return matriculaRepository.findBySubscription(subscription);
    }

    public List<MatriculaModel> findStudentsByNameAndSubscription(String name, String subscription) {
        return matriculaRepository.findByNameAndSubscription(name, subscription);
    }

    public Long getStudentsCount() {
        return matriculaRepository.count();
    }

    public Long getStudentsCountByName(String name) {
        return matriculaRepository.countByName(name);
    }

    public Long getStudentsCountBySubscription(String subscription) {
        return matriculaRepository.countBySubscription(subscription);
    }

    public Long getStudentsCountByNameAndSubscription(String name, String subscription) {
        return matriculaRepository.countByNameAndSubscription(name, subscription);
    }

    public MatriculaModel findById(Long subscriptionNumber) {
        Optional<MatriculaModel> studentOptional = matriculaRepository.findById(subscriptionNumber);
        return studentOptional.orElse(null);
    }

    public void delete(Long subscriptionNumber) {
        matriculaRepository.deleteById(subscriptionNumber);
    }

    public boolean existsById(Long subscriptionNumber) {
        return matriculaRepository.existsById(subscriptionNumber);
    }
}
