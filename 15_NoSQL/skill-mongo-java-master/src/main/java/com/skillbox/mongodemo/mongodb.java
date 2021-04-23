package com.skillbox.mongodemo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Variable;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.expr;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static java.util.Arrays.asList;

public class mongodb {


    public static MongoCollection getCollection(MongoDatabase mongoDatabase, String name){
        return mongoDatabase.getCollection(name);
    }

    public static void addShop(String[] words, MongoCollection shops){
        if (words.length == 2) {
            shops.insertOne(new Document().append("name", words[1]));
        } else {
            System.out.println("Введите название магазина в 1 слово");
        }
    }

    public static void addProduct(String[] words, MongoCollection products){
        if (words.length == 3){
            products.insertOne(new Document().append("name", words[1]).append("price", words[2]));
        }else{
            System.out.println("Введите название товара в 1 слово и цену");
        }
    }

    public static void addProductInShop(String[] words, MongoCollection shops, MongoCollection products){
        shops.findOneAndUpdate(new Document("name", new Document("$eq", words[2])), new Document("$set", new Document("product", words[1]))) ;
        List<Variable<?>> variables = asList(
                new Variable<>("product_shop", "$product"));
        List<Bson> pipeline = asList(
//                            expr(new Document("&and", asList(new Document("$eq", asList("$name", "$$product_item")), new Document("price", "price1")))),
                match(expr(new Document("&eq", asList("$name", "$$product_item")))),
                project(fields(excludeId())));
        lookup("products", variables, pipeline, "product_list");
    }
}
