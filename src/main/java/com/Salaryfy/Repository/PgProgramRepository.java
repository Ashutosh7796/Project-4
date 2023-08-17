package com.Salaryfy.Repository;
import com.Salaryfy.Entity.Pgprogram;
import com.Salaryfy.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PgProgramRepository extends JpaRepository<Pgprogram, Integer> {

    public List<Pgprogram> getPlansByStatus(String status);

}
