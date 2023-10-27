package com.fog1ess.OrderApplication.dao;

import com.fog1ess.OrderApplication.entity.MenuItem;
import com.fog1ess.OrderApplication.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants(){
        try(Session session = sessionFactory.openSession()) {
            return session.createCriteria(Restaurant.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)//de-duplicate
                    .list();
        } catch(Exception exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        //primary key -> restaurant -> menu list
        try(Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if(restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
