package com.example.ecommerceapp.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ecommerceapp.ui.MainActivity;
import com.example.ecommerceapp.auth.AuthActivity;
import com.example.ecommerceapp.auth.User;
import com.example.ecommerceapp.utils.TinyDB;

import static com.example.ecommerceapp.utils.Constants.USER;


public class SplashActivity extends AppCompatActivity {
    SplashViewModel splashViewModel;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* initSplashViewModel();
        checkIfUserIsAuthenticated();*/
        tinyDB = new TinyDB(this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                tinyDB.putString("dashboardData", "{\"statusCode\":200,\"message\":\"Success\",\"data\":{\"topBar\":[{\"id\":1,\"name\":\"Top Image 1\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top1.png\",\"seriesID\":6},{\"id\":2,\"name\":\"Top Image 2\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top2.png\",\"seriesID\":1},{\"id\":3,\"name\":\"Top Image 3\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top3.png\",\"seriesID\":9},{\"id\":4,\"name\":\"Top Image 4\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top4.png\",\"seriesID\":10},{\"id\":5,\"name\":\"Top Image 5\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top5.png\",\"seriesID\":99},{\"id\":6,\"name\":\"Top Image 6\",\"imageLink\":\"stock-vector-logout-icon-exit-vector-576166198.jpg\",\"seriesID\":4},{\"id\":7,\"name\":\"Top Image 7\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top7.png\",\"seriesID\":6}],\"category\":[{\"categoryID\":2,\"category_Name\":\"Special purpose\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/RetroFitment.png\"},{\"categoryID\":3,\"category_Name\":\"Riding gear\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/RidingWear.jpg\"},{\"categoryID\":4,\"category_Name\":\"Utilty & convienience\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/P6300460.png\"},{\"categoryID\":5,\"category_Name\":\"Design & style\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/RidingWear.jpg\"},{\"categoryID\":6,\"category_Name\":\"Life style accessories\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/RidingWear.jpg\"},{\"categoryID\":7,\"category_Name\":\"Protection & security\",\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/RidingWear.jpg\"}],\"subCategory\":[{\"subCategoryID\":1,\"subCategoryName\":\"Scooter\",\"categoryID\":1,\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/Zest.png\"},{\"subCategoryID\":2,\"subCategoryName\":\"MOTOR CYCLE\",\"categoryID\":1,\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/Apache.png\"},{\"subCategoryID\":3,\"subCategoryName\":\"Moped\",\"categoryID\":1,\"imageLink\":\"https://www.advantagetvs.com/Pgm_Prod_Img/top3.png\"}]}}");
            }
        });
    }

    private void initSplashViewModel() {
        splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {
        splashViewModel.checkIfUserIsAuthenticated();

        if (!splashViewModel.isUserAuthenticatedLiveData.isAuthenticated) {
            goToAuthInActivity();
            finish();
        } else {
            getUserFromDatabase(splashViewModel.isUserAuthenticatedLiveData.uid);
        }

    }

    private void goToAuthInActivity() {
        Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
        startActivity(intent);
    }

    private void getUserFromDatabase(String uid) {
        splashViewModel.setUid(uid);
        splashViewModel.userLiveData.observe(this, user -> {
            goToMainActivity(user);
            finish();
        });
    }

    private void goToMainActivity(User user) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra(USER, user);
        startActivity(intent);
    }
}