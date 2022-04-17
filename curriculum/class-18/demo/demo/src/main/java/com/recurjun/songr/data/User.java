package com.recurjun.songr.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Album> album;

    @ManyToMany
    @JoinTable(
            name = "user_user",
            joinColumns = {@JoinColumn(name = "from_id")},
            inverseJoinColumns = {@JoinColumn(name = "to_id")}
    )
    public List<User> following;

    @ManyToMany(mappedBy = "following", fetch = FetchType.EAGER)
    public List<User> followers;

//    @OneToMany(mappedBy = "fromUser")
//    private List<Follow> following2;
//
//    @OneToMany(mappedBy = "toUser")
//    private List<Follow> folower2;

    public void setFollowers(User user) {
        this.followers.add(user);
    }
}
