package org.school.model;

import org.school.Exercise;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDao {
    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT id, title, description FROM exercises WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercises SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT id, title, description FROM exercises";
    private static final String FIND_EXERCISE_BY_NAME_QUERY =
            "SELECT 1 FROM exercises WHERE title = ?";
    private static final String FIND_UNSOLVED_EXERCISES_FOR_USER_QUERY = "SELECT e.id, e.description, e.title\n" +
            "FROM exercises e WHERE NOT EXISTS (\n" +
            "SELECT 1\n" +
            "FROM solutions s JOIN users u on s.user_id = u.id\n" +
            "WHERE e.id = s.exercise_id\n" +
            "AND u.id = ?);";


    public Exercise create(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, exercise.getTitle());
            ps.setString(2, exercise.getDescription());

            ps.executeUpdate();

            final ResultSet gk = ps.getGeneratedKeys();
            if (gk.next()) {
                int id = gk.getInt(1);
                exercise.setId(id);

                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Exercise read(int exerciseId) {
        try (Connection conn = DbUtil.getConnection();
             final PreparedStatement ps = conn.prepareStatement(READ_EXERCISE_QUERY);
        ) {
            ps.setInt(1, exerciseId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(rs.getInt("id"));
                exercise.setTitle(rs.getString("title"));
                exercise.setDescription(rs.getString("description"));

                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean exerciseExists(String title) {
        try (Connection conn = DbUtil.getConnection();
             final PreparedStatement ps = conn.prepareStatement(FIND_EXERCISE_BY_NAME_QUERY);
        ) {
            ps.setString(1, title);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void update(Exercise exercise) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);) {

            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY)) {
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] findAll() {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);) {
            Exercise[] exercises = new Exercise[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));

                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercise[] findUnsolvedForUser(int userId) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_UNSOLVED_EXERCISES_FOR_USER_QUERY);) {
            Exercise[] exercises = new Exercise[0];
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));

                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Exercise[] addToArray(Exercise u, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = u;
        return tmpExercises;
    }

}
