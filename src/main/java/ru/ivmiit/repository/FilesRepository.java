package ru.ivmiit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.File;

public interface FilesRepository extends JpaRepository<File,Long> {
    File findFirstByStorageFileName(String storageFileName);
}
