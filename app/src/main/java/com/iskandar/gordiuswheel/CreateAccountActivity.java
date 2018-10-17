package com.iskandar.gordiuswheel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateAccountActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq1;
    JsonRequest jrq1;

    EditText etName, etLastName1, etLastName2,
            etUser, etPass, etConfirmPass,
            etBirthday, etPhone, etEmail,
            etMainStreet, etSecondaryStreet,
            etNumberInt, etNumberExt;

    ImageView imageView;
    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        etName = (EditText)findViewById(R.id.etName);
        etLastName1 = (EditText)findViewById(R.id.etLastName1);
        etLastName2 = (EditText)findViewById(R.id.etLastName2);
        etUser = (EditText)findViewById(R.id.etUser);
        etPass = (EditText)findViewById(R.id.etPass);
        etConfirmPass = (EditText)findViewById(R.id.etConfirmPass);
        etBirthday = (EditText)findViewById(R.id.etBirthday);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etMainStreet = (EditText)findViewById(R.id.etMainStreet);
        etSecondaryStreet = (EditText)findViewById(R.id.etSecondaryStreet);
        etNumberInt = (EditText)findViewById(R.id.etNumberInt);
        etNumberExt = (EditText)findViewById(R.id.etNumberExt);

        btnCreateAccount = (Button)findViewById(R.id.btnCreateAccount);

        rq1 = Volley.newRequestQueue(this);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        //ErrorPass.setText("Email o Contraseña Incorrecto");
        Toast.makeText(this, "Registro Fallido " + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        Toast.makeText(this, "Registro Exitoso " + etUser.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    private void createAccount(){

        String url="http://192.168.43.207/login/register.php?user="+etUser.getText().toString()+"&pwd="+etPass.getText().toString()
                +"&name=" +etName.getText().toString() +"&lastname1="+etLastName1.getText().toString() +"&lastname2="+etLastName2.getText().toString();

        //String url="https://semilio9818.000webhostapp.com/sesion.php?email="+BoxUser.getText().toString()+"&pass="+BoxPass.getText().toString();

        jrq1 = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq1.add(jrq1);
    }
}

