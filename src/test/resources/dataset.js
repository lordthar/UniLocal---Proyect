db.clientes.insertMany([
    {
        _id: 'Cliente1',
        nickname: 'juanito',
        ciudadResidencia: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'juan@email.com',
        password: '$2a$10$PfdFflbMKYDVwiyOhpmjxed1q/oDay8jI0UdUXWfpIq5XEgfABs6u',
        nombre: 'Juan',
        estadoRegistro: 'ACTIVO',
        codigosFavorito: [
            {
                fechaDeAgregado: '2023-05-15',
                codigoNegocio: 'N01'
            },
            {
                fechaDeAgregado: '2023-06-20',
                codigoNegocio: 'N02'
            }
        ],
        tipoCliente: 'PREMIUM',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente2',
        nickname: 'maria',
        ciudadResidencia: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'maria@email.com',
        password: '$2a$10$VgT0Gf1q2JMLqD6PmR/.e.eUc0VtBv9ZUPy5dljQLbKRbuDl0VPJG',
        nombre: 'Maria',
        estadoRegistro: 'ACTIVO',
        codigosFavorito: [
            {
                fechaDeAgregado: '2023-07-10',
                codigoNegocio: 'N03'
            },
            {
                fechaDeAgregado: '2023-08-25',
                codigoNegocio: 'N04'
            },
            {
                fechaDeAgregado: '2023-09-05',
                codigoNegocio: 'N05'
            }
        ],
        tipoCliente: 'PREMIUM',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente3',
        nickname: 'Pepe',
        ciudadResidencia: 'Armenia',
        fotoPerfil: 'mi foto',
        email: 'pepe@email.com',
        password: '$2a$10$N16O/jf21U5eUN7wsfkTpewq.ODCVvkjRsCu9t.8bvrUeIAUaKyUK',
        nombre: 'Pepe',
        estadoRegistro: 'ACTIVO',
        codigosFavorito: [
            {
                fechaDeAgregado: '2023-10-12',
                codigoNegocio: 'N01'
            },
            {
                fechaDeAgregado: '2023-11-30',
                codigoNegocio: 'N03'
            },
            {
                fechaDeAgregado: '2023-12-08',
                codigoNegocio: 'N05'
            },
            {
                fechaDeAgregado: '2024-01-15',
                codigoNegocio: 'N02'
            }
        ],
        tipoCliente: 'NORMAL',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente4',
        nickname: 'Mario',
        ciudadResidencia: 'Pereira',
        fotoPerfil: 'mi foto',
        email: 'mario@email.com',
        password: '$2a$10$GNy5K/A8hEQtVzRwQzJxcOR6BAmbRWxUrJXWojnPcyZlExidcJKwS',
        nombre: 'mario',
        estadoRegistro: 'ACTIVO',
        codigosFavorito: [
            {
                fechaDeAgregado: '2023-10-12',
                codigoNegocio: 'N01'
            },
            {
                fechaDeAgregado: '2023-11-30',
                codigoNegocio: 'N03'
            },
            {
                fechaDeAgregado: '2023-12-08',
                codigoNegocio: 'N05'
            },
            {
                fechaDeAgregado: '2024-01-15',
                codigoNegocio: 'N02'
            }
        ],
        tipoCliente: 'NORMAL',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente'
    },
    {
        _id: 'Cliente5',
        nickname: 'Laura',
        ciudadResidencia: 'Calarca',
        fotoPerfil: 'mi foto',
        email: 'laura@email.com',
        password: '$2a$10$Wq.sg9uLP1Iq3e9wEHWn9.DwFFf24.4ZEEveC3DNoA95z3NTqiVZ.',
        nombre: 'laura',
        estadoRegistro: 'ACTIVO',
        codigosFavorito: [
            {
                fechaDeAgregado: '2024-02-20',
                codigoNegocio: 'N04'
            },
            {
                fechaDeAgregado: '2024-03-10',
                codigoNegocio: 'N01'
            },
            {
                fechaDeAgregado: '2024-04-05',
                codigoNegocio: 'N03'
            }
        ],
        tipoCliente: 'NORMAL',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente'
    }
]);

