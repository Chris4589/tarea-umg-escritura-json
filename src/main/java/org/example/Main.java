package org.example;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        URL resource = new URL("file:src/main/resources/estudiantes.json");

        Root estudiantes = modificarJson(resource);
        List<Estudiante> estudiantesList = estudiantes.getEstudiantes();
        Estudiante estudiante = new Estudiante();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del estudiante: ");
        estudiante.setNombre(scanner.nextLine());
        System.out.println("Ingrese el id del estudiante: ");
        estudiante.setId(scanner.nextInt());

        estudiantesList.add(estudiante);
        escribirJson(resource,estudiantes);
    }

    public static Root modificarJson(URL resource) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Root estudiantes = objectMapper.readValue(new File(resource.getPath()), Root.class);
            List<Estudiante> estudiantesList = estudiantes.getEstudiantes();
            for (Estudiante estudiante : estudiantesList) {
                System.out.println("Nombre:".concat(estudiante.getNombre()).concat(" Id:").concat(String.valueOf(estudiante.getId())));
            }
            return estudiantes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamReadException | DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void escribirJson(URL resource, Root root) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(resource.getFile()), root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}