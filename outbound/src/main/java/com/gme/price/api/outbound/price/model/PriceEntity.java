package com.gme.price.api.outbound.price.model;

import com.gme.price.api.outbound.brand.model.BrandEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prices")
public class PriceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private BrandEntity brand;
	@Column
	private LocalDateTime startDate;
	@Column
	private LocalDateTime endDate;
	@Column
	private Long priceList;
	@Column
	private Long productId;
	@Column
	private Integer priority;
	@Column
	private Double price;
	@Column
	private String currency;
}
