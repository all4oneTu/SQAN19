package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "date")
    private String date;
    @Column(name = "time_start")
    private String timeStart;
    @Column(name = "lesson")
    private String lesson;
    @Column(name = "room")
    private String room;
    @Column(name = "type")
    private String type;
    @Column(name = "is_substitute")
    private String isSubstitute;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherInfoEntity teacherInfoEntity;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subjectEntity;

    public ScheduleEntity() {
    }

    public ScheduleEntity(Long id, String date, String timeStart, String lesson, String room, String type,String isSubstitute, TeacherInfoEntity teacherInfoEntity, SubjectEntity subjectEntity) {
        this.id = id;
        this.date = date;
        this.timeStart = timeStart;
        this.lesson = lesson;
        this.room = room;
        this.type = type;
        this.isSubstitute = isSubstitute;
        this.teacherInfoEntity = teacherInfoEntity;
        this.subjectEntity = subjectEntity;
    }


    public String getIsSubstitute() {
        return isSubstitute;
    }

    public void setIsSubstitute(String isSubstitute) {
        this.isSubstitute = isSubstitute;
    }

    public TeacherInfoEntity getTeacherInfoEntity() {
        return teacherInfoEntity;
    }

    public void setTeacherInfoEntity(TeacherInfoEntity teacherInfoEntity) {
        this.teacherInfoEntity = teacherInfoEntity;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public void setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
