package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager em;

    public UserDaoImpl(@Autowired EntityManager em) {
        this.em = em;
    }

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        em.getTransaction().begin();
        if (em.find(User.class, user.getId()) != null)
            em.merge(user);
        em.getTransaction().commit();
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        em.getTransaction().begin();
        Query q = em.createQuery("delete from User where id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("from User").getResultList();
    }
}
