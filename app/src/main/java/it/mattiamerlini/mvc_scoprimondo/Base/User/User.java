package it.mattiamerlini.mvc_scoprimondo.Base.User;

import java.util.Date;

import it.mattiamerlini.mvc_scoprimondo.Utilities.DataUtility;

/**
 * Created by mattia on 27/01/16.
 */
public class User
{
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthday;
    private String role;

    public User(int id, String name, String surname, String email, String password, Date birthday, String role)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public User(String name, String surname, String email, String password, Date birthday, String role)
    {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getRole() {
        return role;
    }

    public boolean missingData()
    {
        return (this.getName() == null) || (this.getSurname() == null) || (this.getEmail() == null) || (this.getPassword() == null) || (this.getBirthday() == null) || (this.getRole() == null);
    }

    public String toString()
    {
        return String.format("[Utente] -> [%d, %s, %s, %s, %s, %s, %s]", this.getId(), this.getName(), this.getSurname(), this.getEmail(), this.getPassword(), DataUtility.dateToString(this.getBirthday()), this.getRole());
    }
}
