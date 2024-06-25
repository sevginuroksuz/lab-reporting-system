package com.managementsystem.service;

import com.managementsystem.entity.Laborant;
import com.managementsystem.repository.LaborantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaborantService {

    private final LaborantRepository laborantRepository;


    public LaborantService(LaborantRepository laborantRepository) {
        this.laborantRepository = laborantRepository;
    }

    public Laborant createLaborant(Laborant laborant) {
        return laborantRepository.save(laborant);
    }

    public boolean deleteLaborant(Long id) {
        laborantRepository.deleteById(id);
        return true;
    }


    public Laborant updateLaborant(Long id, Laborant updatedLaborant) {
        // İlgili laborantın mevcut olup olmadığını kontrol et
        Optional<Laborant> optionalLaborant = laborantRepository.findById(id);
        if (optionalLaborant.isPresent()) {
            Laborant existingLaborant = optionalLaborant.get();
            // Güncelleme işlemlerini yap
            existingLaborant.setFirstName(updatedLaborant.getFirstName());
            existingLaborant.setLastName(updatedLaborant.getLastName());
            return laborantRepository.save(existingLaborant);
        } else {
            // Belirtilen kimlik numarasına sahip bir laborant bulunamadığında null döndür
            return null;
        }
    }

    public Optional<Laborant> findById(Long id) {
        return laborantRepository.findById(id);
    }

    public List<Laborant> findAll() {
        return laborantRepository.findAll();
    }

    public boolean existsById(Long id) {
        return !laborantRepository.existsById(id);
    }
}

