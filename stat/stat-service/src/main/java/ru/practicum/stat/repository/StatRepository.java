package ru.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.stat.model.Hit;
import ru.practicum.stat.model.dao.ViewStatDao;

@Repository
public interface StatRepository extends JpaRepository<Hit, Long>, ViewStatDao {
}
