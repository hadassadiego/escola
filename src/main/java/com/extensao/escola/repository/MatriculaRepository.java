package com.extensao.escola.repository;

import com.extensao.escola.model.MatriculaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<MatriculaModel, Long> {

    List<MatriculaModel> findByName(String name);

    List<MatriculaModel> findBySubscription(String subscription);

    List<MatriculaModel> findByNameAndSubscription(String name, String subscription);

    Long countByName(String name);

    Long countBySubscription(String subscription);

    Long countByNameAndSubscription(String name, String subscription);
}
