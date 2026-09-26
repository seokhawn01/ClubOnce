package com.example.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(
        name = "member",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_member_email",
                columnNames = "email"
        )
)
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Boolean feeRequired;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, name = "status")
    private MemberStatus memberStatus;

    public Member(
            String name,
            String email,
            boolean feeRequired,
            MemberRole role,
            MemberStatus status
    ) {
        this.name = name;
        this.email = email;
        this.createdAt = Instant.now();
        this.feeRequired = feeRequired;
        this.role = role;
        this.memberStatus = memberStatus;
    }
}
