package guru.springframework.spring6webapp.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    
    @Id
    private UUID id;
    private String customerName;

    @Version
    private String version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
