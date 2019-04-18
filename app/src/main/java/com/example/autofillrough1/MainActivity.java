package com.example.autofillrough1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //    ประกาศตัวแปร
    private AutoCompleteTextView userName;
    private EditText userTambon, userProvince, userCode;

    //    String จ.อ.ต.
    private String jsonString = "http://119.59.103.121/app_mobile/get%20spinner.php";
//            "{\n" +
//            "  \"address\": [\n" +
//            "    {\n" +
//            "      \"id\": 2,\n" +
//            "      \"name\": \"bangchak\",\n" +
//            "      \"tambon\": \"phakhanong\",\n" +
//            "      \"province\": \"bangkok\",\n" +
//            "      \"code\": \"10120\"\n" +
//            "    },\n" +
//
//            "    {\n" +
//            "      \"id\": 3,\n" +
//            "      \"name\": \"ramkhamhang\",\n" +
//            "      \"tambon\": \"banhkapi\",\n" +
//            "      \"province\": \"bangkok\",\n" +
//            "      \"code\": \"11170\"\n" +
//            "    },\n" +
//
//            "  ]\n" +
//            "}";

//    มาจาก Class User
    private ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        get event
        initView();

//        userList เชื่อมโยงกับ String ที่ชื่อว่า jsonString
        userList = extractUser(jsonString);

//        class UserAdapter เชื่อมโยงกับlayput user_raw_layout และuserList ของ User
        UserAdapter userAdapter = new UserAdapter(this, R.layout.user_raw_layout, userList);

//        ที่กรอกข้อมูล ตำบล/แขวง
        userName.setAdapter(userAdapter);
//        กำหนดเกณฑ์(1)
        userName.setThreshold(1);
//      ตัวกำหนดตัวแปรว่า ตัวแปรนี้คู่กับข้อมูลนี้
        userName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//               เชื่อมต่อ Class User รับรายการที่ตำแหน่ง position
                User user = (User) parent.getItemAtPosition(position);
                userName.setText(user.getName());// id userName ตรงกับ getName
                userTambon.setText(user.getTambon());// id userTambon ตรงกับ getTambon
                userProvince.setText(user.getProvince());// id userProvince ตรงกับ getProvince
                userCode.setText(user.getCode());// id userCode ตรงกับ getCode
            }
        });

    }// Method

    private void initView() {
//        get event
        userName = findViewById(R.id.user_name);
        userTambon = findViewById(R.id.user_tambon);
        userProvince = findViewById(R.id.user_province);
        userCode = findViewById(R.id.user_code);

        userList = new ArrayList<>();
    }

    private ArrayList<User> extractUser(String jsonString) {
        ArrayList<User> list = new ArrayList<>();

        try {
            JSONObject rootJO = new JSONObject(jsonString);

            JSONArray userJA = rootJO.getJSONArray(jsonString);

            for (int i = 0; i < userJA.length(); i++) {

                JSONObject jo = userJA.getJSONObject(i);

                int id = jo.getInt("id");
                String name = jo.getString("tambon_th");
                String tambon = jo.getString("amphur_th");
                String province = jo.getString("province_th");
                String code = jo.getString("sdist_code");

                User user = new User(id, name, tambon, province, code);

                list.add(user);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}//Main