package com.whatscover.android.activity;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.whatscover.android.R;
import com.whatscover.android.constant.Constant;
import com.whatscover.android.pojo.MultipleResource;
import com.whatscover.android.pojo.UserList;
import com.whatscover.android.utility.A;
import com.whatscover.android.utility.T;
import com.whatscover.android.webservice.ApplicationInterface;
import com.whatscover.android.webservice.CategoryInterface;
import com.whatscover.android.webservice.CategoryQParameterInterface;
import com.whatscover.android.webservice.CompanyInterface;
import com.whatscover.android.webservice.ProductInterface;
import com.whatscover.android.webservice.ProductQParameterInterface;
import com.whatscover.android.webservice.QuestionParameterInterface;
import com.whatscover.android.webservice.SuggestionInterface;
import com.whatscover.android.webservice.UserInterface;
import com.whatscover.android.webservice.UserQParameterInterface;
import com.whatscover.android.webservice.authentication.ServiceGenerator;
import com.whatscover.android.webservice.dto.AddSuggestionResponse;
import com.whatscover.android.webservice.dto.DTOApplication;
import com.whatscover.android.webservice.dto.DTOCategory;
import com.whatscover.android.webservice.dto.DTOCategoryList;
import com.whatscover.android.webservice.dto.DTOCategoryQParameter;
import com.whatscover.android.webservice.dto.DTOCompany;
import com.whatscover.android.webservice.dto.DTOGetUserQParameterRes;
import com.whatscover.android.webservice.dto.DTOProductQParameter;
import com.whatscover.android.webservice.dto.DTOProductSuggestion;
import com.whatscover.android.webservice.dto.DTOQuestionParameter;
import com.whatscover.android.webservice.dto.DTOUserQParameter;
import com.whatscover.android.webservice.dto.DTOUserQParameterNoId;
import com.whatscover.android.webservice.dto.DTOUserQParameterNoIdList;
import com.whatscover.android.webservice.dto.User;
import com.whatscover.android.webservice.dto.UserTest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btnAddPolicy) Button btnAddPolicy;

    @BindString(R.string.first_name_title)
    String firstName;

    private UserTest currentUser;

    private UserInterface userInterface;
    private ProductInterface productInterface;
    private CategoryInterface categoryInterface;
    private CategoryQParameterInterface categoryQParameterInterface;
    private QuestionParameterInterface questionParameterInterface;
    private UserQParameterInterface userQParameterInterface;
    private ProductQParameterInterface productQParameterInterface;
    private CompanyInterface companyInterface;
    private ApplicationInterface applicationInterface;

    // ---------------------------------------------------------------------------------------------
