package com.infogain.api.testService;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.infogain.api.RestaurantApplication;
import com.infogain.api.entity.Coupon;
import com.infogain.api.repository.CouponRepository;
import com.infogain.api.service.CouponService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RestaurantApplication.class)
public class CouponServiceTest {

	@Mock
	private CouponRepository couponRepository;

	@InjectMocks
	private CouponService couponService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

	}
	

	private Coupon couponObject() throws ParseException {
		String releaseDate = "2018-10-29";
		String expiryDate = "2018-11-29";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date mockReleaseDate = (Date) formatter.parse(releaseDate);
		Date mockExpiryDate = (Date) formatter.parse(expiryDate);

		return new Coupon("FIRST100", 20.0f, 500.0f,1000, mockReleaseDate, mockExpiryDate, 10,
				"Maximum discount value is Rs.500");
	}

	private List<Coupon> couponList() throws ParseException {

		List<Coupon> couponList = new ArrayList<>();
		Coupon coupon = couponObject();
		couponList.add(coupon);
		return couponList;

	}

	@Test
	public void testGetCouponByCode() throws ParseException 
	{
		Mockito.when(couponRepository.findByCode(Mockito.anyString())).thenReturn(couponObject());
		Coupon coupon= couponService.getCouponByCode(Mockito.anyString());

		//System.out.println(coupon.getCode());
		assertEquals("FIRST100", coupon.getCode());

	}
	
	@Test
	public void testGetByBillAmount() throws ParseException 
	{
		Mockito.when(couponRepository.findByBillAmount(Mockito.anyInt())).thenReturn(couponList());
		
		List<Coupon> couponList = couponService.getByBillAmount(Mockito.anyInt());

		//System.out.println(coupon.getCode());
		assertEquals(1, couponList.size());

	}

	@Test
	public void testGetAllCoupon() throws ParseException {

		Mockito.when(couponRepository.findAll()).thenReturn(couponList());

		List<Coupon> couponList = couponService.getAllCoupon();

		assertEquals(1, couponList.size());

	}
	  @Test

      public void testAddCoupon() throws ParseException {                           

                      Mockito.when(couponRepository.save(Mockito.any())).thenReturn(couponObject());

                      Coupon coupon= couponService.addCoupon(Mockito.any());

                      assertEquals("FIRST100", coupon.getCode());                     

      }

     

      @Test

      public void testUpdateCoupon() throws ParseException {                          

          Mockito.when(couponRepository.save(Mockito.any())).thenReturn(couponObject());
          Coupon coupon= couponService.updateCoupon(Mockito.any());

          assertEquals("FIRST100", coupon.getCode());      
      }

     

      @Test

      public void testDeleteCoupon() throws ParseException {                          

  		Mockito.when(couponRepository.findByCode(Mockito.anyString())).thenReturn(couponObject());
          String msg= couponService.deleteCoupon(Mockito.any());

          assertEquals("Coupon deleted", msg);      
      }

}
