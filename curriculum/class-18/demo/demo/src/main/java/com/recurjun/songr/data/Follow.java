package com.recurjun.songr.data;

import javax.persistence.*;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private  User fromUser;

    @ManyToOne
    private  User toUser;
}
