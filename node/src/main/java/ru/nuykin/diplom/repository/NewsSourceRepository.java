package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nuykin.diplom.model.NewsSource;

public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
