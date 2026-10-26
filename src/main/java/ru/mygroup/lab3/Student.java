package ru.mygroup.lab3;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Введите фамилию")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank(message = "Введите имя")
    @Size(min = 2, max = 50)
    private String firstName;

    private String patronymic;

    @NotBlank(message = "Укажите пол")
    private String gender;

    @NotBlank(message = "Укажите национальность")
    private String nationality;

    @NotNull(message = "Введите рост")
    @Min(value = 50, message = "Минимальный рост — 50 см")
    @Max(value = 250, message = "Максимальный рост — 250 см")
    private Integer height;

    @NotNull(message = "Введите вес")
    @DecimalMin(value = "2.0", message = "Минимальный вес — 2 кг")
    @DecimalMax(value = "350.0", message = "Максимальный вес — 350 кг")
    private Double weight;

    @NotNull(message = "Укажите дату рождения")
    @Past(message = "Дата рождения должна быть в прошлом")
    private LocalDate birthDate;

    @NotBlank(message = "Введите номер телефона")
    @Size(min = 5, max = 20, message = "Проверьте номер телефона")
    private String phone;

    @NotBlank(message = "Введите почтовый индекс")
    private String postalCode;

    @NotBlank(message = "Введите страну")
    private String country;

    @NotBlank(message = "Введите область")
    private String region;

    @NotBlank(message = "Введите район")
    private String district;

    @NotBlank(message = "Введите город")
    private String city;

    @NotBlank(message = "Введите улицу")
    private String street;

    @NotBlank(message = "Введите номер дома")
    private String house;

    @NotBlank(message = "Введите номер квартиры")
    private String apartment;

    @NotNull(message = "Введите курс")
    @Min(value = 1, message = "Минимальный курс — 1")
    @Max(value = 6, message = "Максимальный курс — 6")
    private Integer course;

    @NotNull(message = "Введите средний балл")
    @DecimalMin(value = "0.0", message = "Минимальный балл — 0")
    @DecimalMax(value = "10.0", message = "Максимальный балл — 10")
    private Double averageGrade;

    @NotBlank(message = "Введите специальность")
    private String specialty;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private StudentGroup studentGroup;

    public Student() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }
}
