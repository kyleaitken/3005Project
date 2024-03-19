package com.example.project.Services;

import com.example.project.Models.FitnessClass;
import com.example.project.Repos.FitnessClassRepository;
import com.example.project.dto.AddClassRequest;
import com.example.project.dto.ClassUpdateRequest;
import com.example.project.dto.FitnessClassView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    public List<FitnessClassView>getAllUpcomingClasses() {
        return fitnessClassRepository.getAllUpcomingClasses();
    }

    public ResponseEntity<?> updateClassRoom(Integer classId, Integer newRoomId) {
        Optional<FitnessClass> fitnessClass = fitnessClassRepository.checkForConflictingClass(classId, newRoomId);
        if (fitnessClass.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Scheduling conflict detected with another class in the proposed room.");      
        } else {
            boolean updateSuccess = fitnessClassRepository.updateClassRoom(classId, newRoomId);
            if (updateSuccess) {
                // Update successful, return OK response
                return ResponseEntity.ok().body("Class room updated successfully.");
            } else {
                // Update failed (e.g., classId not found), return error response
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found or update failed.");
            }
        }
    }


    public ResponseEntity<?> removeClass(Integer classId) {
        boolean removeSuccess = fitnessClassRepository.removeClass(classId);
        if (removeSuccess) return ResponseEntity.ok().body("Class removed successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found or remove failed");
    }


    public ResponseEntity<?> addClass(AddClassRequest newClass) {
        return fitnessClassRepository.addClass(newClass);
    }


    public ResponseEntity<?> updateClassTime(Integer classId, ClassUpdateRequest classUpdate) {
        boolean updateSuccess = fitnessClassRepository.updateClassTime(classId, classUpdate);
        if (updateSuccess) return ResponseEntity.ok().body("Class time updated successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Class not found or update failed");
    }

}
