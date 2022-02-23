package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        if (getUser(user.getId()) != null)
            em.merge(user);
    }

    @Override
    public User getUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        em.remove(getUser(id));
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("from User").getResultList();
    }
}
