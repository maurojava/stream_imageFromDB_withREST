/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entita;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mauro
 */
@Entity
@Table(name = "IMMAGINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Immagine.findAll", query = "SELECT i FROM Immagine i")
    , @NamedQuery(name = "Immagine.findById", query = "SELECT i FROM Immagine i WHERE i.id = :id")
    , @NamedQuery(name = "Immagine.findByContentType", query = "SELECT i FROM Immagine i WHERE i.contentType = :contentType")
    , @NamedQuery(name = "Immagine.findByName", query = "SELECT i FROM Immagine i WHERE i.name = :name")})
public class Immagine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "BYTE_IMMAGINE")
    private byte[] byteImmagine;
    @Size(max = 255)
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;

    public Immagine() {
    }

    public Immagine(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Immagine)) {
            return false;
        }
        Immagine other = (Immagine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entita.Immagine[ id=" + id + " ]";
    }

    public byte[] getByteImmagine() {
        return byteImmagine;
    }

    public void setByteImmagine(byte[] byteImmagine) {
        this.byteImmagine = byteImmagine;
    }
    
}
