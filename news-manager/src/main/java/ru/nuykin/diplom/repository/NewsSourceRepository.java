package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.diplom.model.NewsSource;

@Repository
public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
