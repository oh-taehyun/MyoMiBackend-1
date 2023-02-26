package com.myomi.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.myomi.product.entity.Product;
import com.myomi.seller.entity.Seller;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @JsonAutoDetect
public class ProductSaveDto {
	
	@NotBlank
	private String category;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private Long originPrice;
	
	private int percentage;
	
	@NotBlank
	private int week;
	
	@Size(max = 60, message = "상품 특이사항을 입력해주세요.")
	private String detail;
	
	//상품 등록시 사용
	public Product toEntity(ProductSaveDto productSaveDto
			, Seller seller
			) {
		return Product.builder()
				.seller(seller)
				.category(category)
				.name(name)
				.originPrice(originPrice)
				.percentage(percentage)
				.week(week)
				.detail(detail)
				.fee(9) //기본값 9로 넣어주려고 셋팅
				.build();
	}
}