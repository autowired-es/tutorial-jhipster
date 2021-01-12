package es.autowired.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Oferta.
 */
@Entity
@Table(name = "oferta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "precio_habitual", nullable = false)
    private Double precioHabitual;

    @NotNull
    @DecimalMin(value = "0.1")
    @Column(name = "precio_rebajado", nullable = false)
    private Double precioRebajado;

    @Pattern(regexp = "Server application generated successfully.")
    @Column(name = "link")
    private String link;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Oferta descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioHabitual() {
        return precioHabitual;
    }

    public Oferta precioHabitual(Double precioHabitual) {
        this.precioHabitual = precioHabitual;
        return this;
    }

    public void setPrecioHabitual(Double precioHabitual) {
        this.precioHabitual = precioHabitual;
    }

    public Double getPrecioRebajado() {
        return precioRebajado;
    }

    public Oferta precioRebajado(Double precioRebajado) {
        this.precioRebajado = precioRebajado;
        return this;
    }

    public void setPrecioRebajado(Double precioRebajado) {
        this.precioRebajado = precioRebajado;
    }

    public String getLink() {
        return link;
    }

    public Oferta link(String link) {
        this.link = link;
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Oferta)) {
            return false;
        }
        return id != null && id.equals(((Oferta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Oferta{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            ", precioHabitual=" + getPrecioHabitual() +
            ", precioRebajado=" + getPrecioRebajado() +
            ", link='" + getLink() + "'" +
            "}";
    }
}
