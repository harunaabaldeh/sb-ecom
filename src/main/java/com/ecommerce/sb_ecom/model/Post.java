package com.ecommerce.sb_ecom.model;

import jakarta.persistence.ManyToOne;

public class Post {
    private Long id;

    @ManyToOne
    private User user;
}
