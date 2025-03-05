package com.ufinet.birds.controller;

import com.ufinet.birds.entity.Bird;
import com.ufinet.birds.service.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/birds")
public class BirdController {

    @Autowired
    private BirdService birdService;

    @PostMapping
    public ResponseEntity<Bird> create(@RequestBody Bird bird) {
        Bird newBird = birdService.create(bird);
        return ResponseEntity.ok(newBird);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bird> update(@PathVariable Long id, @RequestBody Bird bird) {
        bird.setId(id);
        Bird birdUpdated = birdService.update(bird);
        return ResponseEntity.ok(birdUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        birdService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<Bird> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return birdService.getBirds(pageable);
    }
}
