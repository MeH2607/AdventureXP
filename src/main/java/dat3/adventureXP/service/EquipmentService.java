package dat3.adventureXP.service;

import dat3.adventureXP.dto.EquipmentResponse;
import dat3.adventureXP.entity.Equipment;
import dat3.adventureXP.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

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
}
