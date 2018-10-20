package com.iskandar.gordiuswheel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest jrq;

    EditText BoxPass, BoxUser;
    Button btnIniciar, btnCrearCuenta;
    TextView ErrorPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BoxUser = (EditText) findViewById(R.id.etEmail);
        BoxPass = (EditText) findViewById(R.id.etPass);

        btnIniciar = (Button) findViewById(R.id.btnLogin);
        btnCrearCuenta = (Button) findViewById(R.id.btnCreateAccount);

        ErrorPass = (TextView) findViewById(R.id.tvPassIncorrect);

        rq = Volley.newRequestQueue(this);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion();
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateAccountActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        //ErrorPass.setText("Email o Contraseña Incorrecto");
        Toast.makeText(this, "Inicio De Sesion Fallido " + error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        User usuario = new User();

        Toast.makeText(this, "Inicio De Sesion Exitoso " + BoxUser.getText().toString(), Toast.LENGTH_SHORT).show();

        JSONArray jsonArray = jsonObject.optJSONArray("datos");
        JSONObject jsonObject1 = null;

        try {
            jsonObject1 = jsonArray.getJSONObject(0);
            usuario.setEmail(jsonObject.optString("Usuario_Cuenta"));
            usuario.setName(jsonObject.optString("Nombre"));
            usuario.setPass(jsonObject.optString("Contrasena_Cuenta"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void IniciarSesion(){

        String url="https://gordiuswheelyae.000webhostapp.com/login.php?user="+BoxUser.getText().toString()+"&pwd="+BoxPass.getText().toString();

        //String url="https://semilio9818.000webhostapp.com/sesion.php?email="+BoxUser.getText().toString()+"&pass="+BoxPass.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        rq.add(jrq);
    }

}
