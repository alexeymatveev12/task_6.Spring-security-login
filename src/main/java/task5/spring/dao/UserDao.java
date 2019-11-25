package task5.spring.dao;

import task5.spring.model.User;


import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    //1-й получить список всех пользователей
    List<User> getAllUsersDao();

    //2-й получить пользователя по ID
    User getUserById(long id) /*throws SQLException*/;

    //3-й проверить есть ли зарегистрированный пользователь с искомым именем
    boolean checkUserByName(String name) throws SQLException;

    //4-й проверить есть ли зарегистрированный пользователь с искомым логином
    boolean checkUserByLogin(String username) throws SQLException;

    //5-й создать и добавить в базу нового пользователя
    void addUser(User user);

    //6-й обновить и записать в базу новые данные пользователя
    void updateUser(User user);

    //7-й удалить пользователя через ID
    void deleteUserByIdDao(long id) /* throws SQLException*/;

    //8-й проверить есть ли зарегистрированный пользователь
    // с искомым login и password
    public User isExist(String username, String password);


    //9-й проверка по логину  - Секьюрити
    User findByUsername(String username);

    //10-й сохранение?????  - Секьюрити
    void save(User user);
}