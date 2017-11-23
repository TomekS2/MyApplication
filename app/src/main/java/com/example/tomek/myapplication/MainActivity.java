package com.example.tomek.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream is = getResources().openRawResource(R.raw.db);
        DocumentBuilderFactory Fabryka = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder budowniczy = Fabryka.newDocumentBuilder();
            Document doc = budowniczy.parse(is);
            doc.getDocumentElement().normalize();
            final NodeList animalNodes = doc.getElementsByTagName("imie");
            ListView lista = findViewById(R.id.lista);
            adapter=new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    listItems);
            lista.setAdapter(adapter);
            Button przycisk = findViewById(R.id.przycisk);
            przycisk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i =0;i<animalNodes.getLength();i++){
                        Node imie = animalNodes.item(i);
                        String imies = imie.getTextContent();
                        listItems.add(imies);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