db.negocios.insertMany([
    {
        _id: 'N01',
        nombre: 'El pandebono',
        descripcion: 'Panaderia con diferentes tipos de delicias, como postres, pasteles entre otros',
        codigoCliente: 'Cliente1',
        imagenes: [
            'im01',
            'im02'
        ],
        telefonos: [
            'tl01',
            'tl02'
        ],
        horarios: [
            {
                dia: 'Lunes',
                horaInicio: ISODate('2024-04-14T12:30:00.000Z'),
                horaFin: ISODate('2024-04-14T06:45:00.000Z')
            },
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T06:00:00.000Z'),
                horaFin: ISODate('2024-04-14T11:00:00.000Z')
            },
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T13:00:00.000Z'),
                horaFin: ISODate('2024-04-14T18:00:00.000Z')
            },
            {
                dia: 'Viernes',
                horaInicio: ISODate('2024-04-14T06:00:00.000Z'),
                horaFin: ISODate('2024-04-14T11:00:00.000Z')
            },
            {
                dia: 'Sabado',
                horaInicio: ISODate('2024-04-14T06:00:00.000Z'),
                horaFin: ISODate('2024-04-14T14:00:00.000Z')
            }
        ],
        coordenada: {
            latitud: 15.2,
            longitud: 30.22
        },
        estadoNegocio: 'APROBADO',
        tipoNegocio: 'PANADERIA',
        estadoRegistro: 'ACTIVO',
        historialRevisiones: [
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'La descripcion no cumple con el tipo de negocio',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Las imagenes son inapropiadas',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'El nombre incumple con las reglas',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'des',
                estadoNegocio: 'APROBADO'
            }
        ],
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio'
    },
    {
        _id: 'N02',
        nombre: 'El café de la esquina',
        descripcion: 'Cafetería con ambiente acogedor y variedad de bebidas calientes y refrigerios.',
        codigoCliente: 'Cliente1',
        imagenes: [
            'imagen03.jpg',
            'imagen04.jpg'
        ],
        telefonos: [
            'num1',
            'num2'
        ],
        horarios: [
            {
                dia: 'Lunes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T07:00:00.000Z')
            },
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Miércoles',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Jueves',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Viernes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Sábado',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Domingo',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            }
        ],
        coordenada: {
            latitud: 20.15,
            longitud: 35.3
        },
        estadoNegocio: 'APROBADO',
        tipoNegocio: 'CAFETERIA',
        estadoRegistro: 'ACTIVO',
        historialRevisiones: [
            {
                codigoModerador: 'M02',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M02',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Imagenes inapropiadas',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M02',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M02',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Revisión de horarios de atención.',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M02',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            }
        ],
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio'
    },
    {
        _id: 'N03',
        nombre: 'La pizzería italiana',
        descripcion: 'Auténtica pizzería con horno de leña y variedad de ingredientes frescos.',
        codigoCliente: 'Cliente2',
        imagenes: [
            'imagen05.jpg',
            'imagen06.jpg'
        ],
        telefonos: [
            'num1',
            'num2'
        ],
        horarios: [
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Miércoles',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Jueves',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Viernes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Sábado',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Domingo',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            }
        ],
        coordenada: {
            latitud: 25.3,
            longitud: 40.4
        },
        estadoNegocio: 'RECHAZADO',
        tipoNegocio: 'OTRO',
        estadoRegistro: 'ACTIVO',
        historialRevisiones: [
            {
                codigoModerador: 'M03',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M03',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Revisión de la calidad de las imagenes.',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M03',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Imagenes malas.',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M03',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '.',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M03',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Revisión de la ubicación en el mapa.',
                estadoNegocio: 'RECHAZADO'
            }
        ],
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio'
    },
    {
        _id: 'N04',
        nombre: 'La boutique de moda',
        descripcion: 'Tienda de ropa con las últimas tendencias y marcas exclusivas.',
        codigoCliente: 'Cliente3',
        imagenes: [
            'imagen07.jpg',
            'imagen08.jpg'
        ],
        telefonos: [
            'num1',
            'num2'
        ],
        horarios: [
            {
                dia: 'Lunes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Miércoles',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Jueves',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Viernes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Sábado',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Domingo',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            }
        ],
        coordenada: {
            latitud: 30.25,
            longitud: 45.35
        },
        estadoNegocio: 'APROBADO',
        tipoNegocio: 'OTRO',
        estadoRegistro: 'ACTIVO',
        historialRevisiones: [
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Nombre ofensivo',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Numeros falsos',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M01',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            }
        ],
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio'
    },
    {
        _id: 'N05',
        nombre: 'Gimnasio FitZone',
        descripcion: 'Gimnasio moderno con equipos de última generación y entrenadores profesionales.',
        codigoCliente: 'Cliente2',
        imagenes: [
            'imagen09.jpg',
            'imagen10.jpg'
        ],
        telefonos: [
            'num1',
            'num2'
        ],
        horarios: [
            {
                dia: 'Martes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Miércoles',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Jueves',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Viernes',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            },
            {
                dia: 'Sábado',
                horaInicio: ISODate('2024-04-14T07:00:00.000Z'),
                horaFin: ISODate('2024-04-14T12:20:00.000Z')
            }
        ],
        coordenada: {
            latitud: 35.2,
            longitud: 50.22
        },
        estadoNegocio: 'APROBADO',
        tipoNegocio: 'OTRO',
        estadoRegistro: 'ACTIVO',
        historialRevisiones: [
            {
                codigoModerador: 'M05',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M05',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Revisión de equipos y servicios.',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M05',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            },
            {
                codigoModerador: 'M05',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: 'Actualización de información de contacto.',
                estadoNegocio: 'RECHAZADO'
            },
            {
                codigoModerador: 'M05',
                fecha: ISODate("2025-02-13T12:30:45.000Z"),
                descripcion: '',
                estadoNegocio: 'APROBADO'
            }
        ],
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio'
    }
]);

