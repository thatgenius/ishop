package com.springapp.mvc.DAO;

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
public class CoffeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Coffee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List coffees = session.createQuery("from Coffee").list();
        return coffees;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public Coffee getById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Coffee.class)
                .add(Restrictions.eq("id", id));
        sessionFactory.getCurrentSession().update(criteria.list().get(0));
        return (Coffee) criteria.list().get(0);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void save(Coffee coffee) {
        sessionFactory.getCurrentSession().save(coffee);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }
}
