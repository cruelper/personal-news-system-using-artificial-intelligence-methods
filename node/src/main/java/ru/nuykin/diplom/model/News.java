package ru.nuykin.diplom.model;

import jakarta.persistence.*;
import lombok.*;

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
}