package com.harshank.dealse.objects;

import java.io.Serializable;


/**
 * Created by harshank on 25/5/17.
 */

public class Users  implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private String usertype;
    private String userlogo;
    private String usercover;

    public String getUserlogo() {
        return userlogo;
    }

    public void setUserlogo(String userlogo) {
        this.userlogo = userlogo;
    }

    public String getUsercover() {
        return usercover;
    }

    public void setUsercover(String usercover) {
        this.usercover = usercover;
    }



    public Users(String id, String name, String email, String usertype) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.usertype = usertype;
    }
    public Users() {
    }
    public String getUsertype() {
        return usertype;

    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
