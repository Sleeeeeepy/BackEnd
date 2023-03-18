package com.vsell.vsell.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String nickName;

    @Column
    private Instant birthDate;

    public static class Builder{
        private String email;
        private String password;
        private String name;
        private String nickName;
        private Instant birthDate;

        public Builder email(String email){
            this.email=email;
            return this;
        }

        public Builder password(String password){
            this.password=password;
            return this;
        }

        public Builder name(String name){
            this.name=name;
            return this;
        }

        public Builder nickName(String nickName){
            this.nickName=nickName;
            return this;
        }

        public Builder birthDate(Instant birthDate) {
            this.birthDate=birthDate;
            return this;
        }
        public User build(){
            return new User(this);
        }

    }

    private User(Builder builder){
        this.name=builder.name;
        this.email= builder.email;
        this.password=builder.password;
        this.nickName=builder.nickName;
        this.birthDate=builder.birthDate;
    }


}
