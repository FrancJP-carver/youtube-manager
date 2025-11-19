package com.youtubemanager.youtube_manager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youtubemanager.youtube_manager.model.VideoIdea;
import com.youtubemanager.youtube_manager.repostiry.VideoIdeaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ideas")
public class VideoIdeaController {

    @Autowired
    private VideoIdeaRepository videoIdeaRepository;

    // GET: Obtener todas las ideas
    @GetMapping
    public List<VideoIdea> getAllIdeas() {
        return videoIdeaRepository.findAll();
    }

    // GET: Obtener una idea por ID
    @GetMapping("/{id}")
    public ResponseEntity<VideoIdea> getIdeaById(@PathVariable Long id) {
        Optional<VideoIdea> idea = videoIdeaRepository.findById(id);
        return idea.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    //Busqueda por titulo
    @GetMapping("/search/title")
    public List<VideoIdea> searchByTitle(@RequestParam String title) {
        return videoIdeaRepository.findByTitleContainingIgnoreCase(title);
    }

    // POST: Crear una nueva idea
    @PostMapping
    public VideoIdea createIdea(@RequestBody VideoIdea idea) {
        return videoIdeaRepository.save(idea);
    }

    // PUT: Actualizar una idea existente
    @PutMapping("/{id}")
    public ResponseEntity<VideoIdea> updateIdea(@PathVariable Long id, @RequestBody VideoIdea ideaDetails) {
        Optional<VideoIdea> optionalIdea = videoIdeaRepository.findById(id);
        
        if (optionalIdea.isPresent()) {
            VideoIdea idea = optionalIdea.get();
            idea.setTitle(ideaDetails.getTitle());
            idea.setDescription(ideaDetails.getDescription());
            idea.setTags(ideaDetails.getTags());
            idea.setStatus(ideaDetails.getStatus());
            
            VideoIdea updatedIdea = videoIdeaRepository.save(idea);
            return ResponseEntity.ok(updatedIdea);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Eliminar una idea
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIdea(@PathVariable Long id) {
        if (videoIdeaRepository.existsById(id)) {
            videoIdeaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
