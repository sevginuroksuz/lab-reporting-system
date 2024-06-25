package com.managementsystem.controller;

import com.managementsystem.entity.Laborant;
import com.managementsystem.service.LaborantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laborants")
public class LaborantController {

    private final LaborantService laborantService;

    public LaborantController(LaborantService laborantService) {
        this.laborantService = laborantService;
    }

    @Transactional
    @GetMapping("/all")
    public List<Laborant> getAllLaborants() {
        return laborantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laborant> getLaborantById(@PathVariable Long id) {
        Optional<Laborant> laborantOptional = laborantService.findById(id);
        return laborantOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Laborant> createLaborant(@RequestBody Laborant laborant) {
        Laborant savedLaborant = laborantService.createLaborant(laborant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLaborant);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Laborant> updateLaborant(@PathVariable Long id, @RequestBody Laborant laborant) {
        if (laborantService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        laborant.setId(id);
        Laborant updatedLaborant = laborantService.updateLaborant(id, laborant);
        return ResponseEntity.ok(updatedLaborant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLaborant(@PathVariable Long id) {
        boolean deleted = laborantService.deleteLaborant(id);
        if (deleted) {
            return ResponseEntity.ok("Laborant with ID " + id + " has been deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
