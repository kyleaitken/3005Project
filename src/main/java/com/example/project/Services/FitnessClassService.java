package com.example.project.Services;

import com.example.project.Repos.FitnessClassRepository;
import com.example.project.dto.FitnessClassView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FitnessClassService {
    private final FitnessClassRepository fitnessClassRepository;

    @Autowired
    public FitnessClassService(FitnessClassRepository fitnessClassRepository) {
        this.fitnessClassRepository = fitnessClassRepository;
    }

    public List<FitnessClassView> findAllPastMemberFitnessClasses(Integer memberId) {
        return fitnessClassRepository.findAllPastMemberFitnessClasses(memberId);
    }

    public List<FitnessClassView> findAllFutureMemberFitnessClasses(Integer memberId) {
        return fitnessClassRepository.findAllFutureMemberFitnessClasses(memberId);
    }

    public List<FitnessClassView> findAllAvailableMemberFitnessClasses(Integer memberId) {
        return fitnessClassRepository.findAllAvailableMemberFitnessClasses(memberId);
    }

    public void removeMemberFromClass(Integer memberId, Integer classId) {
        fitnessClassRepository.removeMemberFromClass(memberId, classId);
    }

    public ResponseEntity<?> addMemberToClass(Integer memberId, Integer classId) {
        return fitnessClassRepository.addMemberToClass(memberId, classId);
    }
}
