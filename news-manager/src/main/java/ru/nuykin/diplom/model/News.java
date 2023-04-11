package ru.nuykin.diplom.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity(name="news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;
    private String embedding;
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "news_source_id", nullable = false)
    private NewsSource newsSource;

    @OneToMany(mappedBy="news", cascade=CascadeType.ALL)
    private List<ReadNews> readNewsList;

    public News(String title, String url, String embedding, Date date, NewsSource newsSource) {
        this.title = title;
        this.url = url;
        this.embedding = embedding;
        this.date = date;
        this.newsSource = newsSource;
        this.readNewsList = Collections.EMPTY_LIST;
    }
}
