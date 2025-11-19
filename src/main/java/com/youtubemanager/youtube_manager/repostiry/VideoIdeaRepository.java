package com.youtubemanager.youtube_manager.repostiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youtubemanager.youtube_manager.model.VideoIdea;

import java.util.List;

@Repository
public interface VideoIdeaRepository extends JpaRepository<VideoIdea, Long> {
    
    // Spring Data JPA crea automáticamente estos métodos:
    // - save() → Guardar una idea
    // - findAll() → Obtener todas las ideas
    // - findById() → Buscar por ID
    // - deleteById() → Eliminar por ID
    
    // Método personalizado: buscar ideas por estado
    List<VideoIdea> findByStatus(String status);
    
    List<VideoIdea> findByTitleContainingIgnoreCase(String title);
}