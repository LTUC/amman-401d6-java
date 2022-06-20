package com.example.apidemo;


import lombok.*;

import javax.persistence.*;

/**
 * <h3>api-demo</h3>
 *
 * @author jason
 * @description <p>blog</p>
 * @date 2022-06-20 10:17
 **/
@Entity
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Blog {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String content;
}
