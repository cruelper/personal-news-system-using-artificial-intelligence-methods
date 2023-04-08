package ru.nuykin.diplom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="userr")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String keycloakEmail;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_news_source",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "news_source_id"))
    private List<NewsSource> newsSources;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<ReadNews> readNewsList;
}