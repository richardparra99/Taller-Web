package org.example.menus;

import org.example.enums.TipoCalificacion;
import org.example.models.CalificacionModel;
import org.example.models.EstudianteModel;
import org.example.models.InscripcionModel;
import org.example.models.MateriaModel;
import org.example.repositories.CalificacionRepository;
import org.example.repositories.EstudianteRepository;
import org.example.repositories.InscripcionRepository;
import org.example.repositories.MateriaRepository;

import java.util.List;
import java.util.Scanner;

public class MenuConsola {
    private static final double NOTA_MINIMA_APROBACION = 51.0;

    private final Scanner scanner;

    private final EstudianteRepository estudianteRepository;
    private final MateriaRepository materiaRepository;
    private final InscripcionRepository inscripcionRepository;
    private final CalificacionRepository calificacionRepository;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MenuConsola(
            Scanner scanner,
            EstudianteRepository estudianteRepository,
            MateriaRepository materiaRepository,
            InscripcionRepository inscripcionRepository,
            CalificacionRepository calificacionRepository
    ) {
        this.scanner = scanner;
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.calificacionRepository = calificacionRepository;
    }


    // =========================================================
    // INICIAR MENU
    // =========================================================

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            opcion = leerEntero(
                    "Seleccione una opcion: "
            );

            System.out.println();

