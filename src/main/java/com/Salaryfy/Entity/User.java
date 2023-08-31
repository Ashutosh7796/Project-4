package com.Salaryfy.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "FullName", length = 250)
    private String fullName;

    @Column(name = "MobileNo")
    private String mobileNo;

    @Column(name = "email", length = 45)
    private String email;


    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "date")
    private LocalDate Date;


    @Column(name = "profilePhoto", length = 250)
    private String profilePhoto;

    @Column(name = "userProfileType", length = 45)
    private String userProfileType;

    @Column(name = "subType", length = 45)
    private String subType;

    @Column(name = "Payment_validity", length = 45)
    private String paymentValidity;

//    @OneToMany(mappedBy = "userUser")
//    private List<Document> documents = new LinkedList<>();

    @OneToMany(mappedBy = "userUser")
    private List<Experiencedoc> experienceDocs = new LinkedList<>();

    @OneToMany(mappedBy = "userUser")
    private List<Job> jobs = new LinkedList<>();


    @OneToMany(mappedBy = "userUser")
    private List<ProfileLevel> profileLevels = new LinkedList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;


}