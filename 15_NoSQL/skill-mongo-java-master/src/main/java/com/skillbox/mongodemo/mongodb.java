package com.skillbox.mongodemo;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.BsonDocument;
import org.bson.Document;

import javax.print.Doc;
import java.util.Arrays;
import java.util.function.Consumer;

import static com.mongodb.client.model.Projections.fields;

public class mongodb {


    public static MongoCollection<Document> getCollection(MongoDatabase mongoDatabase, String name){
        return mongoDatabase.getCollection(name);
    }

    public static void addShop(String[] words, MongoCollection shops){
        if (words.length == 2) {
            shops.insertOne(new Document().append("name", words[1]));
        } else {
            System.out.println("Введите название магазина в 1 слово");
        }
    }

    public static void addProduct(String command, MongoCollection products, String addProductCommand){
        String[] array = command.replaceAll(addProductCommand, "").trim().split(" ");
        products.insertOne(new Document().append("product", array[0]).append("price", Integer.valueOf(array[1])));
    }

    public static void addProductInShop(String[] words, MongoCollection shops, MongoCollection products){
        shops.findOneAndUpdate(new Document("name", new Document("$eq", words[2])), new Document("$addToSet", new Document("product", words[1]))) ;
    }

    public static void productStatistics(MongoCollection shops, MongoCollection products){
        AggregateIterable<Document> productPriceStatistics = shops.aggregate(Arrays.asList(
                Aggregates.lookup(products.getNamespace().getCollectionName(), "product", "product", "products"),
                Aggregates.unwind("$products"),
                Aggregates.group("shops", Accumulators.sum("ItemsSum", 1),
                        Accumulators.avg("AvgPrice", "$products.price"),
                        Accumulators.max("MaxPrice", "$products.price"),
                        Accumulators.min("MinPrice", "$products.price"),
                        Accumulators.sum("lessThen100", Document.parse("{ \"$cond\": [ { \"$lt\": [ \"$products.price\", 100 ] }, 1, 0 ] }")))
                )
        );
        for (Document doc : productPriceStatistics){
            System.out.println(doc.toJson());
        }
    }
}
