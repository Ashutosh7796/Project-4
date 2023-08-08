package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pgprogramdoc")
public class Pgprogramdoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PgProgramDocId", nullable = false)
    private Integer PgProgramDocId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PgProgram_PgProgramId", nullable = false)
    private Pgprogram pgProgram;

}