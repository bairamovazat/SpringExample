package ru.ivmiit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import ru.ivmiit.models.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User,Long>{
    User findOneByUuid(UUID uuid);
}
