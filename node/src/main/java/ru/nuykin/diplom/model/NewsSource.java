package ru.nuykin.diplom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="news_source")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsSource {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;

    @OneToMany(mappedBy="newsSource", cascade=CascadeType.ALL)
    private List<News> newsList;

    @ManyToMany(mappedBy="newsSources", cascade=CascadeType.ALL)
    private List<User> userList;
}
