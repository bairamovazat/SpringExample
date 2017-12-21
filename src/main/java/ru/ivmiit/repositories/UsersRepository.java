package ru.ivmiit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivmiit.models.User;
import ru.ivmiit.security.role.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<User,Long>{
    User findOneByUuid(UUID uuid);

    Optional<User> findOneByLogin(String login);

    List<User> findAllByRole(Role role);

    Optional<User> findById(Long userId);

    Optional<User> findByUuid(UUID uuid);
}


