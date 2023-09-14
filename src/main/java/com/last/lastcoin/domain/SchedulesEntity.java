package com.last.lastcoin.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "schedules")
@Data
@EqualsAndHashCode(callSuper = false)
public class SchedulesEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String image;
    private Integer status; // 0: 미노출, 1: 노출
    private Integer timeType; // 0: 날짜, 시간 표기, 1: 날짜만 표기
    private LocalDateTime eventTime;
    private LocalDateTime deletedTime;
}
