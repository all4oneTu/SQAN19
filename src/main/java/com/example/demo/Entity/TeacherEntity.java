package com.example.demo.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacherEntity")
    private TeacherInfoEntity teacherInfoEntity;
    public TeacherEntity() {
    }

    public TeacherEntity(Long id, String username, String password, TeacherInfoEntity teacherInfoEntity) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.teacherInfoEntity = teacherInfoEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TeacherInfoEntity getTeacherInfoEntity() {
        return teacherInfoEntity;
    }

    public void setTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity) {
        this.teacherInfoEntity = teacherInfoEntity;
    }
}
