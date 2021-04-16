package com.skillbox.mongodemo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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

// общее количество студентов в базе.
// количество студентов старше 40 лет.
// имя самого молодого студента.
// список курсов самого старого студента.

        // Используем JSON-синтаксис для написания запроса (выводим общее количество студентов)
        System.out.println(collection.countDocuments());

        // Используем JSON-синтаксис для написания запроса (выбираем документы с Type=2)
        BsonDocument query = BsonDocument.parse("{ age: { $gte: 40 } }");
        collection.find(query).?????;
    }
}
