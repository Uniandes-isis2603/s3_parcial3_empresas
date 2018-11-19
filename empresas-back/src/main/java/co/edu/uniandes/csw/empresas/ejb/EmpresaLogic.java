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
package co.edu.uniandes.csw.empresas.ejb;


import co.edu.uniandes.csw.empresas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.empresas.entities.EmpresaEntity;
import co.edu.uniandes.csw.empresas.persistence.EmpresaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * .
 *
 * @author ISIS2603
 */
@Stateless
public class EmpresaLogic {

    private static final Logger LOGGER = Logger.getLogger(EmpresaLogic.class.getName());

    @Inject
    private EmpresaPersistence persistence;

    /**
     * Se encarga de crear un  en la base de datos.
     *
     * @param empresaEntity Objeto de EmpresaEntity con los datos nuevos
     * @return Objeto de EmpresaEntity con los datos nuevos y su ID.
     */
    public EmpresaEntity create(EmpresaEntity empresaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del autor");
        EmpresaEntity newEmpresaEntity = persistence.create(empresaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del autor");
        return newEmpresaEntity;
    }

    /**
     * Obtiene la lista de los registros de .
     *
     * @return Colección de objetos de EmpresaEntity.
     */
    public List<EmpresaEntity> gets() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        List<EmpresaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }

    /**
     * Obtiene los datos de una instancia de  a partir de su ID.
     *
     * @param authorsId Identificador de la instancia a consultar
     * @return Instancia de EmpresaEntity con los datos del  consultado.
     */
    public EmpresaEntity get(Long authorsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el autor con id = {0}", authorsId);
        EmpresaEntity empresaEntity = persistence.find(authorsId);
        if (empresaEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", authorsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor con id = {0}", authorsId);
        return empresaEntity;
    }

    /**
     * Actualiza la información de una instancia de .
     *
     * @param authorsId Identificador de la instancia a actualizar
     * @param empresaEntity Instancia de EmpresaEntity con los nuevos datos.
     * @return Instancia de EmpresaEntity con los datos actualizados.
     */
    public EmpresaEntity update(Long authorsId, EmpresaEntity empresaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", authorsId);
        EmpresaEntity newEmpresaEntity = persistence.update(empresaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", authorsId);
        return newEmpresaEntity;
    }

}
