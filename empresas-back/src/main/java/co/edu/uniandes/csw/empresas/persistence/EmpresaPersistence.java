/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.empresas.persistence;

import co.edu.uniandes.csw.empresas.entities.EmpresaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para . Se conecta a través del EmpresaEntity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author ISIS2603
 */
@Stateless
public class EmpresaPersistence {

    private static final Logger LOGGER = Logger.getLogger(EmpresaPersistence.class.getName());

    @PersistenceContext(unitName = "BookStorePU")
    protected EntityManager em;

    /**
     * Crea un autor en la base de datos
     *
     * @param empresaEntity objeto author que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public EmpresaEntity create(EmpresaEntity empresaEntity) {
        LOGGER.log(Level.INFO, "Creando un autor nuevo");
        /* Note que hacemos uso de un método propio de EntityManager para persistir la author en la base de datos.
        Es similar a "INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
        em.persist(empresaEntity);
        LOGGER.log(Level.INFO, "Autor creado");
        return empresaEntity;
    }

    /**
     * Devuelve todas las authores de la base de datos.
     *
     * @return una lista con todas las authores que encuentre en la base de
     * datos, "select u from EmpresaEntity u" es como un "select * from
     * EmpresaEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<EmpresaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        // Se crea un query para buscar todas las authores en la base de datos.
        TypedQuery query = em.createQuery("select u from EmpresaEntity u", EmpresaEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de authores.
        return query.getResultList();
    }

    /**
     * Busca si hay alguna author con el id que se envía de argumento
     *
     * @param authorsId: id correspondiente a la author buscada.
     * @return un author.
     */
    public EmpresaEntity find(Long authorsId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", authorsId);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from EmpresaEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(EmpresaEntity.class, authorsId);
    }

    /**
     * Actualiza una author.
     *
     * @param empresaEntity: la author que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una author con los cambios aplicados.
     */
    public EmpresaEntity update(EmpresaEntity empresaEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", empresaEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la author con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(empresaEntity);
    }

    /**
     * Borra una author de la base de datos recibiendo como argumento el id de
     * la author
     *
     * @param authorsId: id correspondiente a la author a borrar.
     */
    public void delete(Long authorsId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", authorsId);
        // Se hace uso de mismo método que esta explicado en public EmpresaEntity find(Long id) para obtener la author a borrar.
        EmpresaEntity empresaEntity = em.find(EmpresaEntity.class, authorsId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "EmpresaEntity", volvemos hacer uso de un método propio del
        EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
        Es similar a "delete from EmpresaEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(empresaEntity);
    }
}
