package guru.springframework.spring6webapp.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import guru.springframework.spring6webapp.model.BeerStyle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Beer {
       
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;   
    
    @NotNull
    @NotBlank
    private String beerName;
    
    @NotNull
    private BeerStyle beerStyle;
    
    @NotNull
    @NotBlank
    private String upc;
    private Integer quantityOnHand;
    
    @NotNull
    private BigDecimal price;

    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
