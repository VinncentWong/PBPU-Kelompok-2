package com.pbpu.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Setter
@Getter
public class Buku {

    private Integer id;

    private String bookName;

    private Integer price;

    private String authorName;

    private LocalDate publishedAt;
}
