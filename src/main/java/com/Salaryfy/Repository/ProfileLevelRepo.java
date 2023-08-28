package com.Salaryfy.Repository;

import com.Salaryfy.Entity.ProfileLevel;
import com.Salaryfy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileLevelRepo extends JpaRepository<ProfileLevel,Integer> {

//    @Query("SELECT jfq FROM JobfairQue jfq WHERE jfq.questionType = :questionType AND jfq.setNo = :setNo")

//    @Query("SELECT p FROM profile_level p WHERE p.user_userId = :user_userId")
//    Optional<ProfileLevel> findByUserId(@Param("user_userId")Integer user_userId);
//    public Optional<ProfileLevel> findByUserId(Optional<User> byId);
//    public Optional<ProfileLevel> findByUserId(Integer user_Id);

//    public Optional<ProfileLevel> findByUserId(Integer userId);
}
