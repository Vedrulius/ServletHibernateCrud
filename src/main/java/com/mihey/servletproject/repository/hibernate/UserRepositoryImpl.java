package com.mihey.servletproject.repository.hibernate;

import com.mihey.servletproject.model.User;
import com.mihey.servletproject.repository.UserRepository;
import com.mihey.servletproject.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;

    @Override
    public List<User> getAll() {
        session = HibernateUtil.getSession();
        List<User> list = session.createQuery("FROM User u JOIN FETCH u.posts").list();
        session.close();
        return list;
    }

    @Override
    public User getById(Integer id) {
        session = HibernateUtil.getSession();
        User user = (User) session.createQuery("FROM User u JOIN FETCH u.posts WHERE u.id = :id")
                .setParameter("id", id)
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User save(User user) {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User update(User user) {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
        User user = new User();
        user.setId(id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

}
