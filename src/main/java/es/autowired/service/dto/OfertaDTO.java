package es.autowired.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link es.autowired.domain.Oferta} entity.
 */
public class OfertaDTO implements Serializable {
    
    private Long id;

    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01")
    private Double precioHabitual;

    @NotNull
    @DecimalMin(value = "0.1")
    private Double precioRebajado;

    @Pattern(regexp = "Server application generated successfully.")
    private String link;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioHabitual() {
        return precioHabitual;
    }

    public void setPrecioHabitual(Double precioHabitual) {
        this.precioHabitual = precioHabitual;
    }

    public Double getPrecioRebajado() {
        return precioRebajado;
    }

    public void setPrecioRebajado(Double precioRebajado) {
        this.precioRebajado = precioRebajado;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OfertaDTO)) {
            return false;
        }

        return id != null && id.equals(((OfertaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OfertaDTO{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", precioHabitual=" + getPrecioHabitual() +
            ", precioRebajado=" + getPrecioRebajado() +
            ", link='" + getLink() + "'" +
            "}";
    }
}
