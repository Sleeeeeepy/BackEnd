package com.vsell.vsell.user.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String profilePath;

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;

    }
}
