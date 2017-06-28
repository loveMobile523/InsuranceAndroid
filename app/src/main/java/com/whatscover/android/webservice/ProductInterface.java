package com.whatscover.android.webservice;

import com.whatscover.android.webservice.dto.DTOApplyProductResponse;
import com.whatscover.android.webservice.dto.DTOProduct;
import com.whatscover.android.webservice.dto.DTOProductSuggestion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dorin on 15.05.2017.
 */

public interface ProductInterface {

    /**
     * Search Function
     * @param query ??500
     * @return List<DTOProduct>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/products")
    Call<List<DTOProduct>> searchProducts(@Query("page") int page, @Query("size") int size,
                                           @Query("query") String query, @Query("sort") String[] sort);

    /**
     * Get All
     * @return List<DTOProduct>
     */
    @Headers("Content-Type:application/json")
    @GET("api/products")
    Call<List<DTOProduct>> getProducts(@Query("page") int page, @Query("size") int size,
                                        @Query("sort") String[] sort);

    /**
     * Get With ID
     * @param id Product id
     * @return DTOProduct
     */
    @Headers("Content-Type:application/json")
    @GET("api/products/{id}")
    Call<DTOProduct> getProductWithID(@Path("id") long id);

    // -----------------------------------------

    /**
     * Get Product Suggestions
     * @param categoryId category id
     * @return List<DTOProduct>
     */
    @Headers("Content-Type:application/json")
    @GET("api/product/suggestion/category/{categoryId}")
    Call<List<DTOProductSuggestion>> getProductSuggestions(@Path("categoryId") long categoryId);

    /**
     * Get All Product By Category
     * @param page page
     * @param size size
     * @param id ca
     * @param sort sort
     * @return List<DTOProduct>
     */
    @Headers("Content-Type:application/json")
    @GET("api/_search/products/categories/{id}")
    Call<List<DTOProduct>> getAllProductsByCategory(@Query("page") int page, @Query("size") int size,
                                          @Path("id") long id, @Query("sort") String[] sort);

    // ---------------------------------------------------------------------------------------------
    /**
     * compare two products
     * @param productIdOne productIdOne
     * @param productIdTwo productIdTwo
     * @return List<DTOProduct>
     */
    @Headers("Content-Type:application/json")
    @GET("api/product/compare")
    Call<List<DTOProduct>> compareTwoProducts(@Query("product-id-one") long productIdOne, @Query("product-id-two") long productIdTwo);

    // ---------------------------------------------------------------------------------------------

    /**
     * apply product
     * @param productId productId
     * @return DTOApplyProductResponse
     */
    @Headers("Content-Type:application/json")
    @GET("api/product/apply/{productId}")
    Call<DTOApplyProductResponse> applyProduct(@Path("productId") long productId);
}
