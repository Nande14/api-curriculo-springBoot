package com.example.springboot.controllers;

import com.example.springboot.dtos.CurriculumRecordDto;
import com.example.springboot.models.CurriculumModel;
import com.example.springboot.repositories.CurriculumRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CurriculumController {
    @Autowired
    CurriculumRepository curriculumRepository;

    @PostMapping("/curriculum")
    public ResponseEntity<CurriculumModel> saveCurriculum(@RequestBody @Valid CurriculumRecordDto curriculumRecordDto) {
        var curriculumModel = new CurriculumModel();
        BeanUtils.copyProperties(curriculumRecordDto, curriculumModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(curriculumRepository.save(curriculumModel));
    }

    @GetMapping("/curriculum")
    public ResponseEntity<List<CurriculumModel>> getAllCurriculums() {
        return ResponseEntity.status(HttpStatus.OK).body(curriculumRepository.findAll());
    }

    @GetMapping("/curriculum/{id}")
    public ResponseEntity<Object> getOneCurriculum(@PathVariable(value = "id") UUID id) {
        Optional<CurriculumModel> curriculum = curriculumRepository.findById(id);
        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculum not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(curriculum.get());
    }

    @PutMapping("/curriculum/{id}")
    public ResponseEntity<Object> updateCurriculum(@PathVariable(value = "id") UUID id,
                                                   @RequestBody @Valid CurriculumRecordDto curriculumRecordDto) {
        Optional<CurriculumModel> curriculum = curriculumRepository.findById(id);

        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculum not found");
        }

        CurriculumModel existingCurriculum = curriculum.get();
        BeanUtils.copyProperties(curriculumRecordDto, existingCurriculum);

        return ResponseEntity.status(HttpStatus.OK).body(curriculumRepository.save(existingCurriculum));
    }

    @DeleteMapping("/curriculum/{id}")
    public ResponseEntity<Object> deleteCurriculum(@PathVariable(value = "id") UUID id) {
        Optional<CurriculumModel> curriculum = curriculumRepository.findById(id);
        if (curriculum.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curriculum not found");
        }
        curriculumRepository.delete(curriculum.get());
        return ResponseEntity.status(HttpStatus.OK).body("Curriculum deleted successfully");
    }
}
