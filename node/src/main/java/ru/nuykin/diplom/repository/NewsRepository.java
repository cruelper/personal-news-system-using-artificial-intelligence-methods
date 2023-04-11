package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nuykin.diplom.model.News;

public interface NewsRepository extends JpaRepository<News, Long> {
}
