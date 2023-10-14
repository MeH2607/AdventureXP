package dat3.adventureXP.service;

import dat3.adventureXP.dto.EquipmentRequest;
import dat3.adventureXP.dto.EquipmentResponse;
import dat3.adventureXP.entity.Equipment;
import dat3.adventureXP.repository.EquipmentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentService {
    EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<EquipmentResponse> getEquipments(){
        List<Equipment> equipments = equipmentRepository.findAll();
        return equipments.stream().map(equipment -> new EquipmentResponse(equipment)).collect(Collectors.toList());
    }

    public EquipmentResponse getEquipmentById(int id){
        Equipment equipment = equipmentRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipment not found"));

        return new EquipmentResponse(equipment);
    }

    public EquipmentResponse updateEquipmentStatus(EquipmentRequest body, String name){
        Equipment editEquipment = equipmentRepository.findByName(name);

        if (editEquipment == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipment not found");
        }

        editEquipment.setStatus(body.getStatus());
        return new EquipmentResponse(equipmentRepository.save(editEquipment));
    }
}
