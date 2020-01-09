package org.school;

import java.util.Date;

public class Solution {

    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int userId;
    private int exerciseId;

    public Solution() {
    }

    public Solution(int id, Date updated, String description) {
        this.id = id;
        this.updated = updated;
        this.description = description;
    }

    public Solution(Date created, Date updated, String description, int userId, int exerciseId) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.userId = userId;
        this.exerciseId = exerciseId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Date getCreated() {

        return created;
    }

    public void setCreated(Date created) {

        this.created = created;
    }

    public Date getUpdated() {

        return updated;
    }


    public void setUpdated(Date updated)
    {
        this.updated = updated;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public int getExerciseId() {

        return exerciseId;
    }


    public void setExerciseId(int exerciseId) {

        this.exerciseId = exerciseId;
    }

    @Override
    public String toString() {
        return "org.school.Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", exerciseId=" + exerciseId +
                '}';
    }
}
