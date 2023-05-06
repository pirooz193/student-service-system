package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Faculty;
import com.example.studentserviceapplication.domain.Teacher;
import com.example.studentserviceapplication.domain.enumuration.Faculties;
import com.example.studentserviceapplication.repository.TeacherRepository;
import com.example.studentserviceapplication.service.FacultyService;
import com.example.studentserviceapplication.service.TeacherService;
import com.example.studentserviceapplication.service.dto.TeacherDTO;
import com.example.studentserviceapplication.service.mapper.FacultyMapper;
import com.example.studentserviceapplication.service.mapper.TeacherMapper;
import com.example.studentserviceapplication.web.error.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final FacultyService facultyService;
    private final FacultyMapper facultyMapper;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, FacultyService facultyService, FacultyMapper facultyMapper, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.facultyService = facultyService;
        this.facultyMapper = facultyMapper;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherDTO getTeacherById(long id) {
        Optional<Teacher> requiredTeacher = teacherRepository.findById(id);
        if (requiredTeacher.isPresent()) {
            return teacherMapper.toDTO(requiredTeacher.get());
        } else throw new NotFoundException(String.valueOf(id));
    }

    @Cacheable("teachers")
    @Override
    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Teacher> getAllTeachersFromWeb() {
        for (int i = 0; i < Faculties.values().length; i++) {
            Faculties faculty = Faculties.values()[i];
            try {
                String requiredFacultyUrl = "https://shahroodut.ac.ir/fa/schools/index.php?id=S" + faculty.getFacultyCode();
//                faculties.get(1).attr("href") ;
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
                                Faculty teacherFaculty = facultyMapper.toEntity(facultyService.getFacultyByCode(faculty.getFacultyCode()));
                                teacherFaculty.getTeachers().add(teacher);
                                teacherRepository.save(teacher);
                                facultyService.save(teacherFaculty);
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
        return teacherRepository.findAll();
    }

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        return teacherMapper.toDTO(teacherRepository.save(teacher));
    }
}
