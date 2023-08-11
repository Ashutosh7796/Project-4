package com.Salaryfy.Repository;

import com.Salaryfy.Entity.Job;
import com.Salaryfy.Entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    public List<Plan> getPlansByStatus(boolean status);
}
