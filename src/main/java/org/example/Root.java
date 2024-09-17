package org.example;

import java.util.List;

public class Root {
    private List<Estudiante> estudiantes;

    public List<Estudiante> getEstudiantes() {
        if (estudiantes == null) {
            throw new RuntimeException("No hay estudiantes");
        }
        return estudiantes;
    }
}
