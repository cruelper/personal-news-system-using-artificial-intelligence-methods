package ru.nuykin.diplom.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.nuykin.diplom.dto.NewsDto;
import ru.nuykin.diplom.model.News;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDto modelToDto(News news);
    News DtoToModel(NewsDto newsDto);
}
