package com.isa.usersengine;

import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        System.out.println("All stored names are:");

        new UsersRepositoryDaoBean().getUsersList().forEach(u -> System.out.println(u.getName()));

    }
}
