package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marksId", nullable = false)
    private Integer marksId;

    @Column(name = "`user_id`")
    private Integer userId;

    @Column(name = "`Total_marks`")
    private Integer totalMarks;

    @Column(name = "obtainedMarks")
    private Integer obtainedMarks;

    @Column(name = "Status", length = 45)
    private String status;

}