package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class EduSuggestion {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "EduSuggestionId")
    private  Integer EduSuggestionId;

    @Column(name ="education")
    private String education;

    @Lob
    @Column(name = "boardUniversity")
    private String boardUniversity;

    @Column(name = "Stream")
    private String Stream;

    @Column (name = "State")
    private String State;
}
