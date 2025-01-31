package com.takameyer.TodoListBackend.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.takameyer.TodoListBackend.domain.model.Item;
import com.takameyer.TodoListBackend.service.ItemService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTests {

	@LocalServerPort
	private Integer port;

	@Autowired
	private ItemService itemService;

	static MongoDBContainer mongodb = new MongoDBContainer("mongo:7.0");


	@BeforeAll
	static void beforeAll() {
		mongodb.start();
	}

	@AfterAll
	static void afterAll() {
		mongodb.close();
	}

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		// ReplicaSetUrl automatically gets a database set
		registry.add("mongodb.uri", mongodb::getReplicaSetUrl);
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		itemService.deleteAll();
	}

	@Test void shouldGetAllItems() throws JsonProcessingException {
		List<Item> items = List.of(
				new Item("1", "Item 1", false, "1"),
				new Item("2", "Item 2", true, "2")
		);
		itemService.saveAll(items);

		String json =  given()
				.contentType(ContentType.JSON)
				.when()
				.get("/api/items")
				.then()
				.statusCode(200)
				.body(".", hasSize(2))
				.extract().asString();

		Gson gson = new GsonBuilder().create();
		System.out.print(gson.toJson(json));
		ObjectMapper mapper = new ObjectMapper();
		Object jsonObject = mapper.readValue(json, Object.class);
		String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		System.out.println(prettyJson);

	}

}
