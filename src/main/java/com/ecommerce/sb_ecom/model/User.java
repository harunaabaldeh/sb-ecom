package com.ecommerce.sb_ecom.model;

import java.util.ArrayList;

import jakarta.persistence.OneToMany;

public class User {
    private Long id;

    @OneToMany(mappedBy = "user")
    private ArrayList<Post> userPost = new ArrayList<Post>();
}
