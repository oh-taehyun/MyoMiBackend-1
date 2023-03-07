package com.myomi.seller.control;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myomi.seller.dto.SellerAddRequestDto;
import com.myomi.seller.dto.SellerCheckRequestDto;
import com.myomi.seller.dto.SellerInfoResponseDto;
import com.myomi.seller.dto.SellerOrderDetailDto;
import com.myomi.seller.dto.SellerProductResponseDto;
import com.myomi.seller.dto.SellerReadResponseDto;
import com.myomi.seller.service.SellerService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RestController
@RequiredArgsConstructor
public class SellerController {
	private final SellerService sellerService;
	
	//판매자로 신청하기
	@PostMapping("mypage/partner")
	public ResponseEntity<SellerAddRequestDto> sellerAdd(@RequestBody SellerAddRequestDto sellerAddRequestDto,Authentication user){
		return sellerService.addSeller(sellerAddRequestDto,user);
	}
	
	//판매자 신청현황 조회하기
	@GetMapping("mypage/partner")
	public SellerReadResponseDto sellerJoinDetail(Authentication user) {
		return sellerService.getDetailBySellerJoin(user);
	}
	
	//판매자 사업자등록번호 검증
	@PostMapping("mypage/partner/check")
	public int sellerCheck(@RequestBody SellerCheckRequestDto sellerCheckDto) {
		return sellerService.getSellerCheck(sellerCheckDto);
	}
	
	//판매자 스토어 정보
	@GetMapping("store/info/{seller}")
	public SellerInfoResponseDto SellerStoreInfo(@PathVariable String seller) {
		return sellerService.getSellerStoreInfo(seller);
	}
	
	//판매자 주문현황 조회
	@GetMapping("sellerpage/orderlist")
	public List<SellerOrderDetailDto> sellerOrderList(Authentication user,@PageableDefault(size=5) Pageable pageable){
		return sellerService.getAllSellerOrderList(user,pageable);
	}
	
	//판매자 주문현황 상세조회
	@GetMapping("sellerpage/order/detail/{orderNum}/{prodNum}")
	public SellerOrderDetailDto sellerOrderDetail(@PathVariable Long orderNum,@PathVariable Long prodNum) {
		return sellerService.getSellerOrderDetail(orderNum,prodNum);
	}
	
	//판매자 info
	@GetMapping("sellerpage/info")
	public SellerInfoResponseDto SellerInfo(Authentication user){
		return sellerService.getSellerInfo(user);
	}
	
	//판매자 탈퇴
	@PutMapping("sellerpage/withdraw")
	public void sellerDelete(Authentication user){
		sellerService.removeSeller(user);
	}
	
	//판매자 상품 조회
	@GetMapping("sellerpage/productlist")
	public List<SellerProductResponseDto> sellerProductList(Authentication user,@PageableDefault(size=5) Pageable pageable){
		return sellerService.getAllSellerProductList(user,pageable);
	}
}
