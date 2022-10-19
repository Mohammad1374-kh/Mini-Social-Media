package com.mohammad.msm.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "sign_up_date" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date signUpDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;




}
