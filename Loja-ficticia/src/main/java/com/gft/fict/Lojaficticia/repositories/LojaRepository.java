package com.gft.fict.Lojaficticia.repositories;

import com.gft.fict.Lojaficticia.entities.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long> {
}
