package study.security.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Base {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate boardCreatedTime;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate boardUpdatedTime;
}
