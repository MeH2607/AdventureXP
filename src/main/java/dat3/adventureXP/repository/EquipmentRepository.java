package dat3.adventureXP.repository;

import dat3.adventureXP.dto.EquipmentResponse;
import dat3.adventureXP.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    Equipment findByName(String name);
}
