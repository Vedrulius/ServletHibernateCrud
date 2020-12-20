package com.mihey.servletproject.repository.hibernate;

import com.mihey.servletproject.model.Region;
import com.mihey.servletproject.repository.RegionRepository;
import com.mihey.servletproject.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class RegionRepositoryImpl implements RegionRepository {

    private Session session;

    @Override
    public List<Region> getAll() {
        session = HibernateUtil.getSession();
        List<Region> list = session.createQuery("FROM Post").list();
        session.close();
        return list;
    }

    @Override
    public Region getById(Integer id) {
        session = HibernateUtil.getSession();
        Region region = session.get(Region.class, id);
        session.close();
        return region;
    }

    @Override
    public Region save(Region region) {
        Region region1 = findRegion(region);
        if (region1 == null) {
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.saveOrUpdate(region);
            session.getTransaction().commit();
            session.close();
        } else {
            return region1;
        }
        return region;
    }

    @Override
    public Region update(Region region) {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(region);
        session.getTransaction().commit();
        session.close();
        return region;
    }

    @Override
    public void deleteById(Integer id) {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Region region = new Region();
        region.setId(id);
        session.delete(region);
        session.getTransaction().commit();
        session.close();
    }

    private Region findRegion(Region region) {
        session = HibernateUtil.getSession();
//        Query query = session.createQuery("from Region where name = :name");
//        query.setParameter("name", region.getName().toLowerCase());
        region = (Region) session.createQuery("from Region where name = :name")
                .setParameter("name", region.getName().toLowerCase())
                .uniqueResult();
        session.close();
        return region;
    }
}
