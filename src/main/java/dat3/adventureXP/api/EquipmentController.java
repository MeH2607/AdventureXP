package dat3.adventureXP.api;

import dat3.adventureXP.dto.EquipmentRequest;
import dat3.adventureXP.dto.EquipmentResponse;
import dat3.adventureXP.service.EquipmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/equipment")
public class EquipmentController {
    EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<EquipmentResponse> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{id}")
    public EquipmentResponse getEquipmentById(@PathVariable int id){
        return equipmentService.getEquipmentById(id);
    }

    @PutMapping("/{id}")
    public EquipmentResponse updateEquipmentStatus(@RequestBody EquipmentRequest equipmentRequest, @PathVariable String id){
        return equipmentService.updateEquipmentStatus(equipmentRequest, id);
    }
}
