package ch.project.kanbanboard.repository;

import ch.project.kanbanboard.entity.Settingsconfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsconfigurationRepository extends JpaRepository<Settingsconfiguration, Integer> {
    Settingsconfiguration findByBoardUUID_Id(String id);

    Settingsconfiguration findByBoardUUID_IdAndDefaultValue(String id, Boolean defaultValue);
}