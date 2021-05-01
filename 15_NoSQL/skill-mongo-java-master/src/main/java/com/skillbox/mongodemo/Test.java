package com.skillbox.mongodemo;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Variable;
import org.bson.BasicBSONObject;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.expr;
import static com.mongodb.client.model.Projections.*;
import static java.util.Arrays.asList;


import javax.print.Doc;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Test {

    private static final String filePath = "C:\\Users\\Kita\\java_basics\\java_basics\\15_NoSQL\\skill-mongo-java-master\\mongo.csv";

    public static void main(String[] args) throws IOException {

        MongoClient mongoClient = new MongoClient( "127.0.0.1" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("local");

        // Создаем коллекцию
        MongoCollection shops = mongodb.getCollection(database, "shop");
        MongoCollection products = mongodb.getCollection(database, "products");

        // Удалим из нее все документы
        shops.drop();
        products.drop();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (;;){
            try {
                String command = reader.readLine();
                String[] words = command.split(" ");
                if (words[0].equals("ДОБАВИТЬ_МАГАЗИН") || words[0].equals("добавить_магазин")){
                    mongodb.addShop(words, shops);
                }

                else if (words[0].equals("ДОБАВИТЬ_ТОВАР") || words[0].equals("добавить_товар")){
                    mongodb.addProduct(command, products, words[0]);
                }

//                ВЫСТАВИТЬ_ТОВАР Вафли Девяточка
                else if (words[0].equals("ВЫСТАВИТЬ_ТОВАР") || words[0].equals("выставить_товар")){
                    mongodb.addProductInShop(words, shops, products);
                    System.out.println(products.getNamespace().getCollectionName());
                }

                else if (words[0].equals("СТАТИСТИКА_ТОВАРОВ") || words[0].equals("статистика_товаров")){
                    mongodb.productStatistics(shops, products);
                }

                else {
                    System.out.println("Команда не распознана.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        ArrayList<String> array = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                //Записали всех студентов в массив
//                array.add(line);
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        //добавляем каждого студента в коллекцию
//        for (String student : array){
//            String[] category = student.split(",", 3);
//            Document doc = new Document().append("name", category[0]).append("age", category[1]).append("courses", category[2]);
//            collection.insertOne(doc);
//        }
//
//        // выводим общее количество студентов
//        System.out.println(collection.countDocuments());
//
//        // количество студентов старше 40
//        MongoCollection<Document> gt40 = database.getCollection("gt40");
//        BsonDocument query = BsonDocument.parse("{ name: { $gte: 40 } }");
//        collection.find(new Document("age", new Document("$gte", "40"))).forEach((Consumer<Document>) doc -> gt40.insertOne(doc));
//        System.out.println(gt40.countDocuments());
//
//        // имя самого солодого студента
//        collection.find().sort(new BasicDBObject("age", 1)).limit(1).forEach((Consumer<Document>) doc -> System.out.println(doc.get("name")));
//
//        // список курсов самого старого студента
//        collection.find().sort(new BasicDBObject("age", -1)).limit(1).forEach((Consumer<Document>) doc -> System.out.println(doc.get("courses")));
    }
}
