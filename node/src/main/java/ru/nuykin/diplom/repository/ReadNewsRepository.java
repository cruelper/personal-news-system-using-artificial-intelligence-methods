package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nuykin.diplom.model.ReadNews;

public interface ReadNewsRepository extends JpaRepository<ReadNews, Long> {
}
