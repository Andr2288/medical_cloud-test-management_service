package com.intern_project.test_management_service.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private Long testId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "test_type_id")
    private TestType testType;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<TestRequest> testRequests;
}