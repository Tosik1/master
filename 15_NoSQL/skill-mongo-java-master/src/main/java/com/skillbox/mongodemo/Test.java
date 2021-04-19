package com.skillbox.mongodemo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BasicBSONObject;
import org.bson.BsonDocument;
import org.bson.Document;


import javax.print.Doc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Test {

    private static final String filePath = "C:\\Users\\Kita\\java_basics\\java_basics\\15_NoSQL\\skill-mongo-java-master\\mongo.csv";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection<Document> shops = database.getCollection("shop");
        MongoCollection<Document> products = database.getCollection("products");

        // Удалим из нее все документы
        shops.drop();
        products.drop();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;){
            try {
                String command = reader.readLine();
                String[] words = command.split(" ");
                if (words[0].equals("ДОБАВИТЬ_МАГАЗИН") || words[0].equals("добавить_магазин")){

                    // имя самого солодого студента
//        collection.find().sort(new BasicDBObject("age", 1)).limit(1).forEach((Consumer<Document>) doc -> System.out.println(doc.get("name")));
                        if (words.length == 2) {
                            shops.insertOne(new Document().append("name", words[1]));
                        } else {
                            System.out.println("Введите название магазина в 1 слово");
                        }
                }else if (words[0].equals("ДОБАВИТЬ_ТОВАР") || words[0].equals("добавить_товар")){
                    if (words.length == 3){
                        products.insertOne(new Document().append("name", words[1]).append("price", words[2]));
                    }else{
                        System.out.println("Введите название товара в 1 слово и цену");
                    }
                }else if (words[0].equals("ВЫСТАВИТЬ_ТОВАР") || words[0].equals("выставить_товар")){

                }else if (words[0].equals("СТАТИСТИКА_ТОВАРОВ") || words[0].equals("статистика_товаров")){

                }else {
                    System.out.println("Команда не распознана.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