            switch (opcion) {

                case 1 -> registrarEstudiante();

                case 2 -> crearMateria();

                case 3 -> asignarEstudianteMateria();

                case 4 -> registrarCalificacion();

                case 5 -> generarReporteAcademicoMateria();

                case 6 -> consultarEstudiantesReprobados();

                case 7 -> generarHistoricoEstudiante();

                case 8 -> listarMateriasCantidadEstudiantes();

                case 0 -> System.out.println(
                        "Saliendo del sistema..."
                );

                default -> System.out.println(
                        "Opcion incorrecta."
                );
            }

        } while (opcion != 0);
    }


    // =========================================================
    // MOSTRAR MENU
    // =========================================================

    private void mostrarMenu() {

        System.out.println();
        System.out.println(
                "============================================"
        );

        System.out.println(
                "     SISTEMA DE GESTION UNIVERSITARIA"
        );

        System.out.println(
                "============================================"
        );

        System.out.println(
                "1. Registrar nuevo estudiante"
        );

        System.out.println(
                "2. Crear materia"
        );

        System.out.println(
                "3. Asignar estudiante a materia"
        );

        System.out.println(
                "4. Registrar calificacion"
        );

        System.out.println(
                "5. Generar reporte academico de materia"
        );

        System.out.println(
                "6. Consultar estudiantes reprobados"
        );

        System.out.println(
                "7. Generar historico del estudiante"
        );

        System.out.println(
                "8. Listar materias y cantidad de estudiantes"
        );

        System.out.println(
                "0. Salir"
        );

        System.out.println(
                "============================================"
        );
    }


    // =========================================================
    // OPCION 1
    // REGISTRAR ESTUDIANTE
    // =========================================================

    private void registrarEstudiante() {

        System.out.println(
                "===== REGISTRAR ESTUDIANTE ====="
        );

        System.out.print(
                "Codigo: "
        );

        String codigo =
                scanner.nextLine().trim();


        // COMPROBAR SI YA EXISTE

        EstudianteModel existente =
                estudianteRepository.findByCodigo(codigo);


        if (existente != null) {

            System.out.println(
                    "Ya existe un estudiante con ese codigo."
            );

            return;
        }


        System.out.print(
                "Nombre: "
        );

        String nombre =
                scanner.nextLine().trim();


        System.out.print(
                "Correo: "
        );

        String correo =
                scanner.nextLine().trim();


        System.out.print(
                "Carrera: "
        );

        String carrera =
                scanner.nextLine().trim();


        // CREAR ESTUDIANTE

        EstudianteModel estudiante =
                new EstudianteModel();


        estudiante.setCodigo(
                codigo
        );

        estudiante.setNombre(
                nombre
        );

        estudiante.setCorreo(
                correo
        );

        estudiante.setCarrera(
                carrera
        );


        // GUARDAR

        estudianteRepository.save(
                estudiante
        );


        System.out.println();

        System.out.println(
                "Estudiante registrado correctamente."
        );

        System.out.println(
                "ID generado: "
                        + estudiante.getId()
        );
    }

    private void crearMateria() {

        System.out.println(
                "===== CREAR MATERIA ====="
        );


        System.out.print(
                "Nombre de la materia: "
        );

        String nombre =
                scanner.nextLine().trim();


        System.out.println();

        System.out.println(
                "Tipo de calificacion:"
        );

        System.out.println(
                "1. Promedio"
        );

        System.out.println(
                "2. Ponderacion"
        );


        int opcionTipo =
                leerEntero(
                        "Seleccione: "
                );


        TipoCalificacion tipo;


        if (opcionTipo == 1) {

            tipo =
                    TipoCalificacion.PROMEDIO;

        } else if (opcionTipo == 2) {

            tipo =
                    TipoCalificacion.PONDERACION;

        } else {

            System.out.println(
                    "Tipo de calificacion incorrecto."
            );

            return;
        }


        // CREAR MATERIA

        MateriaModel materia =
                new MateriaModel();


        materia.setNombre(
                nombre
        );

        materia.setTipoCalificacion(
                tipo
        );

        materiaRepository.save(
                materia
        );


        System.out.println();

        System.out.println(
                "Materia registrada correctamente."
        );

        System.out.println(
                "ID generado: "
                        + materia.getId()
        );

        System.out.println(
                "Tipo: "
                        + materia.getTipoCalificacion()
        );
    }

    private void asignarEstudianteMateria() {

        System.out.println(
                "===== ASIGNAR ESTUDIANTE A MATERIA ====="
        );


        List<EstudianteModel> estudiantes =
                estudianteRepository.getAll();


        if (estudiantes.isEmpty()) {

            System.out.println(
                    "No existen estudiantes registrados."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "ESTUDIANTES:"
        );


        estudiantes.forEach(
                estudiante ->
                        System.out.println(
                                estudiante.getId()
                                        + " - "
                                        + estudiante.getNombre()
                                        + " ["
                                        + estudiante.getCodigo()
                                        + "]"
                        )
        );


        int estudianteId =
                leerEntero(
                        "ID del estudiante: "
                );


        EstudianteModel estudiante =
                estudianteRepository.findById(
                        estudianteId
                );


        if (estudiante == null) {

            System.out.println(
                    "Estudiante no encontrado."
            );

            return;
        }


        List<MateriaModel> materias =
                materiaRepository.getAll();


        if (materias.isEmpty()) {

            System.out.println(
                    "No existen materias registradas."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "MATERIAS:"
        );


        materias.forEach(
                materia ->
                        System.out.println(
                                materia.getId()
                                        + " - "
                                        + materia.getNombre()
                                        + " ["
                                        + materia.getTipoCalificacion()
                                        + "]"
                        )
        );


        int materiaId = leerEntero("ID de la materia: ");

        MateriaModel materia = materiaRepository.findById(materiaId);

        if (materia == null) {

            System.out.println(
                    "Materia no encontrada."
            );

            return;
        }


        // COMPROBAR SI YA ESTA INSCRITO

        boolean yaInscrito =
                inscripcionRepository
                        .findByEstudianteId(
                                estudianteId
                        )
                        .stream()
                        .anyMatch(
                                inscripcion ->
                                        inscripcion
                                                .getMateria()
                                                .getId()
                                                == materiaId
                        );


        if (yaInscrito) {

            System.out.println(
                    "El estudiante ya esta asignado a esta materia."
            );

            return;
        }


        // CREAR INSCRIPCION

        InscripcionModel inscripcion =
                new InscripcionModel();


        inscripcion.setEstudiante(
                estudiante
        );

        inscripcion.setMateria(
                materia
        );


        // GUARDAR

        inscripcionRepository.save(
                inscripcion
        );


        System.out.println();

        System.out.println(
                "Estudiante asignado correctamente."
        );

        System.out.println(
                estudiante.getNombre()
                        + " -> "
                        + materia.getNombre()
        );
    }


    // =========================================================
    // OPCION 4
    // REGISTRAR CALIFICACION
    // =========================================================

    private void registrarCalificacion() {

        System.out.println(
                "===== REGISTRAR CALIFICACION ====="
        );


        List<InscripcionModel> inscripciones =
                inscripcionRepository.getAll();


        if (inscripciones.isEmpty()) {

            System.out.println(
                    "No existen estudiantes asignados a materias."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "INSCRIPCIONES:"
        );


        inscripciones.forEach(
                inscripcion ->
                        System.out.println(
                                "ID: "
                                        + inscripcion.getId()
                                        + " | "
                                        + inscripcion
                                        .getEstudiante()
                                        .getNombre()
                                        + " | "
                                        + inscripcion
                                        .getMateria()
                                        .getNombre()
                        )
        );


        int inscripcionId =
                leerEntero(
                        "Seleccione ID de inscripcion: "
                );


        InscripcionModel inscripcion =
                inscripcionRepository.findById(
                        inscripcionId
                );


        if (inscripcion == null) {

            System.out.println(
                    "Inscripcion no encontrada."
            );

            return;
        }


        System.out.print("Nombre de la evaluacion: ");

        String nombreEvaluacion = scanner.nextLine().trim();


        double nota = leerDouble("Nota: ");

        if (nota < 0 || nota > 100) {

            System.out.println(
                    "La nota debe estar entre 0 y 100."
            );

            return;
        }


        Double ponderacion = null;


        // =====================================================
        // MATERIA POR PONDERACION
        // =====================================================

        if (
                inscripcion
                        .getMateria()
                        .getTipoCalificacion()
                        == TipoCalificacion.PONDERACION
        ) {

            ponderacion =
                    leerDouble(
                            "Ponderacion (%): "
                    );


            if (
                    ponderacion <= 0
                            || ponderacion > 100
            ) {

                System.out.println(
                        "La ponderacion debe ser mayor a 0 y menor o igual a 100."
                );

                return;
            }


            // COMPROBAR PONDERACION YA REGISTRADA

            List<CalificacionModel> calificacionesExistentes =
                    calificacionRepository
                            .findByInscripcionId(
                                    inscripcionId
                            );


            double ponderacionAcumulada = 0;


            for (
                    CalificacionModel calificacionExistente
                    : calificacionesExistentes
            ) {

                if (
                        calificacionExistente
                                .getPonderacion()
                                != null
                ) {

                    ponderacionAcumulada +=
                            calificacionExistente
                                    .getPonderacion();
                }
            }


            if (
                    ponderacionAcumulada
                            + ponderacion
                            > 100
            ) {

                System.out.println(
                        "No se puede registrar la calificacion."
                );

                System.out.println(
                        "La ponderacion total superaria el 100%."
                );

                System.out.println(
                        "Ponderacion actual: "
                                + ponderacionAcumulada
                                + "%"
                );

                return;
            }
        }


        // CREAR CALIFICACION

        CalificacionModel calificacion =
                new CalificacionModel();


        calificacion.setInscripcion(
                inscripcion
        );

        calificacion.setNombreEvaluacion(
                nombreEvaluacion
        );

        calificacion.setNota(
                nota
        );

        calificacion.setPonderacion(
                ponderacion
        );


        // GUARDAR

        calificacionRepository.save(
                calificacion
        );


        System.out.println();

        System.out.println(
                "Calificacion registrada correctamente."
        );

        System.out.println(
                "Estudiante: "
                        + inscripcion
                        .getEstudiante()
                        .getNombre()
        );

        System.out.println(
                "Materia: "
                        + inscripcion
                        .getMateria()
                        .getNombre()
        );

        System.out.println(
                "Evaluacion: "
                        + nombreEvaluacion
        );

        System.out.println(
                "Nota: "
                        + nota
        );


        if (ponderacion != null) {

            System.out.println(
                    "Ponderacion: "
                            + ponderacion
                            + "%"
            );
        }
    }


    // =========================================================
    // OPCION 5
    // GENERAR REPORTE ACADEMICO DE MATERIA
    // =========================================================

    private void generarReporteAcademicoMateria() {

        System.out.println();
        System.out.println(
                "===== REPORTE ACADEMICO DE MATERIA ====="
        );


        List<MateriaModel> materias =
                materiaRepository.getAll();


        if (materias.isEmpty()) {

            System.out.println(
                    "No existen materias registradas."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "MATERIAS:"
        );


        for (MateriaModel materia : materias) {

            System.out.println(
                    materia.getId()
                            + " - "
                            + materia.getNombre()
                            + " ["
                            + materia.getTipoCalificacion()
                            + "]"
            );
        }


        int materiaId =
                leerEntero(
                        "Seleccione ID de la materia: "
                );


        MateriaModel materia =
                materiaRepository.findById(
                        materiaId
                );


        if (materia == null) {

            System.out.println(
                    "Materia no encontrada."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "============================================"
        );

        System.out.println(
                "          REPORTE ACADEMICO"
        );

        System.out.println(
                "============================================"
        );

        System.out.println(
                "Materia: "
                        + materia.getNombre()
        );

        System.out.println(
                "Tipo de calificacion: "
                        + materia.getTipoCalificacion()
        );


        List<InscripcionModel> inscripciones =
                inscripcionRepository
                        .findByMateriaId(
                                materia.getId()
                        );


        if (inscripciones.isEmpty()) {

            System.out.println();

            System.out.println(
                    "No existen estudiantes inscritos en esta materia."
            );

            System.out.println(
                    "============================================"
            );

            return;
        }


        System.out.println();

        System.out.println(
                "Cantidad de estudiantes: "
                        + inscripciones.size()
        );


        for (InscripcionModel inscripcion : inscripciones) {

            EstudianteModel estudiante =
                    inscripcion.getEstudiante();


            System.out.println();
            System.out.println(
                    "--------------------------------------------"
            );

            System.out.println(
                    "Codigo: "
                            + estudiante.getCodigo()
            );

            System.out.println(
                    "Estudiante: "
                            + estudiante.getNombre()
            );

            System.out.println(
                    "Carrera: "
                            + estudiante.getCarrera()
            );


            List<CalificacionModel> calificaciones =
                    calificacionRepository
                            .findByInscripcionId(
                                    inscripcion.getId()
                            );


            if (calificaciones.isEmpty()) {

                System.out.println(
                        "Calificaciones: Sin calificaciones registradas."
                );

                System.out.println(
                        "Estado: SIN CALIFICACIONES"
                );

                continue;
            }


            System.out.println();
            System.out.println(
                    "CALIFICACIONES:"
            );


            for (CalificacionModel calificacion : calificaciones) {

                System.out.println(
                        "Evaluacion: "
                                + calificacion.getNombreEvaluacion()
                );

                System.out.println(
                        "Nota: "
                                + calificacion.getNota()
                );


                if (
                        materia.getTipoCalificacion()
                                == TipoCalificacion.PONDERACION
                ) {

                    Double ponderacion =
                            calificacion.getPonderacion();


                    if (ponderacion != null) {

                        System.out.println(
                                "Ponderacion: "
                                        + ponderacion
                                        + "%"
                        );
                    }
                }


                System.out.println(
                        "------------------------"
                );
            }


            double notaFinal =
                    calcularNotaFinal(
                            inscripcion,
                            calificaciones
                    );


            System.out.printf(
                    "Nota actual: %.2f%n",
                    notaFinal
            );


            if (
                    notaFinal
                            >= NOTA_MINIMA_APROBACION
            ) {

                System.out.println(
                        "Estado: APROBADO"
                );

            } else {

                System.out.println(
                        "Estado: REPROBADO"
                );
            }
        }


        System.out.println();
        System.out.println(
                "============================================"
        );
    }


    // =========================================================
    // OPCION 6
    // CONSULTAR ESTUDIANTES REPROBADOS
    // =========================================================

    private void consultarEstudiantesReprobados() {

        System.out.println();
        System.out.println(
                "===== ESTUDIANTES REPROBADOS ====="
        );


        List<InscripcionModel> inscripciones =
                inscripcionRepository.getAll();


        if (inscripciones.isEmpty()) {

            System.out.println(
                    "No existen estudiantes inscritos en materias."
            );

            return;
        }


        boolean hayReprobados =
                false;


        for (
                InscripcionModel inscripcion
                : inscripciones
        ) {

            List<CalificacionModel> calificaciones =
                    calificacionRepository
                            .findByInscripcionId(
                                    inscripcion.getId()
                            );


            // SI NO TIENE NOTAS, NO LO CONSIDERAMOS
            // APROBADO NI REPROBADO

            if (calificaciones.isEmpty()) {

                continue;
            }


            double notaFinal =
                    calcularNotaFinal(
                            inscripcion,
                            calificaciones
                    );


            if (
                    notaFinal
                            < NOTA_MINIMA_APROBACION
            ) {

                hayReprobados =
                        true;


                EstudianteModel estudiante =
                        inscripcion.getEstudiante();


                MateriaModel materia =
                        inscripcion.getMateria();


                System.out.println(
                        "--------------------------------------------"
                );

                System.out.println(
                        "Codigo: "
                                + estudiante.getCodigo()
                );

                System.out.println(
                        "Estudiante: "
                                + estudiante.getNombre()
                );

                System.out.println(
                        "Correo: "
                                + estudiante.getCorreo()
                );

                System.out.println(
                        "Carrera: "
                                + estudiante.getCarrera()
                );

                System.out.println(
                        "Materia: "
                                + materia.getNombre()
                );

                System.out.println(
                        "Tipo de calificacion: "
                                + materia.getTipoCalificacion()
                );

                System.out.printf(
                        "Nota actual: %.2f%n",
                        notaFinal
                );

                System.out.println(
                        "Estado: REPROBADO"
                );
            }
        }


        if (!hayReprobados) {

            System.out.println(
                    "No existen estudiantes reprobados."
            );

        } else {

            System.out.println(
                    "--------------------------------------------"
            );
        }
    }


    // =========================================================
    // CALCULAR NOTA FINAL
    // =========================================================

    private double calcularNotaFinal(
            InscripcionModel inscripcion,
            List<CalificacionModel> calificaciones
    ) {

        TipoCalificacion tipo =
                inscripcion
                        .getMateria()
                        .getTipoCalificacion();


        // =====================================================
        // CALCULO POR PROMEDIO
        // =====================================================

        if (
                tipo
                        == TipoCalificacion.PROMEDIO
        ) {

            double sumaNotas =
                    0;


            for (
                    CalificacionModel calificacion
                    : calificaciones
            ) {

                sumaNotas +=
                        calificacion.getNota();
            }


            return sumaNotas
                    / calificaciones.size();
        }


        // =====================================================
        // CALCULO POR PONDERACION
        // =====================================================

        if (
                tipo
                        == TipoCalificacion.PONDERACION
        ) {

            double sumaPonderada =
                    0;

            double totalPonderacion =
                    0;


            for (
                    CalificacionModel calificacion
                    : calificaciones
            ) {

                Double ponderacion =
                        calificacion.getPonderacion();


                if (
                        ponderacion != null
                                && ponderacion > 0
                ) {

                    sumaPonderada +=
                            calificacion.getNota()
                                    * ponderacion;


                    totalPonderacion +=
                            ponderacion;
                }
            }


            if (
                    totalPonderacion == 0
            ) {

                return 0;
            }


            return sumaPonderada
                    / totalPonderacion;
        }


        return 0;
    }


    // =========================================================
    // OPCION 7
    // GENERAR HISTORICO DEL ESTUDIANTE
    // =========================================================

    private void generarHistoricoEstudiante() {

        System.out.println();
        System.out.println(
                "===== HISTORICO DEL ESTUDIANTE ====="
        );


        List<EstudianteModel> estudiantes =
                estudianteRepository.getAll();


        if (estudiantes.isEmpty()) {

            System.out.println(
                    "No existen estudiantes registrados."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "ESTUDIANTES:"
        );


        for (EstudianteModel estudiante : estudiantes) {

            System.out.println(
                    estudiante.getId()
                            + " - "
                            + estudiante.getNombre()
                            + " ["
                            + estudiante.getCodigo()
                            + "]"
            );
        }


        int estudianteId =
                leerEntero(
                        "Seleccione ID del estudiante: "
                );


        EstudianteModel estudiante =
                estudianteRepository.findById(
                        estudianteId
                );


        if (estudiante == null) {

            System.out.println(
                    "Estudiante no encontrado."
            );

            return;
        }


        System.out.println();
        System.out.println(
                "============================================"
        );

        System.out.println(
                "        HISTORICO ACADEMICO"
        );

        System.out.println(
                "============================================"
        );

        System.out.println(
                "Codigo: "
                        + estudiante.getCodigo()
        );

        System.out.println(
                "Nombre: "
                        + estudiante.getNombre()
        );

        System.out.println(
                "Correo: "
                        + estudiante.getCorreo()
        );

        System.out.println(
                "Carrera: "
                        + estudiante.getCarrera()
        );


        List<InscripcionModel> inscripciones =
                inscripcionRepository
                        .findByEstudianteId(
                                estudianteId
                        );


        if (inscripciones.isEmpty()) {

            System.out.println();

            System.out.println(
                    "El estudiante no tiene materias asignadas."
            );

            System.out.println(
                    "============================================"
            );

            return;
        }


        System.out.println();
        System.out.println(
                "MATERIAS:"
        );


        for (InscripcionModel inscripcion : inscripciones) {

            MateriaModel materia =
                    inscripcion.getMateria();


            System.out.println();
            System.out.println(
                    "--------------------------------------------"
            );

            System.out.println(
                    "Materia: "
                            + materia.getNombre()
            );

            System.out.println(
                    "Tipo de calificacion: "
                            + materia.getTipoCalificacion()
            );


            List<CalificacionModel> calificaciones =
                    calificacionRepository
                            .findByInscripcionId(
                                    inscripcion.getId()
                            );


            if (calificaciones.isEmpty()) {

                System.out.println(
                        "Calificaciones: Sin calificaciones."
                );

                System.out.println(
                        "Estado: SIN CALIFICACIONES"
                );

                continue;
            }


            System.out.println();
            System.out.println(
                    "CALIFICACIONES:"
            );


            for (CalificacionModel calificacion : calificaciones) {

                System.out.println(
                        "Evaluacion: "
                                + calificacion.getNombreEvaluacion()
                );

                System.out.println(
                        "Nota: "
                                + calificacion.getNota()
                );


                if (
                        materia.getTipoCalificacion()
                                == TipoCalificacion.PONDERACION
                ) {

                    Double ponderacion =
                            calificacion.getPonderacion();


                    if (ponderacion != null) {

                        System.out.println(
                                "Ponderacion: "
                                        + ponderacion
                                        + "%"
                        );
                    }
                }


                System.out.println(
                        "------------------------"
                );
            }


            double notaFinal =
                    calcularNotaFinal(
                            inscripcion,
                            calificaciones
                    );


            System.out.printf(
                    "Nota actual de la materia: %.2f%n",
                    notaFinal
            );


            if (
                    notaFinal
                            >= NOTA_MINIMA_APROBACION
            ) {

                System.out.println(
                        "Estado: APROBADO"
                );

            } else {

                System.out.println(
                        "Estado: REPROBADO"
                );
            }
        }


        System.out.println();
        System.out.println(
                "============================================"
        );

        System.out.println(
                "FIN DEL HISTORICO ACADEMICO"
        );

        System.out.println(
                "============================================"
        );
    }


    // =========================================================
    // OPCION 8
    // LISTAR MATERIAS Y CANTIDAD DE ESTUDIANTES
    // =========================================================

    private void listarMateriasCantidadEstudiantes() {

        System.out.println();
        System.out.println(
                "===== MATERIAS Y ESTUDIANTES ====="
        );


        List<MateriaModel> materias =
                materiaRepository.getAll();


        if (materias.isEmpty()) {

            System.out.println(
                    "No existen materias registradas."
            );

            return;
        }


        for (
                MateriaModel materia
                : materias
        ) {

            // BUSCAR INSCRIPCIONES DE LA MATERIA

            List<InscripcionModel> inscripciones =
                    inscripcionRepository
                            .findByMateriaId(
                                    materia.getId()
                            );


            int cantidadEstudiantes =
                    inscripciones.size();


            System.out.println();

            System.out.println(
                    "============================================"
            );

            System.out.println(
                    "ID: "
                            + materia.getId()
            );

            System.out.println(
                    "Materia: "
                            + materia.getNombre()
            );

            System.out.println(
                    "Tipo de calificacion: "
                            + materia.getTipoCalificacion()
            );

            System.out.println(
                    "Cantidad de estudiantes: "
                            + cantidadEstudiantes
            );


            // =================================================
            // MOSTRAR ESTUDIANTES
            // =================================================

            if (inscripciones.isEmpty()) {

                System.out.println(
                        "Estudiantes: Ninguno asignado."
                );

            } else {

                System.out.println();

                System.out.println(
                        "ESTUDIANTES INSCRITOS:"
                );


                for (
                        InscripcionModel inscripcion
                        : inscripciones
                ) {

                    EstudianteModel estudiante =
                            inscripcion.getEstudiante();


                    System.out.println(
                            "--------------------------------------------"
                    );

                    System.out.println(
                            "ID: "
                                    + estudiante.getId()
                    );

                    System.out.println(
                            "Codigo: "
                                    + estudiante.getCodigo()
                    );

                    System.out.println(
                            "Nombre: "
                                    + estudiante.getNombre()
                    );

                    System.out.println(
                            "Correo: "
                                    + estudiante.getCorreo()
                    );

                    System.out.println(
                            "Carrera: "
                                    + estudiante.getCarrera()
                    );
                }
            }


            System.out.println(
                    "============================================"
            );
        }
    }


    // =========================================================
    // METODO AUXILIAR
    // LEER ENTEROS
    // =========================================================

    private int leerEntero(
            String mensaje
    ) {

        while (true) {

            try {

                System.out.print(
                        mensaje
                );

                return Integer.parseInt(
                        scanner
                                .nextLine()
                                .trim()
                );

            } catch (
                    NumberFormatException e
            ) {

                System.out.println(
                        "Debe ingresar un numero entero."
                );
            }
        }
    }


    // =========================================================
    // METODO AUXILIAR
    // LEER DECIMALES
    // =========================================================

    private double leerDouble(
            String mensaje
    ) {

        while (true) {

            try {

                System.out.print(
                        mensaje
                );

                return Double.parseDouble(
                        scanner
                                .nextLine()
                                .trim()
                                .replace(
                                        ",",
                                        "."
                                )
                );

            } catch (
                    NumberFormatException e
            ) {

                System.out.println(
                        "Debe ingresar un numero valido."
                );
            }
        }
    }
}