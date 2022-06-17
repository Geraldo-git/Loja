package com.gft.fict.Lojaficticia.repositories;

import com.gft.fict.Lojaficticia.entities.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {


}
