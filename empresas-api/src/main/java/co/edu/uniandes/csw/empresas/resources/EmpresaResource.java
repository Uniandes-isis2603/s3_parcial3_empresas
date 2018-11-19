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
package co.edu.uniandes.csw.empresas.resources;

import co.edu.uniandes.csw.empresas.dtos.EmpresaDTO;
import co.edu.uniandes.csw.empresas.ejb.EmpresaLogic;
import co.edu.uniandes.csw.empresas.entities.EmpresaEntity;
import co.edu.uniandes.csw.empresas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.empresa.mappers.WebApplicationExceptionMapper;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "authors".
 *
 * @author ISIS2603
 * @version 1.0
 */
@Path("/empresas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class EmpresaResource {

    private static final Logger LOGGER = Logger.getLogger(EmpresaResource.class.getName());

    @Inject
    private EmpresaLogic empresaLogic;

    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     */
    @POST
    public EmpresaDTO create(EmpresaDTO empresa) {
        LOGGER.log(Level.INFO, "Resource create: input: {0}", empresa.toString());
        EmpresaDTO authorDTO = new EmpresaDTO(empresaLogic.create(empresa.toEntity()));
        LOGGER.log(Level.INFO, "Resource create: output: {0}", authorDTO.toString());
        return authorDTO;
    }

    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link DetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<EmpresaDTO> gets() {
        LOGGER.info("Resource gets: input: void");
        List<EmpresaDTO> listas = listEntity2DTO(empresaLogic.gets());
        LOGGER.log(Level.INFO, "Resource gets: output: {0}", listas.toString());
        return listas;
    }

    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param authorsId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link EmpresaDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{authorsId: \\d+}")
    public EmpresaDTO get(@PathParam("authorsId") Long authorsId) {
        LOGGER.log(Level.INFO, "Resource get: input: {0}", authorsId);
        EmpresaEntity authorEntity = empresaLogic.get(authorsId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
        }
        EmpresaDTO detailDTO = new EmpresaDTO(authorEntity);
        LOGGER.log(Level.INFO, "Resource get: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza el autor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param authorsId Identificador del autor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param empresa {@link DetailDTO} El autor que se desea guardar.
     * @return JSON {@link DetailDTO} - El autor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor a
     * actualizar.
     */
    @PUT
    @Path("{authorsId: \\d+}")
    public EmpresaDTO update(@PathParam("authorsId") Long authorsId, EmpresaDTO empresa) {
        LOGGER.log(Level.INFO, "Resource update: input: authorsId: {0} , empresa: {1}", new Object[]{authorsId, empresa.toString()});
        empresa.setId(authorsId);
        if (empresaLogic.get(authorsId) == null) {
            throw new WebApplicationException("El recurso /authors/" + authorsId + " no existe.", 404);
        }
        EmpresaDTO detailDTO = new EmpresaDTO(empresaLogic.update(authorsId, empresa.toEntity()));
        LOGGER.log(Level.INFO, "Resource update: output: {0}", detailDTO.toString());
        return detailDTO;
    }

   

  
    /**
     * Convierte una lista de Entity a una lista de EmpresaDTO.
     *
     * @param entityList Lista de Entity a convertir.
     * @return Lista de EmpresaDTO convertida.
     */
    private List<EmpresaDTO> listEntity2DTO(List<EmpresaEntity> entityList) {
        List<EmpresaDTO> list = new ArrayList<>();
        for (EmpresaEntity entity : entityList) {
            list.add(new EmpresaDTO(entity));
        }
        return list;
    }
}