//    private final int MAX_NUMBER_QUESTION = 5;
    private int currentQuestionNum;
    List<DTOUserQParameterNoId> dtoUserQParameterNoIds;

    private TextView tvQuestion;
    private EditText etQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);

        btnAddPolicy.setPaintFlags(btnAddPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        currentUser = userManager.read();

        initialize();
        getUser();

//        callWebApi();
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);

        callSuggestionInterface();


    }

    private void getUser(){
        Call<User> call = userInterface.getUser(currentUser.getName());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!isError(response.code()) || response.isSuccessful()) {
                    User user = response.body();
                    currentUser.setId(String.valueOf(user.getId()));

                    userManager.setUserId(currentUser.getId());
                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    // ---------------------------------------------------------------------------------------------
    //
    public void initialize() {
        currentQuestionNum = 0;

        userInterface = ServiceGenerator.createService(UserInterface.class, currentUser.getToken(), true);
        productInterface = ServiceGenerator.createService(ProductInterface.class, currentUser.getToken(), true);
        categoryInterface = ServiceGenerator.createService(CategoryInterface.class, currentUser.getToken(), true);
        categoryQParameterInterface = ServiceGenerator.createService(CategoryQParameterInterface.class, currentUser.getToken(), true);
        questionParameterInterface = ServiceGenerator.createService(QuestionParameterInterface.class, currentUser.getToken(), true);
        userQParameterInterface = ServiceGenerator.createService(UserQParameterInterface.class, currentUser.getToken(), true);
        productQParameterInterface = ServiceGenerator.createService(ProductQParameterInterface.class, currentUser.getToken(), true);
        companyInterface = ServiceGenerator.createService(CompanyInterface.class, currentUser.getToken(), true);
        applicationInterface = ServiceGenerator.createService(ApplicationInterface.class, currentUser.getToken(), true);
    }

    // ---------------------------------------------------------------------------------------------
    // web api
    public void callSuggestionInterface() {

        UserTest userTest = userManager.read();

        SuggestionInterface suggestionInterface =
                ServiceGenerator.createService(SuggestionInterface.class, userTest.getToken(), true);

        Call<AddSuggestionResponse> call = suggestionInterface.addSuggestion();
        call.enqueue(new Callback<AddSuggestionResponse>() {
            @Override
            public void onResponse(Call<AddSuggestionResponse> call, Response<AddSuggestionResponse> response) {

                if( !isError(response.code()) || response.isSuccessful()) {

                    AddSuggestionResponse addSuggestionResponse = response.body();
                    String firstName = addSuggestionResponse.getUser().getFirstName();
                    T.show(context, MainActivity.this.firstName + ": " + firstName );

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }

            }

            @Override
            public void onFailure(Call<AddSuggestionResponse> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });

//        callWebApi();
    }

    // ---------------------------------------------------------------------------------------------
    // web api
    public void callWebApi() {
        btnAddPolicy.setPaintFlags(btnAddPolicy.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        /**
         GET List Resources
         **/
        Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {


                Log.d("TAG", response.code() + "");

                String displayResponse = "";

                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + " Page\n" + total + " Total\n" + totalPages + " Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + " " + datum.name + " " + datum.pantoneValue + " " + datum.year + "\n";
                }

//                setText(displayResponse);
            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
                call.cancel();
            }
        });

        /**
         Create new user
         **/
        UserTest user = new UserTest("morpheus", "leader");
        Call<UserTest> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<UserTest>() {
            @Override
            public void onResponse(Call<UserTest> call, Response<UserTest> response) {

            }

            @Override
            public void onFailure(Call<UserTest> call, Throwable t) {
                call.cancel();
            }
        });

        /**
         GET List Users
         **/
        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });


        /**
         POST name and job Url encoded.
         **/
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    @OnClick(R.id.btnAddPolicy)
    public void onClickAddPolicy(Button btnAddPolicy) {
        goToOtherActivity(AddNewPolicyActivity.class, null, false);
    }

    private Callback<List<DTOCategory>> categoriesDTOCallback = new Callback<List<DTOCategory>>(){
        @Override
        public void onResponse(Call<List<DTOCategory>> call, Response<List<DTOCategory>> response) {

            if( !isError(response.code()) || response.isSuccessful()) {

                DTOCategoryList dtoCategoryList = (DTOCategoryList) response.body();

                A.goOtherActivity(context, HealthInsuranceActivity.class);

            }else{
                T.showSuccess(context, parseError( response.code()));
            }
        }

        @Override
        public void onFailure(Call<List<DTOCategory>> call, Throwable t) {
            T.showError(context, t.getMessage());
        }
    };

    private void showUserQParameters(final List<DTOQuestionParameter> dtoQuestionParameters) {
        currentQuestionNum=0;
        final Dialog dialog =  new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup_user_qparameters);
        dialog.setCancelable(true);

        // -----------------------------------------------------------------------------------------
        // show questions
        final Button btnSubmit = (Button) dialog.findViewById(R.id.btnSubmit);
        btnSubmit.setPaintFlags(btnSubmit.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvQuestion = (TextView) dialog.findViewById(R.id.tvQuestion1);
        etQuestion = (EditText) dialog.findViewById(R.id.etQuestion1);

        dtoUserQParameterNoIds = new ArrayList<DTOUserQParameterNoId>();

        final int len = dtoQuestionParameters.size();
        final int userId = Integer.parseInt(currentUser.getId());
        final String userLogin = currentUser.getName();

        tvQuestion.setText(dtoQuestionParameters.get(currentQuestionNum).getQuestionString());
        validateEditText(dtoQuestionParameters);
        if(len == 1)
            btnSubmit.setText(getString(R.string.submit));


        // -----------------------------------------------------------------------------------------
        // images

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mAwesomeValidation.validate()) {
                    DTOUserQParameterNoId dtoUserQParameterNoId = new DTOUserQParameterNoId();

                    // todo validation check
                    dtoUserQParameterNoId.setUserId(userId);
                    dtoUserQParameterNoId.setUserLogin(userLogin);
                    dtoUserQParameterNoId.setQuestionParameterId(dtoQuestionParameters.get(currentQuestionNum).getId());
                    dtoUserQParameterNoId.setValue(etQuestion.getText().toString());

                    dtoUserQParameterNoIds.add(dtoUserQParameterNoId);

                    if(currentQuestionNum == len - 1) {
                        dialog.dismiss();


                        DTOUserQParameterNoIdList dtoUserQParameterNoIdList = new DTOUserQParameterNoIdList(dtoUserQParameterNoIds);
                        saveBulkUserQuestionParameterValue(dtoUserQParameterNoIdList);
                    } else {
                        currentQuestionNum++;
                        tvQuestion.setText(dtoQuestionParameters.get(currentQuestionNum).getQuestionString());
                        etQuestion.setText("");
                        initmAwesomeValidation();
                        validateEditText(dtoQuestionParameters);
                        if( currentQuestionNum == len - 1 ) {
                            btnSubmit.setText(getString(R.string.submit));
                        } else {
                            btnSubmit.setText(getString(R.string.next));
                        }
                    }
                }
            }
        });

        dialog.show();
    }

    private void validateEditText(List<DTOQuestionParameter> dtoQuestionParameters){
        if(dtoQuestionParameters.get(currentQuestionNum).getType().equalsIgnoreCase(Constant.NUMERIC))
            mAwesomeValidation.addNumberValidation(etQuestion);
        else if(dtoQuestionParameters.get(currentQuestionNum).getType().equalsIgnoreCase(Constant.DATE_TO_AGE))
            mAwesomeValidation.addDateValidation(etQuestion);
    }

    public void saveBulkUserQuestionParameterValue(DTOUserQParameterNoIdList dtoUserQParameterNoIdList) {
        Call<List<DTOUserQParameter>> call = userQParameterInterface.saveBulkUserQuestionParameterValue(dtoUserQParameterNoIdList);
        call.enqueue(new Callback<List<DTOUserQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOUserQParameter>> call, Response<List<DTOUserQParameter>> response) {
                if(!isError(response.code()) || response.isSuccessful() ) {
                    List<DTOUserQParameter> dtoUserQParameters = response.body();
                    // todo go to step 3

                    getProductSuggestions(1);
                } else {
                    T.showError(context, parseError(response.code()));
                    System.out.println("Error step 2");
                }
            }

            @Override
            public void onFailure(Call<List<DTOUserQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
                System.out.println("Error stepp 2");
            }
        });
    }

    public void getProductSuggestions(long categoryID) {
        Call<List<DTOProductSuggestion>> call = productInterface.getProductSuggestions(categoryID);
        call.enqueue(new Callback<List<DTOProductSuggestion>>() {
            @Override
            public void onResponse(Call<List<DTOProductSuggestion>> call, Response<List<DTOProductSuggestion>> response) {
                if(isError(response.code()) || response.isSuccessful()) {
                    List<DTOProductSuggestion> dtoProductSuggestions = response.body();

                    T.showSuccess(context, dtoProductSuggestions.size());


                    if(dtoProductSuggestions!=null){

                        goToOtherActivity(HealthInsuranceActivity.class, dtoProductSuggestions, false);

                    }else T.showError(context, getString(R.string.error_suggestion_empty));

                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOProductSuggestion>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    @OnClick(R.id.liLayCategory)
    public void categoryClick() {
//        A.goOtherActivity(context, HealthInsuranceActivity.class);

        // todo go to step 1
        getUserQuestionParametes(1);
//        getProductSuggestions(1);

    }

    public void getUserQuestionParametes(long categoryId) {
        Call<DTOGetUserQParameterRes> call = categoryQParameterInterface.getUserQuestionParametes(categoryId);
        call.enqueue(new Callback<DTOGetUserQParameterRes>() {
            @Override
            public void onResponse(Call<DTOGetUserQParameterRes> call, Response<DTOGetUserQParameterRes> response) {
                if(!isError(response.code()) || response.isSuccessful()) {
                    DTOGetUserQParameterRes dtoGetUserQParameterRes = response.body();

                    List<DTOQuestionParameter> dtoQuestionParameters = response.body().getQuestionParameterList();

                    if(dtoQuestionParameters.size() > 0) {
                        // todo go to step 2
                        showUserQParameters(dtoQuestionParameters);
                    } else {
                        System.out.println("Error get question");
                        getProductSuggestions(1);
                        // todo go to step 3
                    }

                } else {
                    System.out.println("Error gett question");
                    //T.showError(context, parseError(response.code()));
                    getProductSuggestions(1);
                }
            }

            @Override
            public void onFailure(Call<DTOGetUserQParameterRes> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getApplicationsWithID(long id) {
        Call<DTOApplication> call = applicationInterface.getApplicationsWithID(id);
        call.enqueue(new Callback<DTOApplication>() {
            @Override
            public void onResponse(Call<DTOApplication> call, Response<DTOApplication> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    DTOApplication dtoApplication = response.body();


                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOApplication> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getAllApplications(int page, int size, String[] sort) {
        Call<List<DTOApplication>> call = applicationInterface.getAllApplications(page, size, sort);
        call.enqueue(new Callback<List<DTOApplication>>() {
            @Override
            public void onResponse(Call<List<DTOApplication>> call, Response<List<DTOApplication>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    List<DTOApplication> dtoApplications = response.body();


                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOApplication>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });

    }

    public void searchApplications(int page, int size, String query, String[] sort) {
        Call<List<DTOApplication>> call = applicationInterface.searchApplications(page, size, query, sort);
        call.enqueue(new Callback<List<DTOApplication>>() {
            @Override
            public void onResponse(Call<List<DTOApplication>> call, Response<List<DTOApplication>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    List<DTOApplication> dtoApplications = response.body();


                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOApplication>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    /**
     * Company
     * @param query query
     * @param page page
     * @param size size
     * @param sort sort
     */
    public void searchCompanies(String query, int page, int size, String[] sort) {
        Call<List<DTOCompany>> call = companyInterface.searchCompanies(page, size, query, sort);
        call.enqueue(new Callback<List<DTOCompany>>() {
            @Override
            public void onResponse(Call<List<DTOCompany>> call, Response<List<DTOCompany>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    List<DTOCompany> dtoCompanies = response.body();


                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCompany>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getCompanies(final int page, int size, String[] sort) {
        Call<List<DTOCompany>> call = companyInterface.getCompanies(page, size, sort);
        call.enqueue(new Callback<List<DTOCompany>>() {
            @Override
            public void onResponse(Call<List<DTOCompany>> call, Response<List<DTOCompany>> response) {
                if(!isError(response.code()) || response.isSuccessful()) {
                    List<DTOCompany> dtoCompanies = response.body();

                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCompany>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getCompanyWithID(long id) {
        Call<DTOCompany> call = companyInterface.getCompanyWithID(id);
        call.enqueue(new Callback<DTOCompany>() {
            @Override
            public void onResponse(Call<DTOCompany> call, Response<DTOCompany> response) {
                if(!isError(response.code()) || response.isSuccessful()) {
                    DTOCompany dtoCompany = response.body();

                } else {
                    T.showError(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOCompany> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    /**
     * Search Functions
     * @param query query
     */
    public void searchProductQParameters(String query) {
        Call<List<DTOProductQParameter>> call = productQParameterInterface.searchProductQParameters(query);
        call.enqueue(new Callback<List<DTOProductQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOProductQParameter>> call, Response<List<DTOProductQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful() ){
                    List<DTOProductQParameter> dtoProductQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);
                } else {
                    T.showSuccess(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOProductQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void searchUserQParameters(String query) {
        Call<List<DTOUserQParameter>> call = userQParameterInterface.searchUserQParameters(query);
        call.enqueue(new Callback<List<DTOUserQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOUserQParameter>> call, Response<List<DTOUserQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    List<DTOUserQParameter> dtoUserQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);
                } else {
                    T.showSuccess(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOUserQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void searchQuestionParameters(String query) {
        Call<List<DTOQuestionParameter>> call = questionParameterInterface.searchQuestionParameters(query);
        call.enqueue(new Callback<List<DTOQuestionParameter>>() {
            @Override
            public void onResponse(Call<List<DTOQuestionParameter>> call, Response<List<DTOQuestionParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {
                    List<DTOQuestionParameter> dtoQuestionParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);
                } else {
                    T.showSuccess(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOQuestionParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void searchCategorieQParameters(String query) {
        Call<List<DTOCategoryQParameter>> call = categoryQParameterInterface.searchCategorieQParameters(query);
        call.enqueue(new Callback<List<DTOCategoryQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOCategoryQParameter>> call, Response<List<DTOCategoryQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {

                    List<DTOCategoryQParameter> dtoCategoryQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);
                } else {
                    T.showSuccess(context, parseError(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCategoryQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void searchCategories(String query) {
        Call<List<DTOCategory>> call = categoryInterface.searchCategories(query);
        call.enqueue(new Callback<List<DTOCategory>>() {
            @Override
            public void onResponse(Call<List<DTOCategory>> call, Response<List<DTOCategory>> response) {
                if( !isError(response.code()) || response.isSuccessful() ) {

                    List<DTOCategory> dtoCategories = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                } else {
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCategory>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    /**
     * Get With Id Functions
     * @param id id
     */
    public void getProductQParameterWithID(long id) {
        Call<DTOProductQParameter> call = productQParameterInterface.getProductQParameterWithID(id);
        call.enqueue(new Callback<DTOProductQParameter>() {
            @Override
            public void onResponse(Call<DTOProductQParameter> call, Response<DTOProductQParameter> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    DTOProductQParameter dtoProductQParameter = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOProductQParameter> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getUserQParameterWithID(long id) {
        Call<DTOUserQParameter> call = userQParameterInterface.getUserQParameterWithID(id);
        call.enqueue(new Callback<DTOUserQParameter>() {
            @Override
            public void onResponse(Call<DTOUserQParameter> call, Response<DTOUserQParameter> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    DTOUserQParameter dtoUserQParameter = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOUserQParameter> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getQuestionParameterWithID(long id) {
        Call<DTOQuestionParameter> call = questionParameterInterface.getQuestionParameterWithID(id);
        call.enqueue(new Callback<DTOQuestionParameter>() {
            @Override
            public void onResponse(Call<DTOQuestionParameter> call, Response<DTOQuestionParameter> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    DTOQuestionParameter dtoQuestionParameter = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOQuestionParameter> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getCategoryQParameterWithID(long id) {
        Call<DTOCategoryQParameter> call = categoryQParameterInterface.getCategoryQParameterWithID(id);
        call.enqueue(new Callback<DTOCategoryQParameter>() {
            @Override
            public void onResponse(Call<DTOCategoryQParameter> call, Response<DTOCategoryQParameter> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    DTOCategoryQParameter dtoCategoryQParameter = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOCategoryQParameter> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getCategoryWithID(long id) {
        Call<DTOCategory> call = categoryInterface.getCategoryWithID(id);
        call.enqueue(new Callback<DTOCategory>() {
            @Override
            public void onResponse(Call<DTOCategory> call, Response<DTOCategory> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    DTOCategory dtoCategory = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<DTOCategory> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    /**
     * Get All Functions
     */
    public void getAllProductQParameters() {
        Call<List<DTOProductQParameter>> call = productQParameterInterface.getProductQParameters();
        call.enqueue(new Callback<List<DTOProductQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOProductQParameter>> call, Response<List<DTOProductQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    List<DTOProductQParameter> dtoProductQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOProductQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getAllUserQParameters() {
        Call<List<DTOUserQParameter>> call = userQParameterInterface.getUserQParameters();
        call.enqueue(new Callback<List<DTOUserQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOUserQParameter>> call, Response<List<DTOUserQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    List<DTOUserQParameter> dtoUserQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOUserQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getAllQuestionParameters() {
        Call<List<DTOQuestionParameter>> call = questionParameterInterface.getQuestionParameters();
        call.enqueue(new Callback<List<DTOQuestionParameter>>() {
            @Override
            public void onResponse(Call<List<DTOQuestionParameter>> call, Response<List<DTOQuestionParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    List<DTOQuestionParameter> dtoQuestionParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOQuestionParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getAllCategoryQParameters() {
        Call<List<DTOCategoryQParameter>> call = categoryQParameterInterface.getCategoryQParameters();
        call.enqueue(new Callback<List<DTOCategoryQParameter>>() {
            @Override
            public void onResponse(Call<List<DTOCategoryQParameter>> call, Response<List<DTOCategoryQParameter>> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    List<DTOCategoryQParameter> dtoCategoryQParameters = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCategoryQParameter>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    public void getAllCategories() {
        Call<List<DTOCategory>> call = categoryInterface.getCategories();
        call.enqueue(new Callback<List<DTOCategory>>() {
            @Override
            public void onResponse(Call<List<DTOCategory>> call, Response<List<DTOCategory>> response) {
                if( !isError(response.code()) || response.isSuccessful()) {

                    List<DTOCategory> dtoCategoryList = response.body();

                    A.goOtherActivity(context, HealthInsuranceActivity.class);

                }else{
                    T.showSuccess(context, parseError( response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<DTOCategory>> call, Throwable t) {
                T.showError(context, t.getMessage());
            }
        });
    }

    @OnClick(R.id.liLayOverView)
    public void overViewClick() {

        goToOtherActivity(SuggestionsActivity.class, null, false);
    }

    @Override
    public void onBackPressed() {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);

        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= SystemClock.uptimeMillis() - 1500) {
            moveTaskToBack(true);
            this.finishAffinity();
            return;
        }
        //Toast.makeText(getApplicationContext(), getString(R.string.two_exit), Toast.LENGTH_SHORT).show();
        T.showInfo(context, getString(R.string.two_exit));
    }
}
