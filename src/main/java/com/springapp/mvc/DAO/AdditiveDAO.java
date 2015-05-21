package com.springapp.mvc.DAO;

import com.springapp.mvc.entity.Additive;
import com.springapp.mvc.entity.Coffee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AdditiveDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Coffee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List coffees = session.createQuery("from Additive").list();
        return coffees;
    }

    @Transactional
     @SuppressWarnings("unchecked")
     public Additive getById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Additive.class)
                .add(Restrictions.eq("id", id));
        return (Additive) criteria.list().get(0);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public Additive getByName(String name) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Additive.class)
                .add(Restrictions.eq("additiveName", name));
        return (Additive) criteria.list().get(0);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void save(Additive additive) {
        sessionFactory.getCurrentSession().save(additive);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }
}
