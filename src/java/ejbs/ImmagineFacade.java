/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entita.Immagine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mauro
 */
@Stateless
public class ImmagineFacade extends AbstractFacade<Immagine> {

    @PersistenceContext(unitName = "prova3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImmagineFacade() {
        super(Immagine.class);
    }
    
}
