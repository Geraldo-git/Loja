package com.gft.fict.Lojaficticia.repositories;

import com.gft.fict.Lojaficticia.entities.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideogameRepository extends JpaRepository<VideoGame, Long> {
}