db.comentarios.insertMany([
    {
        _id: 'C01',
        calificacion: 4,
        fecha: ISODate('2022-10-01T08:00:00.000Z'),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'N02',
        mensaje: 'Excelente ambiente y café delicioso.',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario'
    },
    {
        _id: 'C02',
        calificacion: 5,
        fecha: ISODate('2022-10-05T12:30:00.000Z'),
        codigoCliente: 'Cliente2',
        codigoNegocio: 'N03',
        mensaje: 'Las pizzas son increíbles, definitivamente volveré.',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario'
    },
    {
        _id: 'C03',
        calificacion: 4,
        fecha: ISODate('2022-10-15T10:00:00.000Z'),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'N05',
        mensaje: 'El gimnasio tiene buenos equipos y el personal es muy atento.',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario'
    },
    {
        _id: 'C04',
        calificacion: 4,
        fecha: ISODate('2022-10-15T10:00:00.000Z'),
        codigoCliente: 'Cliente1',
        codigoNegocio: 'N05',
        mensaje: 'El gimnasio tiene buenos equipos y el personal es muy atento.',
        respuesta: '',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario'
    },
    {
        _id: 'C05',
        calificacion: 2,
        fecha: ISODate('2022-10-20T14:20:00.000Z'),
        codigoCliente: 'Cliente2',
        codigoNegocio: 'N02',
        mensaje: 'El café estaba frío y el servicio fue lento.',
        respuesta: 'Lamentamos tu experiencia, tomaremos medidas para mejorar nuestro servicio.',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario'
    }
]);

db.moderadores.insertMany([
    {
        _id: 'M01',
        nickname: 'moderador1',
        password: '$2a$10$eV0V2jyOWAJdoMRu/PUYJ.ZZyUBw5QI90isTuZFT9cRqdU5euYuVq',
        email: 'jakoja5649@etopys.com',
        estadoRegistro: 'ACTIVO',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador'
    },
    {
        _id: 'M02',
        nickname: 'moderador2',
        password: '$2a$10$171elT/NPONXB.TkIVfRHeEjYstUoYCYfYIygxNILRtKYRRG8z.am',
        email: 'moderador2@example.com',
        estadoRegistro: 'ACTIVO',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador'
    },
    {
        _id: 'M03',
        nickname: 'moderador3',
        password: '$2a$10$eqY2xGMLI60XpihX9.fo4e2T1XUXNVfEjZ842FebknK/Tvm0q5BEm',
        email: 'moderador3@example.com',
        estadoRegistro: 'ACTIVO',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador'
    },
    {
        _id: 'M04',
        nickname: 'moderador4',
        password: '$2a$10$DmnS4NlXqzFsYgiPrSxP7uaNF3d1DYKOG9qOzpw6mrCa6Ht8r5Pfu',
        email: 'moderador4@example.com',
        estadoRegistro: 'ACTIVO',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador'
    },
    {
        _id: 'M05',
        nickname: 'moderador5',
        password: '$2a$10$izFmcLMNdxjJhCninbUqBe8mZ6vNtQHUK79MWDoUTmO2S02M5ViM2',
        email: 'moderador5@example.com',
        estadoRegistro: 'ACTIVO',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador'
    }
]);

db.pqrs.insertMany([
    {
        _id: 'PQRS01',
        titulo: 'Sugerencia de mejora en el menú',
        descripcion: 'Me gustaría sugerir agregar opciones vegetarianas al menú del restaurante.',
        codigoCliente: 'Cliente1',
        codigoNegocio: 'N03',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs'
    },
    {
        _id: 'PQRS02',
        titulo: 'Reclamo por servicio lento',
        descripcion: 'El servicio en la tienda fue extremadamente lento, esperé más de 30 minutos para ser atendido.',
        codigoCliente: 'Cliente2',
        codigoNegocio: 'N04',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs'
    },
    {
        _id: 'PQRS03',
        titulo: 'Petición de información adicional',
        descripcion: 'Me gustaría obtener más información sobre las clases ofrecidas en el gimnasio.',
        codigoCliente: 'Cliente3',
        codigoNegocio: 'N05',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs'
    },
    {
        _id: 'PQRS04',
        titulo: 'Queja por producto defectuoso',
        descripcion: 'Compré un artículo en la tienda y resultó estar defectuoso. Quiero un reembolso.',
        codigoCliente: 'Cliente1',
        codigoNegocio: 'N04',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs'
    },
    {
        _id: 'PQRS05',
        titulo: 'Solicitud de cambio de horario',
        descripcion: 'Quisiera solicitar un cambio en el horario de atención del café para que abra más temprano los fines de semana.',
        codigoCliente: 'Cliente2',
        codigoNegocio: 'N02',
        _class: 'co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs'
    }
]);