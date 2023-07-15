package lesson4.services;

import lesson4.models.Teacher;
import lesson4.models.User;
import lesson4.repositories.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeacherService implements UserService<Teacher> {

    private final UserRepository<Teacher> userRepository;

    public Teacher(UserRepository<Teacher> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {

        userRepository.create(new Teacher(fullName, age, phoneNumber, groupTitle));
    }

    @Override
    public List<Teacher> getAll() {
        var students = userRepository.getAll();
        Collections.sort(teachers);
        return students;
    }

    public List<Teacher> getAllSortByFullName() {
        var teachers = userRepository.getAll();
/*        teachers.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return o1.getFullName().compareTo(o2.getFullName());
            }
        });*/
//        teachers.sort((o1, o2) -> o1.getFullName().compareTo(o2.getFullName()));
        teachers.sort(Comparator.comparing(User::getFullName));

        return teachers;
    }

    public List<Teacher> getAllSortById() {
        var teachers = userRepository.getAll();
        teachers.sort(Comparator.comparing(User::getId));
        return teachers;
    }



    @Override
    public int remove(String fullName) {
        return userRepository.remove(fullName);
    }

    @Override
    public List<Teacher> getAllByTitile(String groupTitle) {
        return userRepository.getAllByGroupTitle(groupTitle);
    }
}
