package com.example.FlightSelection.Pages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class BookingData {

    private ArrayList<ExampleData> data = new ArrayList();
    Logger logger = LoggerFactory.getLogger(BookingData.class);

    public BookingData() {
        try {
            File jsonFile = new File(Path.of("src", "test", "java", "com", "example", "FlightSelection", "TestData.json").toUri());
            HashMap read_data = new ObjectMapper().readValue(jsonFile, HashMap.class);

            for (int count = 0; count< read_data.values().toArray().length; count++) {
                data.add(new ExampleData((String) Arrays.toString(read_data.values().toArray())));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Retrieved data from json file...");

    }

    public ExampleData getData(int index){return data.get(index);}

    public class ExampleData {

            String origen;
            String destino;
            String fecha_ida;
            String fecha_vuelta;
            String adultos;
            String ninos;
            String bebes;

            public ExampleData(String data) {
                String[] values = data.split(",");

                origen = values[0].split("=")[1];
                destino = values[1].split("=")[1];

                fecha_ida = values[2].split("=")[1];
                fecha_vuelta = values[3].split("=")[1];

                adultos = values[4].split("=")[1];
                ninos = values[5].split("=")[1];
                bebes = values[6].split("=")[1].substring(0,1);
            }

        public String getOrigen() {return origen;}
        public String getDestino() {return destino;}
        public String getFecha_ida() {return fecha_ida;}
        public String getFecha_vuelta() {return fecha_vuelta;}
        public String getAdultos() {return adultos;}
        public String getNinos() {return ninos;}
        public String getBebes() {return bebes;}
    }
}
