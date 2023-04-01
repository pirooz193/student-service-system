package com.example.studentserviceapplication.domain.enumuration;

public enum Faculties {

    COMPUTER( "001") ,
    ELECTRIC ( "002") ,
    CIVIL( "003") ,
    GEOLOGY( "004") ,
    MECHANIC ( "005") ,
    CHEMICAL ( "006") ,
    MATHEMATICAL_SCIENCE ( "007") ,
    INDUSTRY_AND_MANAGEMENT( "008") ,
    PHYSICS( "009") ,
    MINING_AND_PETROLEUM ( "010") ,
    AGRICULTURE( "011") ,
    PHYSICAL_EDUCATION( "012") ,
    CHEMICAL_AND_MATERIAL( "013") ,
    ARCHITECTURE( "014") ;

    private final String facultyCode;

    Faculties(String facultyCode) {
        this.facultyCode = facultyCode;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    // add a method to get the faculty enum based on the faculty code
    public static Faculties getFacultyByCode(String code) {
        for (Faculties faculty : Faculties.values()) {
            if (faculty.getFacultyCode().equals(code)) {
                return faculty;
            }
        }
        throw new IllegalArgumentException("Invalid faculty code: " + code);
    }
}
