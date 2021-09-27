package com.smality.realm_database_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import io.realm.*;
public class MainActivity extends AppCompatActivity {
    Button add, view, update, delete;
    EditText roll_no, name;
    TextView text;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        roll_no = (EditText)findViewById(R.id.roll_no);
        name = (EditText)findViewById(R.id.name);
        text = (TextView)findViewById(R.id.text);

        realm = Realm.getDefaultInstance();

    }
    //Her button için setOnClickListener kodunu uzun uzun yazmak yerine xmlde onClick özelliğine clickAction metodunu tanımladım.
    //Böylelikle kısa ve daha anlaşılır kod yazmış oldum.
    public void clickAction(View view){
        switch (view.getId()){
            case R.id.add:  addRecord();
                break;
            case R.id.view: viewRecord();
                break;
            case R.id.update:   updateRecord();
                break;
            case R.id.delete:   deleteRecord();
        }
    }
    public void addRecord(){
        realm.beginTransaction();
        //Student sınıfında belirlenin objeleri createObject ile oluşturduk.
        Student student = realm.createObject(Student.class);
        //Tablo sütunlarına verileri ekliyoruz...
        student.setRoll_no(Integer.parseInt(roll_no.getText().toString()));
        student.setName(name.getText().toString());

        realm.commitTransaction();
    }
    public void viewRecord(){
        //Tüm kayıtları getiren sorgu
        RealmResults<Student> results = realm.where(Student.class).findAll();

        //Kayıtların arayüzde gösterilmesi
        for(Student student : results){
            text.append("Rol No: "+student.getRoll_no() + "\nName: " + student.getName() + "\n");
        }
    }
    public void updateRecord(){
        realm.beginTransaction();
        //roll_no sutünundaki değer edittexten gelen değeri eşitse ilgili kayıtı güncellenecek
        RealmResults<Student> results = realm.where(Student.class).equalTo("roll_no", Integer.parseInt(roll_no.getText().toString())).findAll();

        for(Student student : results){
            student.setName(name.getText().toString());
        }
        realm.commitTransaction();
    }
    public void deleteRecord(){
        realm.beginTransaction();
        //roll_no sutünundaki değer edittexten gelen değeri eşitse ilgili kayıt silenecek
        RealmResults<Student> results = realm.where(Student.class).equalTo("roll_no", Integer.parseInt(roll_no.getText().toString())).findAll();
        results.deleteAllFromRealm();

        realm.commitTransaction();
    }
    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
