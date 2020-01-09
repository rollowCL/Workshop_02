package org.school;

public class AllData {
    private Solution solution;
    private Exercise exercise;
    private User user;
    private Group group;

    public AllData(Solution solution, User user, Exercise exercise, Group group) {
        this.solution = solution;
        this.user = user;
        this.exercise = exercise;
        this.group = group;
    }

    public AllData() {
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
