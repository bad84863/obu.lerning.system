package com.obu.obu.lerning.system;

import jakarta.persistence.*;

@Entity
public class LiveClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String meetingLink;
    private String department;
    private String studyYear;
    private boolean active;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public String getMeetingLink() { return meetingLink; }
    public void setMeetingLink(String meetingLink) { this.meetingLink = meetingLink; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getStudyYear() { return studyYear; }
    public void setStudyYear(String studyYear) { this.studyYear = studyYear; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
