package ru.nuykin.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.diplom.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
