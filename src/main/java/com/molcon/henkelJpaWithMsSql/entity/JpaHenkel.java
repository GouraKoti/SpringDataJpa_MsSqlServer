package com.molcon.henkelJpaWithMsSql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="henkel")
public class JpaHenkel {

    @Id
    private String mcid;
    private String commonName;
    private String description;
    private String sapCs;
}
