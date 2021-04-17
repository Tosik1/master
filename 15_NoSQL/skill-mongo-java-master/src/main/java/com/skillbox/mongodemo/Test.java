package com.skillbox.mongodemo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BasicBSONObject;
import org.bson.BsonDocument;
import org.bson.Document;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Test {

    private static final String filePath = "C:\\Users\\Kita\\java_basics\\java_basics\\15_NoSQL\\skill-mongo-java-master\\mongo.csv";

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection<Document> collection = database.getCollection("TestSkillDemo");

        // Удалим из нее все документы
        collection.drop();

        ArrayList<String> array = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Записали всех студентов в массив
                array.add(line);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //добавляем каждого студента в коллекцию
        for (String student : array){
            String[] category = student.split(",", 3);
            Document doc = new Document().append("name", category[0]).append("age", category[1]).append("courses", category[2]);
            collection.insertOne(doc);
        }

        // выводим общее количество студентов
        System.out.println(collection.countDocuments());

        // количество студентов старше 40
        MongoCollection<Document> gt40 = database.getCollection("gt40");
        BsonDocument query = BsonDocument.parse("{ name: { $gte: 40 } }");
        collection.find(new Document("age", new Document("$gte", "40"))).forEach((Consumer<Document>) doc -> gt40.insertOne(doc));
        System.out.println(gt40.countDocuments());

        // имя самого солодого студента
        collection.find().sort(new BasicDBObject("age", 1)).limit(1).forEach((Consumer<Document>) doc -> System.out.println(doc.get("name")));

        // список курсов самого старого студента
        collection.find().sort(new BasicDBObject("age", -1)).limit(1).forEach((Consumer<Document>) doc -> System.out.println(doc.get("courses")));
    }
}
