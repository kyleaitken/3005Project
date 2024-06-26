package com.example.project.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Models.Equipment;
import com.example.project.Models.Invoice;
import com.example.project.Models.Room;
import com.example.project.Repos.EquipmentRepository;
import com.example.project.Repos.RoomRepository;
import com.example.project.Services.FitnessClassService;
import com.example.project.Services.InvoiceService;
import com.example.project.dto.AddClassRequest;
import com.example.project.dto.ClassUpdateRequest;
import com.example.project.dto.FitnessClassView;

@RestController
@RequestMapping("/admin") // Base URI for all endpoints in this controller
public class AdminController {
    FitnessClassService fitnessClassService;
    InvoiceService invoiceService;
    EquipmentRepository equipmentRepository;
    RoomRepository roomRepository;

    public AdminController(FitnessClassService fitnessClassService, InvoiceService invoiceService, EquipmentRepository equipmentRepository, RoomRepository roomRepository) {
        this.fitnessClassService = fitnessClassService;
        this.invoiceService = invoiceService;
        this.equipmentRepository = equipmentRepository;
        this.roomRepository = roomRepository;
    }

    /*
     * ** ROOMS
     */

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms() {
       List<Room> rooms = roomRepository.getRooms();
       return ResponseEntity.ok(rooms);
    }


    /*
     * *** CLASSES ****
     */
    @GetMapping("/classes")
    public ResponseEntity<List<FitnessClassView>> getUpcomingClasses() {
       List<FitnessClassView> fitnessClasses = fitnessClassService.getAllUpcomingClasses();
       return ResponseEntity.ok(fitnessClasses); 
    }

    // change class time/date
    @PutMapping("/classes/changeTime/{classId}")
    public ResponseEntity<?> updateClassTime(@PathVariable Integer classId, @RequestBody ClassUpdateRequest classUpdate) {
        return fitnessClassService.updateClassTime(classId, classUpdate);
    }

    // change class Room
    @PutMapping("/classes/{classId}/changeRoom/{newRoomId}")
    public ResponseEntity<?> attemptUpdateClassRoom(@PathVariable Integer classId, @PathVariable Integer newRoomId) {
        return fitnessClassService.updateClassRoom(classId, newRoomId);
    }

    @PutMapping("/classes/update/{classId}")
    public ResponseEntity<?> updateClass(@PathVariable Integer classId, @RequestBody ClassUpdateRequest classUpdate) {
        return fitnessClassService.updateClass(classId, classUpdate);
    }

    // delete class
    @DeleteMapping("/classes/delete/{classId}")
    public ResponseEntity<?> removeFitnessClass(@PathVariable Integer classId) {
        return fitnessClassService.removeClass(classId);
    }


    @PostMapping("/classes/add")
    public ResponseEntity<?> addClass(@RequestBody AddClassRequest newClass) {
        return fitnessClassService.addClass(newClass);
    }

    /*
     * *** INVOICES ****
     */

     @GetMapping("/invoices/processing")
     public List<Invoice> getProcessingInvoices() {
        return invoiceService.getProcessingInvoices();
     }

     @GetMapping("/invoices")
     public List<Invoice> getInvoices() {
        return invoiceService.getInvoices();
     }
    
     @PutMapping("/invoices/{paymentId}")
     public ResponseEntity<?> processInvoice(@PathVariable Integer paymentId) {
        return invoiceService.processInvoice(paymentId);
     }

    /*
     * *** EQUIPMENT ****
     */

    @GetMapping("/equipment")
    public List<Equipment> getEquipment() {
        return equipmentRepository.getEquipment(); 
    }

    @PutMapping("/equipment/repair/{equipmentId}")
    public ResponseEntity<?> repairEquipment(@PathVariable Integer equipmentId) {
        boolean repaired = equipmentRepository.repairEquipment(equipmentId);
        if (repaired) return ResponseEntity.ok().body("Success");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Equipment not found or repair failed");
    }

    @PutMapping("/equipment/reportBroken/{equipmentId}")
    public ResponseEntity<?> reportEquipmentBroken(@PathVariable Integer equipmentId) {
        boolean equipmentUpdated = equipmentRepository.reportEquipmentBroken(equipmentId);
        if (equipmentUpdated) return ResponseEntity.ok().body("Success");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed");
    }
}
