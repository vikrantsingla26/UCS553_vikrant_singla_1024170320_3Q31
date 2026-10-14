import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** A small course registration system with prerequisites and a FIFO waitlist. */
public class CourseRegistrationPortal {

    private static class Course {
        private final String code;
        private final int capacity;
        private final Set<String> enrolledStudentIds = new HashSet<>();
        private final List<String> prerequisiteCodes = new ArrayList<>();
        private final LinkedList<String> waitlist = new LinkedList<>();

        private Course(String code, int capacity, List<String> prerequisites) {
            this.code = code;
            this.capacity = capacity;
            this.prerequisiteCodes.addAll(prerequisites);
        }
    }

    private final Map<String, Course> courses = new HashMap<>();
    private final Map<String, Set<String>> completedCoursesByStudent = new HashMap<>();

    public void addCourse(String code, int capacity, String... prerequisites) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Course capacity must be at least one.");
        }
        courses.put(code, new Course(code, capacity, List.of(prerequisites)));
    }

    /** Records a completed course so later registrations can check prerequisites. */
    public void markCourseCompleted(String studentId, String courseCode) {
        completedCoursesByStudent
                .computeIfAbsent(studentId, ignored -> new HashSet<>())
                .add(courseCode);
    }

    public String register(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            return "Course " + courseCode + " was not found.";
        }
        if (course.enrolledStudentIds.contains(studentId)) {
            return studentId + " is already enrolled in " + courseCode + ".";
        }
        if (course.waitlist.contains(studentId)) {
            return studentId + " is already on the waitlist for " + courseCode + ".";
        }

        Set<String> completed = completedCoursesByStudent.getOrDefault(studentId, Set.of());
        List<String> missing = new ArrayList<>();
        for (String prerequisite : course.prerequisiteCodes) {
            if (!completed.contains(prerequisite)) {
                missing.add(prerequisite);
            }
        }
        if (!missing.isEmpty()) {
            return "Registration denied for " + studentId + ": complete " + missing
                    + " before taking " + courseCode + ".";
        }

        if (course.enrolledStudentIds.size() < course.capacity) {
            course.enrolledStudentIds.add(studentId);
            return studentId + " is enrolled in " + courseCode + ".";
        }

        course.waitlist.addLast(studentId);
        return courseCode + " is full. " + studentId + " is number "
                + course.waitlist.size() + " on the waitlist.";
    }

    /** Drops a student and gives the newly open seat to the first eligible person waiting. */
    public String drop(String studentId, String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            return "Course " + courseCode + " was not found.";
        }
        if (!course.enrolledStudentIds.remove(studentId)) {
            return studentId + " was not enrolled in " + courseCode + ".";
        }

        String message = studentId + " dropped " + courseCode + ".";
        while (!course.waitlist.isEmpty()) {
            String nextStudent = course.waitlist.removeFirst();
            if (hasAllPrerequisites(nextStudent, course)) {
                course.enrolledStudentIds.add(nextStudent);
                return message + " " + nextStudent + " has been promoted from the waitlist.";
            }
            message += " " + nextStudent + " was skipped because a prerequisite is missing.";
        }
        return message + " No one was waiting for the seat.";
    }

    private boolean hasAllPrerequisites(String studentId, Course course) {
        Set<String> completed = completedCoursesByStudent.getOrDefault(studentId, Set.of());
        for (String prerequisite : course.prerequisiteCodes) {
            if (!completed.contains(prerequisite)) {
                return false;
            }
        }
        return true;
    }

    public void printCourseStatus(String courseCode) {
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course " + courseCode + " was not found.");
            return;
        }
        System.out.println(course.code + " — enrolled: " + course.enrolledStudentIds
                + ", waitlist (first to last): " + course.waitlist);
    }

    public static void main(String[] args) {
        CourseRegistrationPortal portal = new CourseRegistrationPortal();
        portal.addCourse("CS101", 2);
        portal.addCourse("CS201", 1, "CS101");

        portal.markCourseCompleted("Maya", "CS101");
        portal.markCourseCompleted("Leo", "CS101");
        portal.markCourseCompleted("Asha", "CS101");
        System.out.println(portal.register("Maya", "CS201"));
        System.out.println(portal.register("Leo", "CS201"));
        portal.printCourseStatus("CS201");
        System.out.println(portal.drop("Maya", "CS201"));
        portal.printCourseStatus("CS201");
    }
}
