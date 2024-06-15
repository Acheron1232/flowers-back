package com.acheron.flowers.store.entity;

import com.acheron.flowers.security.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ShopComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author",nullable = false)
    private User author;
    @DateTimeFormat(style = "yyyy-MM-dd-HH-mm")
    @Column(nullable = false)
    private LocalDateTime date;
    @Column(nullable = false)
    private String text;
}
