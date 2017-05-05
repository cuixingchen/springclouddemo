package com.cuixingchen.springcloud.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuipengfei on 17-4-25.
 */
@Path("/aggdemo")
@Produces(MediaType.APPLICATION_JSON)
public class AggregateDemoResource {

    private Logger logger= LoggerFactory.getLogger(AggregateDemoResource.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @GET
    @Path("/get")
    public Response get(){
        Criteria c = Criteria.where("skus").exists(true);
        List<Criteria> cList = new ArrayList<>();
        cList.add(Criteria.where("goodsId").is("GD201608190000000727"));
        cList.add(Criteria.where("skus.skuId").is("GDSKU201608190000001506"));
        c.andOperator(cList.toArray(new Criteria[cList.size()]));
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(c), Aggregation.unwind("skus"), Aggregation.match(c), Aggregation.sort(new Sort(Sort.Direction.DESC, "skus.updateTime")),
                Aggregation.project( "skus.skuId", "skus.skuName","goodsName")).withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build());
        AggregationResults<Sku> result = mongoTemplate.aggregate(agg, "goods", Sku.class);
        logger.info("end time   : " + System.currentTimeMillis());
        logger.info("result:"+result.getMappedResults().toString());
        return Response.ok(""+result.getMappedResults()).type(MediaType.APPLICATION_JSON).build();
    }

    class Goods{
        private String goodsName;
        private String goodsId;

        @Override
        public String toString() {
            return "Goods{" +
                    "goodsName='" + goodsName + '\'' +
                    ", goodsId='" + goodsId + '\'' +
                    '}';
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }
    }

    class Sku implements Serializable{

        private String skuId;
        private String skuName;
        private String GoodsName;

        @Override
        public String toString() {
            return "Sku{" +
                    "skuId='" + skuId + '\'' +
                    ", skuName='" + skuName + '\'' +
                    '}';
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String goodsName) {
            GoodsName = goodsName;
        }
    }
}
