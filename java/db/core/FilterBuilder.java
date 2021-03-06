package db.core;

import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonValue;
import org.bson.types.ObjectId;

import static java.util.Arrays.asList;

public class FilterBuilder extends Builder {

    public FilterBuilder eqId(final String id){
        targetDoc.append("_id",new BsonObjectId(new ObjectId(id)));
        return this;
    }

    public FilterBuilder eq(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, value);
        return this;
    }

    public FilterBuilder ne(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$ne", value));
        return this;
    }

    public FilterBuilder gt(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$gt", value));
        return this;
    }

    public FilterBuilder lt(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$lt", value));
        return this;
    }

    public FilterBuilder gte(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$gte", value));
        return this;
    }

    public FilterBuilder lte(final String fieldName, final BsonValue value) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$lte", value));
        return this;
    }

    public FilterBuilder in(final String fieldName, final BsonValue... values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$in", values));
        return this;
    }

    public FilterBuilder in(final String fieldName, final Iterable<BsonValue> values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$in", values));
        return this;
    }

    public FilterBuilder nin(final String fieldName, final BsonValue... values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$nin", values));
        return this;
    }

    public FilterBuilder nin(final String fieldName, final Iterable<BsonValue> values) {
        StringHelper.notNullOrEmpty("fieldName", fieldName);
        targetDoc.append(fieldName, getOperatorFilter("$nin", values));
        return this;
    }

    public FilterBuilder and(final Iterable<BsonValue> filters) {
        targetDoc.append("$and", toBsonArray(filters));
        return this;
    }

    public FilterBuilder and(final BsonValue... filters) {
        targetDoc.append("$and", toBsonArray(filters));
        return this;
    }

    public FilterBuilder or(final Iterable<BsonValue> filters) {
        targetDoc.append("$or", toBsonArray(filters));
        return this;
    }

    public FilterBuilder or(final BsonValue... filters) {
        targetDoc.append("$or", toBsonArray(filters));
        return this;
    }

    public FilterBuilder not(final BsonValue filter) {
        targetDoc.append("$not", filter);
        return this;
    }

    public FilterBuilder nor(final BsonValue... filters) {
        targetDoc.append("$nor", toBsonArray(filters));
        return this;
    }

    public FilterBuilder nor(final Iterable<BsonValue> filters) {
        targetDoc.append("$nor", toBsonArray(filters));
        return this;
    }

    private void notNullOrEmpty(final String fieldName) {
        if (fieldName == null || fieldName.isEmpty())
            throw new IllegalArgumentException("fieldName can not be null or empty");
    }

    private BsonDocument getOperatorFilter(final String operatorName, final BsonValue value) {
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, value);
        return doc;
    }

    private BsonDocument getOperatorFilter(final String operatorName, final BsonValue... values) {
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, toBsonArray(values));
        return doc;
    }

    private BsonDocument getOperatorFilter(final String operatorName, final Iterable<BsonValue> values) {
        BsonDocument doc = new BsonDocument();
        doc.append(operatorName, toBsonArray(values));
        return doc;
    }

    private BsonArray toBsonArray(final BsonValue... values) {
        return new BsonArray(asList(values));
    }

    private BsonArray toBsonArray(final Iterable<BsonValue> values) {
        BsonArray array = new BsonArray();
        values.forEach(array::add);
        return array;
    }

}

