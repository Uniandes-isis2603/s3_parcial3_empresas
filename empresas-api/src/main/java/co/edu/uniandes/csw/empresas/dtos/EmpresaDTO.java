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
package co.edu.uniandes.csw.empresas.dtos;

import co.edu.uniandes.csw.empresas.adapters.DateAdapter;
import co.edu.uniandes.csw.empresas.entities.EmpresaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO Objeto de transferencia de datos de Autores. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "empresa": string,
 *      "birthDate": date,
 *      "image": string
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "empresa": "Gabriel García Márquez",
 *      "birthDate": "23091935",
 *      "image": "mifoto.com"
 *   }
 *
 * </pre>
 *
 * @ ISIS2603
 */
public class EmpresaDTO implements Serializable {

    private Long id;
    private String empresa;
    private String ciudad;
    private String image;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date aniocreacion;

    /**
     * Constructor vacio
     */
    public EmpresaDTO() {
    }

    /**
     * Crea un objeto DTO a partir de un objeto Entity.
     *
     * @param Entity Entidad Entity desde la cual se va a crear el nuevo objeto.
     *
     */
    public EmpresaDTO(EmpresaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.empresa = entity.getEmpresa();
            this.aniocreacion = entity.getAniocreacion();
            this.ciudad = entity.getCiudad();
            this.image = entity.getImage();

        }
    }

    /**
     * Convierte un objeto DTO a Entity.
     *
     * @return Nueva objeto Entity.
     *
     */
    public EmpresaEntity toEntity() {
        EmpresaEntity entity = new EmpresaEntity();
        entity.setId(this.getId());
        entity.setEmpresa(this.getEmpresa());
        entity.setCiudad(this.ciudad);
        entity.setImage(this.image);
        entity.setAniocreacion(this.getAniocreacion());
        return entity;
    }

    /**
     * Obtiene el atributo id.
     *
     * @return atributo id.
     *
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el valor del atributo id.
     *
     * @param id nuevo valor del atributo
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el atributo empresa.
     *
     * @return atributo empresa.
     *
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * Establece el valor del atributo empresa.
     *
     * @param empresa nuevo valor del atributo
     *
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el atributo descripción
     *
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Ontiene el atributo de imagen
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Establece el atributo de descripción
     *
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Establece la imagen del autor
     *
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the aniocreacion
     */
    public Date getAniocreacion() {
        return aniocreacion;
    }

    /**
     * @param aniocreacion the aniocreacion to set
     */
    public void setAniocreacion(Date aniocreacion) {
        this.aniocreacion = aniocreacion;
    }
}
