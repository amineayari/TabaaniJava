/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava.entities;

import java.sql.Date;

/**
 *
 * @author Abirn
 */
public class Admin {

    private int id;
    private String adminname;
    private String lastname;
    private String gender;
    private int tel;
    private String email;
    private String password;
    private Date birthday;
    private String image;

    public Admin() {
    }

    public Admin(String adminname, String lastname, String gender, int tel, String email, String password, Date birthday, String image) {
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.image = image;
    }

    public Admin(int id, String adminname, String lastname, String gender, int tel, String email, Date birthday, String image) {
        this.id = id;
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.birthday = birthday;
        this.image = image;
    }

    public Admin(int id, String adminname, String lastname, String gender, int tel, String email, String password, Date birthday, String image) {
        this.id = id;
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.image = image;
    }

    public Admin(int id, String adminname, String lastname, String gender, int tel, String email, String password, Date birthday) {
        this.id = id;
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public Admin(String adminname, String lastname, String gender, int tel, String email, String password, Date birthday) {
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }

    public Admin(String adminname, String lastname, String gender, int tel, String email, Date birthday, String image) {
        this.adminname = adminname;
        this.lastname = lastname;
        this.gender = gender;
        this.tel = tel;
        this.email = email;
        this.birthday = birthday;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", adminname=" + adminname + ", lastname=" + lastname + ", gender=" + gender + ", tel=" + tel + ", email=" + email + ", password=" + password + ", birthday=" + birthday + ", image=" + image + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
