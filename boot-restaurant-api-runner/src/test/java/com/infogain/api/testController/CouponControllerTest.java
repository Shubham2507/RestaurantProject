package com.infogain.api.testController;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.infogain.api.controller.CouponController;
import com.infogain.api.entity.Coupon;
import com.infogain.api.service.ICouponService;

@SpringBootTest(classes = CouponController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CouponControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	@MockBean
	private ICouponService couponService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();

	}

	private String couponObjectJson = "{\"code\":\"FIRST100\",\"discountPercentage\":20,\"maximumDiscount\":500,\"billAmount\":1000,\"releaseDate\":\"2018-10-24\",\"expiryDate\":\"2018-11-25\",\"quantity\":10,\"termsAndConditions\":\"Maximum discount value is Rs.500\"}";

	private String updateCouponObjectJson = "{\"code\":\"FIRST100\",\"discountPercentage\":20,\"maximumDiscount\":200,\"billAmount\":1000,\"releaseDate\":\"2018-10-24\",\"expiryDate\":\"2018-11-25\",\"quantity\":10,\"termsAndConditions\":\"Maximum discount value is Rs.200\"}";

	private Coupon couponObject() throws ParseException {
		String releaseDate = "2018-10-29";
		String expiryDate = "2018-11-29";
		DateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
		Date mockReleaseDate = (Date) formatter.parse(releaseDate);
		Date mockExpiryDate = (Date) formatter.parse(expiryDate);

		return new Coupon("FIRST100", 20.0f, 500.0f, 1000, mockReleaseDate, mockExpiryDate, 10,
				"Maximum discount value is Rs.500");
	}

	private List<Coupon> couponList() throws ParseException {

		List<Coupon> couponList = new ArrayList<>();
		Coupon coupon = couponObject();
		couponList.add(coupon);
		return couponList;

	}

	
	@Test
	public void testGetAllCoupon() throws Exception {
		Mockito.when(couponService.getAllCoupon()).thenReturn(couponList());

		mockMvc.perform(MockMvcRequestBuilders.get("/coupon")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

}
	
	@Test
	public void testGetAllCouponNullList() throws Exception {
		Mockito.when(couponService.getAllCoupon()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/coupon")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	@Test
	public void testGetAllCouponEmptyList() throws Exception {
		Mockito.when(couponService.getAllCoupon()).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/coupon")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	@Test
	public void testGetByBillAmountNullList() throws Exception {
		Mockito.when(couponService.getByBillAmount(Mockito.anyInt())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/coupon/filter/"+1000)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}

	
	@Test
	public void testGetByBillAmountEmptyList() throws Exception {
		Mockito.when(couponService.getByBillAmount(Mockito.anyInt())).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/coupon/filter/"+1000)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());

	}
	
	
	@Test
	public void testGetByBillAmountList() throws Exception {
		Mockito.when(couponService.getByBillAmount(Mockito.anyInt())).thenReturn(couponList());
		mockMvc.perform(MockMvcRequestBuilders.get("/coupon/filter/"+1000)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());

	}
	

	
	@Test
	public void testGetCouponByCode() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(couponObject());

		mockMvc.perform(get("/coupon/" + couponObject().getCode())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("response.code", is(couponObject().getCode())))
				.andDo(print());
	}
	
	@Test
	public void testGetNullCouponByCode() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(get("/coupon/" + couponObject().getCode())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andDo(print());
	}

	@Test // adding coupon assuming coupon doesn't exist already
	public void testAddCoupon() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(post("/coupon")
				.contentType(MediaType.APPLICATION_JSON)
				.content(couponObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test // adding coupon assuming coupon exist already
	public void testAddCouponExisting() throws Exception {
		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(couponObject());

		mockMvc.perform(post("/coupon").contentType(MediaType.APPLICATION_JSON).content(couponObjectJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isConflict()).andDo(print());
	}

	@Test // updating coupon assuming coupon being updated already exist and we are
			// returning the existing coupon by couponservice's getCouponByCode method
	public void testUpdateCoupon() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(couponObject());

		mockMvc.perform(put("/coupon")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updateCouponObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.response.code").exists())
				.andDo(print());

	}

	@Test // updating coupon assuming coupon being updated doesn't exist and we are
			// returning the existing coupon by couponservice's getCouponByCode method
	public void testUpdateCouponNull() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(put("/coupon").contentType(MediaType.APPLICATION_JSON).content(updateCouponObjectJson)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andDo(print());

	}

	@Test// deleting a coupon with valid coupon code
	public void testDeleteCouponByCode() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(couponObject());

		mockMvc.perform(delete("/coupon/" + couponObject().getCode())
				.contentType(MediaType.APPLICATION_JSON)
				// .content(couponObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				//.andExpect(status().isGone())
				//.andExpect(jsonPath("code", is(couponObject().getCode())))
				.andDo(print());

	}

	
	@Test// deleting a coupon with invalid coupon code
	public void testDeleteInvalidCouponByCode() throws Exception {

		Mockito.when(couponService.getCouponByCode(Mockito.anyString())).thenReturn(null);

		mockMvc.perform(delete("/coupon/" + couponObject().getCode())
				.contentType(MediaType.APPLICATION_JSON)
				// .content(couponObjectJson)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				//.andExpect(jsonPath("code", is(couponObject().getCode())))
				.andDo(print());

	}

	
	/*
	 * private String mapToJson(Object object) throws JsonProcessingException {
	 * ObjectMapper objectMapper = new ObjectMapper(); return
	 * objectMapper.writeValueAsString(object); }
	 */
}
