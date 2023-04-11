package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nuykin.diplom.model.User;

public interface UserRepository extends JpaRepository<User, String> {
}
