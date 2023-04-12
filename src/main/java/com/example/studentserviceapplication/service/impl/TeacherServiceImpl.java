package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.repository.FacultyRepository;
import com.example.studentserviceapplication.repository.TeacherRepository;
import com.example.studentserviceapplication.service.FacultyService;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final FacultyService facultyService;
    private final FacultyRepository facultyRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, FacultyService facultyService, FacultyRepository facultyRepository) {
        this.teacherRepository = teacherRepository;
        this.facultyService = facultyService;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public TeacherDTO getTeacherById(long id) {
        return null;
    }

    @Override
    public List<TeacherDTO> getAllTeachers() {
        return null;
    }

    @Override
    public List<Teacher> getAllTeachersFromWeb() {
        /*for (int i = 0; i < Faculties.values().length; i++) {
            Faculties faculty = Faculties.values()[i];
            try {
                String requiredFacultyUrl = "https://shahroodut.ac.ir/fa/schools/index.php?id=S" + faculty.getFacultyCode();*//*faculties.get(1).attr("href") ;*//*
                Document requiredFacultyDoc = Jsoup.connect(requiredFacultyUrl).get();
                Elements requiredTeachersPart = requiredFacultyDoc.select(".nav").select("ul").select("ul").select("li");
                for (int j = 0; j < 20; j++) {
                    try {
                        String requiredPartUrl = "https://shahroodut.ac.ir/fa/schools/index.php" + requiredTeachersPart.get(2).select("ul").select("li").get(j).select("a").attr("href");
                        Document selectedPartUrl = Jsoup.connect(requiredPartUrl).get();
                        Elements teachers = selectedPartUrl.select(".card");
                        for (Element teacherItem : teachers) {
                            String avatarUrl = teacherItem.select("img").attr("src").replace("..", "");
                            String teacherFullName = teacherItem.select(".stafftitle").select("a").text();
                            if (teacherRepository.findByFullName(teacherFullName).isEmpty()) {
                                Teacher teacher = new Teacher(teacherFullName, avatarUrl);
                                Faculty teacherFaculty = facultyService.getFacultyByCode(faculty.getFacultyCode());
                                teacherFaculty.getTeachers().add(teacher);
                                teacherRepository.save(teacher);
                                facultyRepository.save(teacherFaculty);
                            }


                        }
                    } catch (IndexOutOfBoundsException exception) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  teacherRepository.findAll();*/
        return null;
    }
}
