package io.github.educontessi.core.address.adapters.out.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "country_view")
public class CountryEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "acronym")
    private String acronym;

    @NotNull
    @Size(min = 2, max = 10)
    @Column(name = "bacen")
    private String bacen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getBacen() {
        return bacen;
    }

    public void setBacen(String bacen) {
        this.bacen = bacen;
    }
}
