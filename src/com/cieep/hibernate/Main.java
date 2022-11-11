package com.cieep.hibernate;

import com.cieep.hibernate.modelos.Abonado;
import com.cieep.hibernate.modelos.Alquiler;
import com.cieep.hibernate.modelos.Autor;
import com.cieep.hibernate.modelos.Libro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Abonado abonado = new Abonado();
        abonado.setNombre("Manolito");
        Autor autor = new Autor();
        autor.setNombre("Javier");
        Libro libro = new Libro();
        libro.setTitulo("Por qué no dedicarse a la Programación");
        libro.setAutor(autor);
        Alquiler alquiler = new Alquiler();
        alquiler.setFecha(Date.valueOf(LocalDate.now()));
        abonado.getAlquileres().add(alquiler);
        libro.getAlquires().add(alquiler);
        autor.getLibros().add(libro);

        // CREA LA VARIABLE QUE PUEDE CONECTAR
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // LA VARIBLE DE LA CONEXiÓN
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.persist(abonado);
        session.persist(autor);
        session.persist(libro);
        session.persist(alquiler);

        session.getTransaction().commit();
        session.close();
    }
}