package com.infogain.api.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.infogain.api.controller.OrderController;

import com.infogain.api.entity.OrderPlaced;

import com.infogain.api.service.OrderServiceImpl;

@RunWith(SpringRunner.class)
@ContextConfiguration
@SpringBootTest(classes=OrderController.class)
public class OrderControllerTest {
private MockMvc mvc;
@Autowired
private WebApplicationContext ctx;

@MockBean
private OrderServiceImpl osi;
List<OrderPlaced> orderPlaced=new ArrayList<>();
OrderPlaced orderPlace;
@Before
public void setUp() throws Exception{
this.mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
orderPlace=new OrderPlaced();
orderPlace.setId(1);
orderPlace.setOrderId(123450);
orderPlace.setCategory("snacks");
orderPlace.setDescription("nothing new");
orderPlace.setItemId(1);
orderPlace.setOrderStatus("Arriving");
orderPlace.setQuantity(1);
orderPlace.setRate(100);
orderPlace.setTotalPrice(100);
orderPlace.setUsername("vishal");
orderPlaced.add(orderPlace);
}
@Test
public void testGetAllOrder()throws Exception
{
Mockito.when(osi.getAllOrder()).thenReturn(orderPlaced);
RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/poc/order")
.contentType(MediaType.APPLICATION_JSON_UTF8);
MvcResult result =  mvc.perform(requestBuilder).andReturn();
String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":[{\"id\":1,\"orderId\":123450,\"orderStatus\":\"Arriving\",\"username\":\"vishal\",\"itemId\":1,\"itemName\":null,\"rate\":100,\"category\":\"snacks\",\"quantity\":1,\"description\":\"nothing new\",\"totalPrice\":100}]}";
assertEquals(expected, result.getResponse().getContentAsString());
assertNotNull(expected,result.getResponse().getContentAsString());
}
/*@Test
public void testAddOrderItem()throws Exception
{
Mockito.when(osi.addOrder(Mockito.any())).thenReturn(orderPlaced);
RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/poc/order")
.contentType(MediaType.APPLICATION_JSON_UTF8)
.content("{\"username\":\"Vishal\"}");
MvcResult result = mvc.perform(requestBuilder).andReturn();
String expected="{\"code\":\"200\",\"message\":\"Following Data Found\",\"response\":[{\"id\":1,\"orderId\":123450,\"orderStatus\":\"Arriving\",\"username\":\"vishal\",\"itemId\":1,\"itemName\":null,\"rate\":100,\"category\":\"snacks\",\"quantity\":1,\"description\":\"nothing new\",\"totalPrice\":100}]}";
assertEquals(expected, result.getResponse().getContentAsString());
}*/
@Test
public void testUpdateOrderItem()throws Exception
{
Mockito.when(osi.updateOrder(Mockito.any())).thenReturn(orderPlace);
RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/poc/order")
.contentType(MediaType.APPLICATION_JSON_UTF8);
/*.content("{\"Id\":1,\\\"orderId\\\":123450,\\\"orderStatus\\\":\\\"Arriving\\\",\\\"username\\\":\\\"vishal\\\",\\\"itemId\\\":1,\\\"itemName\\\":null,\\\"rate\\\":100,\\\"category\\\":\\\"snacks\\\",\\\"quantity\\\":1,\\\"description\\\":\\\"nothing new\\\",\\\"totalPrice\\\":100}");*/
/*.content("{.content(\"{\"id\":1");*/
mvc.perform(requestBuilder).andExpect(status().isBadRequest());
}

}
