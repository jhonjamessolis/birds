package com.ufinet.birds.service;

import com.ufinet.birds.entity.Bird;
import com.ufinet.birds.repository.BirdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BirdService {

    @Autowired
    private BirdRepository birdRepository;

    public Bird create(Bird bird) {
        return birdRepository.save(bird);
    }

    public Bird update(Bird bird) {
        if (birdRepository.existsById(bird.getId())) {
            bird.setName(bird.getName());
            bird.setType(bird.getType());
            return birdRepository.save(bird);
        }
        throw new RuntimeException("bird not found with id: " + bird.getId());
    }

    public void delete(Long id) {
        if (birdRepository.existsById(id)) {
            birdRepository.deleteById(id);
        } else {
            throw new RuntimeException("bird not found with id: " + id);
        }
    }

    public Page<Bird> getBirds(Pageable pageable) {
        return birdRepository.findAll(pageable);
    }

}
