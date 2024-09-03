package com.example.fakestoreproductservice.service;

import com.example.fakestoreproductservice.dto.FakeStoreProductDto;
import com.example.fakestoreproductservice.exceptions.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreApiClient {

    RestTemplate restTemplate;
    String baseUrl = "https://fakestoreapi.com/products";

    public FakeStoreApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FakeStoreProductDto> getAllProducts(Map<String, String> queryParams) {

        MultiValueMap<String, String> multiValuequeryParams = new LinkedMultiValueMap<>();
        for(Map.Entry<String, String> entry : queryParams.entrySet()) {
            multiValuequeryParams.add(entry.getKey(), entry.getValue());
        }
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(multiValuequeryParams).build();

        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(builder.toUriString(), FakeStoreProductDto[].class);

        return Arrays.stream(responseEntity.getBody()).toList();
    }

    public FakeStoreProductDto getProductById(Long productId) throws NotFoundException {

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(baseUrl+"/{id}", FakeStoreProductDto.class, productId);

        if(response.getBody() == null)
        {
            throw new NotFoundException( "Product with product id: "+productId + " is not present.");
        }
        return response.getBody();
    }

    public FakeStoreProductDto createProduct(FakeStoreProductDto product) {
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(baseUrl,product, FakeStoreProductDto.class);
        return response.getBody();
    }

    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto product) {
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.patchForObject(baseUrl+"/{id}", product, FakeStoreProductDto.class, productId);

        return fakeStoreProductDto;
    }

    /*How to pass both path parameter and query parameter*/

    /*
    * String uri = http://my-rest-url.org/rest/account/{account};

    Map<String, String> uriParam = new HashMap<>();
    uriParam.put("account", "my_account");

    UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("pageSize","2")
                        .queryParam("page","0")
                        .queryParam("name","my_name").build();

    String url = builder.toUriString() //This will apply query parameters to the url

    HttpEntity<String> requestEntity = new HttpEntity<>(null, getHeaders());

    ResponseEntity<String> strResponse = restTemplate.exchange(url,HttpMethod.GET, requestEntity,
                        String.class,uriParam);

    //final URL: http://my-rest-url.org/rest/account/my_account?pageSize=2&page=0&name=my_name
    * */


}
